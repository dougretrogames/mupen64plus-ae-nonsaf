if(NOT TARGET oboe::oboe)
add_library(oboe::oboe SHARED IMPORTED)
set_target_properties(oboe::oboe PROPERTIES
    IMPORTED_LOCATION "C:/Users/Douglas/.gradle/caches/8.14.3/transforms/79f2a3c6b545820f18a47e75c49681eb/transformed/jetified-oboe-1.6.1/prefab/modules/oboe/libs/android.x86/liboboe.so"
    INTERFACE_INCLUDE_DIRECTORIES "C:/Users/Douglas/.gradle/caches/8.14.3/transforms/79f2a3c6b545820f18a47e75c49681eb/transformed/jetified-oboe-1.6.1/prefab/modules/oboe/include"
    INTERFACE_LINK_LIBRARIES ""
)
endif()

