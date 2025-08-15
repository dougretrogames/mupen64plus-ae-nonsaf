package paulscode.android.mupen64plusae;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.contentesesourcesCompat;
import androidx.preference.PreferenceManager;
import androidx.tvprovider.media.tv.Channel;
import androidx.tvprovider.media.tv.TvContractCompat;
import androidx.tvprovider.media.tv.ChannelLogoUtils;

import paulscode.android.mupen64plusae.R;

import java.util.List;

import paulscode.android.mupen64plusae.cheat.CheatUtils;
import paulscode.android.mupen64plusae.persistent.AppData;
import paulscode.android.mupen64plusae.persistent.GlobalPrefs;
import paulscode.android.mupen64plusae.task.ExtractAssetsOrCleanupTask;
import paulscode.android.mupen64plusae.task.ExtractAssetsOrCleanupTask.ExtractAssetsListener;
import paulscode.android.mupen64plusae.task.ExtractAssetsOrCleanupTask.Failure;
import paulscode.android.mupen64plusae.task.SyncProgramsJobService;
import paulscode.android.mupen64plusae.util.DeviceUtil;
import paulscode.android.mupen64plusae.util.FileUtil;
import paulscode.android.mupen64plusae.util.LocaleContextWrapper;
import paulscode.android.mupen64plusae.util.Notifier;
import paulscode.android.mupen64plusae.util.RomDatabase;

public class SplashActivity extends AppCompatActivity implements ExtractAssetsListener, ActivityCompat.OnRequestPermissionsResultCallback
{
    static final int PERMISSION_REQUEST = 177;
    static final int NUM_PERMISSIONS = 2; // READ + WRITE STORAGE

    private static final int SPLASH_DELAY = 1000;
    public static final String SOURCE_DIR = "mupen64plus_data";

    private TextView mTextView;
    private AppData mAppData = null;
    private GlobalPrefs mGlobalPrefs = null;

    private AlertDialog mPermissionsNeeded = null;
    private boolean mRequestingPermissions = false;

    private static final String STATE_REQUESTING_PERMISSIONS = "STATE_REQUESTING_PERMISSIONS";

    @Override
    protected void attachBaseContext(Context newBase) {
        if (TextUtils.isEmpty(LocaleContextWrapper.getLocalCode())) {
            super.attachBaseContext(newBase);
        } else {
            super.attachBaseContext(LocaleContextWrapper.wrap(newBase, LocaleContextWrapper.getLocalCode()));
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i("SplashActivity", "onNewIntent");
        super.onNewIntent(intent);
        setIntent(intent);
        ActivityHelper.startGalleryActivity(SplashActivity.this, getIntent());
        finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("SplashActivity", "onCreate");
        super.onCreate(savedInstanceState);

        DeviceUtil.clearLogCat();

        mAppData = new AppData(this);
        mGlobalPrefs = new GlobalPrefs(this, mAppData);

        PreferenceManager.setDefaultValues(this, R.xml.preferences_audio, false);
        PreferenceManager.setDefaultValues(this, R.xml.preferences_data, false);
        PreferenceManager.setDefaultValues(this, R.xml.preferences_display, false);
        PreferenceManager.setDefaultValues(this, R.xml.preferences_input, false);
        PreferenceManager.setDefaultValues(this, R.xml.preferences_library, false);
        PreferenceManager.setDefaultValues(this, R.xml.preferences_touchscreen, false);

        mGlobalPrefs = new GlobalPrefs(this, mAppData);

        FileUtil.makeDirs(mGlobalPrefs.touchscreenCustomSkinsDir);

        Notifier.initialize(this);

        getWindow().setFlags(LayoutParams.FLAG_KEEP_SCREEN_ON, LayoutParams.FLAG_KEEP_SCREEN_ON);

        try {
            setContentView(R.layout.splash_activity);
        } catch (android.view.InflateException e) {
            Log.e("SplashActivity", "Resource NOT found");
            Notifier.showToast(this, R.string.invalidInstall_message);
            return;
        }

        mTextView = findViewById(R.id.mainText);

        try {
            Drawable randomDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_arrow_u, null);
            if (randomDrawable != null) {
                Log.i("SplashActivity", "Resource found: " + randomDrawable.toString());
            }
        } catch (android.contentesesources.NotFoundException e) {
            Log.e("SplashActivity", "Resource NOT found");
            Notifier.showToast(this, R.string.invalidInstall_message);
            return;
        }

        if (mGlobalPrefs.isBigScreenMode) {
            final ImageView splash = findViewById(R.id.mainImage);
            splash.setImageResource(R.drawable.publisherlogo);
        }

        if (mAppData.isAndroidTv && AppData.IS_OREO && mAppData.getChannelId() == -1) {
            createChannel();
        }

        SyncProgramsJobService.scheduleSyncingProgramsForChannel(this, mAppData.getChannelId());

        if (savedInstanceState != null) {
            mRequestingPermissions = savedInstanceState.getBoolean(STATE_REQUESTING_PERMISSIONS);
        }

        if (!mRequestingPermissions) {
            requestPermissions();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.i("SplashActivity", "onSaveInstanceState");
        savedInstanceState.putBoolean(STATE_REQUESTING_PERMISSIONS, mRequestingPermissions);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        Log.i("SplashActivity", "onDestroy");
        super.onDestroy();
        if (mPermissionsNeeded != null) {
            mPermissionsNeeded.dismiss();
        }
    }

    public void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permissionEAD_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permissionEAD_EXTERNAL_STORAGE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                mPermissionsNeeded = new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.assetExtractor_permissions_title))
                        .setMessage(getString(R.string.assetExtractor_permissions_rationale))
                        .setPositiveButton(getString(android.string.ok), (dialog, which) -> actuallyRequestPermissions())
                        .setNegativeButton(getString(android.string.cancel), (dialog, which) -> new AlertDialog.Builder(SplashActivity.this)
                                .setTitle(getString(R.string.assetExtractor_error))
                                .setMessage(getString(R.string.assetExtractor_failed_permissions))
                                .setPositiveButton(android.string.ok, (dialog1, which1) -> SplashActivity.this.finish())
                                .setCancelable(false).show())
                        .setCancelable(false).show();
            } else {
                actuallyRequestPermissions();
            }
        } else {
            checkExtractAssetsOrCleanup();
        }
    }

    @SuppressLint("InlinedApi")
    public void actuallyRequestPermissions() {
        mRequestingPermissions = true;
        ActivityCompatequestPermissions(this, new String[]{
                Manifest.permissionEAD_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST) {
            boolean good = true;
            if (permissions.length != NUM_PERMISSIONS || grantResults.length != NUM_PERMISSIONS) good = false;
            for (int i = 0; i < grantResults.length && good; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    good = false;
                    break;
                }
            }

            if (!good) {
                mPermissionsNeeded = new AlertDialog.Builder(SplashActivity.this)
                        .setTitle(getString(R.string.assetExtractor_error))
                        .setMessage(getString(R.string.assetExtractor_failed_permissions))
                        .setPositiveButton(android.string.ok, (dialog, which) -> SplashActivity.this.finish())
                        .setCancelable(false).show();
            } else {
                checkExtractAssetsOrCleanup();
            }
        }
    }

    private void checkExtractAssetsOrCleanup() {
        if (mAppData.getAssetCheckNeeded() || mAppData.getAppVersion() != mAppData.appVersionCode ||
                !ExtractAssetsOrCleanupTask.areAllAssetsValid(PreferenceManager.getDefaultSharedPreferences(this),
                        SOURCE_DIR, mAppData.coreSharedDataDir)) {

            mAppData.putAppVersion(mAppData.appVersionCode);
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(extractAssetsTaskLauncher, SPLASH_DELAY);
        } else {
            ActivityHelper.startGalleryActivity(SplashActivity.this, getIntent());
            finish();
        }
    }

    private final Runnable extractAssetsTaskLauncher = this::extractAssets;

    private void extractAssets() {
        new ExtractAssetsOrCleanupTask(this, getAssets(), mAppData, mGlobalPrefs, SOURCE_DIR, mAppData.coreSharedDataDir, SplashActivity.this).doInBackground();
    }

    @Override
    public void onExtractAssetsProgress(String nextFileToExtract, int currentAsset, int totalAssets) {
        runOnUiThread(() -> {
            float percent = (100f * currentAsset) / totalAssets;
            String text = getString(R.string.assetExtractor_progress, percent, nextFileToExtract);
            mTextView.setText(text);
        });
    }

    @Override
    public void onExtractAssetsFinished(List<Failure> failures) {
        runOnUiThread(() -> {
            if (failures.size() != 0) {
                String message = getString(R.string.assetExtractor_failed);
                StringBuilder builder = new StringBuilder();
                builder.append(messageeplace("\n", "<br/>")).append("<p><small>");
                for (Failure failure : failures) {
                    builder.append(failure.toString()).append("<br/>");
                }
                builder.append("</small>");
                mTextView.setText(AppData.fromHtml(builder.toString()));
                Log.e("SplashActivity", "Setting text: " + AppData.fromHtml(builder.toString()));
                mAppData.putAssetCheckNeeded(true);
            } else {
                mAppData.putAssetCheckNeeded(false);
                mTextView.setText(R.string.assetExtractor_finished);
            }

            CheatUtils.mergeCheatFiles(mAppData.mupencheat_default, mGlobalPrefs.customCheats_txt, mAppData.mupencheat_txt);

            if (!RomDatabase.getInstance().hasDatabaseFile()) {
                RomDatabase.getInstance().setDatabaseFile(mAppData.mupen64plus_ini);
            }

            final Handler handler = new Handler(Looper.getMainLooper());
            long delay = failures.size() != 0 ? 5000 : 0;
            handler.postDelayed(() -> {
                ActivityHelper.startGalleryActivity(SplashActivity.this, getIntent());
                SplashActivity.this.finish();
            }, delay);
        });
    }

    private void createChannel() {
        Channel.Builder builder = new Channel.Builder();
        Intent appIntent = new Intent(getApplicationContext(), SplashActivity.class);
        appIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        builder.setType(TvContractCompat.Channels.TYPE_PREVIEW)
                .setDisplayName(getString(R.string.showRecentlyPlayed_title))
                .setAppLinkIntent(appIntent);

        Context context = getApplicationContext();
        try {
            Uri channelUri = context.getContentResolver().insert(
                    TvContractCompat.Channels.CONTENT_URI, builder.build().toContentValues());
            if (channelUri != null) {
                long channelId = ContentUris.parseId(channelUri);
                mAppData.putChannelId(channelId);
                Bitmap bitmapIcon = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
                ChannelLogoUtils.storeChannelLogo(context, channelId, bitmapIcon);
                TvContractCompatequestChannelBrowsable(context, channelId);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
