@echo off
"C:\\Users\\Douglas\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\cmake.exe" ^
  "-HC:\\Users\\Douglas\\mupen64plus-ae-nonsaf\\mupen64plus-audio-android" ^
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
  "-DCMAKE_CXX_FLAGS=-fexceptions -ffast-math -ftree-vectorize -Oz -g" ^
  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=C:\\Users\\Douglas\\mupen64plus-ae-nonsaf\\mupen64plus-audio-android\\build\\intermediates\\cxx\\Release\\725j3w5u\\obj\\x86" ^
  "-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=C:\\Users\\Douglas\\mupen64plus-ae-nonsaf\\mupen64plus-audio-android\\build\\intermediates\\cxx\\Release\\725j3w5u\\obj\\x86" ^
  "-DCMAKE_FIND_ROOT_PATH=C:\\Users\\Douglas\\mupen64plus-ae-nonsaf\\mupen64plus-audio-android\\.cxx\\Release\\725j3w5u\\prefab\\x86\\prefab" ^
  "-BC:\\Users\\Douglas\\mupen64plus-ae-nonsaf\\mupen64plus-audio-android\\.cxx\\Release\\725j3w5u\\x86" ^
  -GNinja ^
  "-DANDROID=1" ^
  "-DCMAKE_BUILD_TYPE=Release" ^
  "-DANDROID_ARM_NEON=TRUE" ^
  "-DANDROID_TOOLCHAIN=clang" ^
  "-DANDROID_LIB_PATH=C:\\Users\\Douglas\\mupen64plus-ae-nonsaf\\mupen64plus-audio-android/../ndkLibs" ^
  "-DM64API_INCLUDE_PATH=C:\\Users\\Douglas\\mupen64plus-ae-nonsaf\\mupen64plus-audio-android/../mupen64plus-core/upstream/src/api/" ^
  "-DANDROID_STL=c++_shared"
