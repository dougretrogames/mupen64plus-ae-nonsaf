@echo off
"C:\\Users\\Douglas\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\cmake.exe" ^
  "-HC:\\Users\\Douglas\\mupen64plus-ae-nonsaf\\mupen64plus-video-gliden64\\upstream\\src" ^
  "-DCMAKE_SYSTEM_NAME=Android" ^
  "-DCMAKE_EXPORT_COMPILE_COMMANDS=ON" ^
  "-DCMAKE_SYSTEM_VERSION=21" ^
  "-DANDROID_PLATFORM=android-21" ^
  "-DANDROID_ABI=x86" ^
  "-DCMAKE_ANDROID_ARCH_ABI=x86" ^
  "-DANDROID_NDK=C:\\Users\\Douglas\\AppData\\Local\\Android\\Sdk\\ndk\\26.1.10909125" ^
  "-DCMAKE_ANDROID_NDK=C:\\Users\\Douglas\\AppData\\Local\\Android\\Sdk\\ndk\\26.1.10909125" ^
  "-DCMAKE_TOOLCHAIN_FILE=C:\\Users\\Douglas\\AppData\\Local\\Android\\Sdk\\ndk\\26.1.10909125\\build\\cmake\\android.toolchain.cmake" ^
  "-DCMAKE_MAKE_PROGRAM=C:\\Users\\Douglas\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\ninja.exe" ^
  "-DCMAKE_C_FLAGS=-ffast-math -ftree-vectorize -O3 -g -fno-omit-frame-pointer" ^
  "-DCMAKE_CXX_FLAGS=-fexceptions -ffast-math -ftree-vectorize -O3 -g -fno-omit-frame-pointer" ^
  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=C:\\Users\\Douglas\\mupen64plus-ae-nonsaf\\mupen64plus-video-gliden64\\build\\intermediates\\cxx\\Release\\6i393y2b\\obj\\x86" ^
  "-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=C:\\Users\\Douglas\\mupen64plus-ae-nonsaf\\mupen64plus-video-gliden64\\build\\intermediates\\cxx\\Release\\6i393y2b\\obj\\x86" ^
  "-BC:\\Users\\Douglas\\mupen64plus-ae-nonsaf\\mupen64plus-video-gliden64\\.cxx\\Release\\6i393y2b\\x86" ^
  -GNinja ^
  "-DANDROID=1" ^
  "-DMUPENPLUSAPI=1" ^
  "-DCMAKE_BUILD_TYPE=Release" ^
  "-DUNIX=1" ^
  "-DANDROID_LIB_PATH=C:\\Users\\Douglas\\mupen64plus-ae-nonsaf\\mupen64plus-video-gliden64/../ndkLibs" ^
  "-DANDROID_ARM_NEON=TRUE" ^
  "-DANDROID_TOOLCHAIN=clang"
