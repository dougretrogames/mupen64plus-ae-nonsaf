@echo off
"C:\\Program Files\\Android\\Android Studio\\jbr\\bin\\java" ^
  --class-path ^
  "C:\\Users\\Douglas\\.gradle\\caches\\modules-2\\files-2.1\\com.google.prefab\\cli\\2.1.0\\aa32fec809c44fa531f01dcfb739b5b3304d3050\\cli-2.1.0-all.jar" ^
  com.google.prefab.cli.AppKt ^
  --build-system ^
  cmake ^
  --platform ^
  android ^
  --abi ^
  x86 ^
  --os-version ^
  21 ^
  --stl ^
  c++_shared ^
  --ndk-version ^
  26 ^
  --output ^
  "C:\\Users\\Douglas\\AppData\\Local\\Temp\\agp-prefab-staging4499810360516976670\\staged-cli-output" ^
  "C:\\Users\\Douglas\\.gradle\\caches\\8.14.3\\transforms\\79f2a3c6b545820f18a47e75c49681eb\\transformed\\jetified-oboe-1.6.1\\prefab"
