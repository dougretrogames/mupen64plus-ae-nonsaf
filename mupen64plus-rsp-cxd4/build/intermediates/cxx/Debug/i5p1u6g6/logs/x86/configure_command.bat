@echo off
"C:\\Users\\Douglas\\AppData\\Local\\Android\\Sdk\\ndk\\26.1.10909125\\ndk-build.cmd" ^
  "NDK_PROJECT_PATH=null" ^
  "APP_BUILD_SCRIPT=C:\\Users\\Douglas\\mupen64plus-ae-nonsaf\\mupen64plus-rsp-cxd4\\Android.mk" ^
  "NDK_APPLICATION_MK=C:\\Users\\Douglas\\mupen64plus-ae-nonsaf\\mupen64plus-rsp-cxd4\\Application.mk" ^
  "APP_ABI=x86" ^
  "NDK_ALL_ABIS=x86" ^
  "NDK_DEBUG=1" ^
  "APP_PLATFORM=android-21" ^
  "NDK_OUT=C:\\Users\\Douglas\\mupen64plus-ae-nonsaf\\mupen64plus-rsp-cxd4\\build\\intermediates\\cxx\\Debug\\i5p1u6g6/obj" ^
  "NDK_LIBS_OUT=C:\\Users\\Douglas\\mupen64plus-ae-nonsaf\\mupen64plus-rsp-cxd4\\build\\intermediates\\cxx\\Debug\\i5p1u6g6/lib" ^
  "APP_CPPFLAGS+=-fexceptions" ^
  "NDK_APPLICATION_MK:=Application.mk" ^
  -j11 ^
  "APP_SHORT_COMMANDS=false" ^
  "LOCAL_SHORT_COMMANDS=false" ^
  -B ^
  -n
