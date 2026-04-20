#include <stdio.h>

// The precompiled libpng.a was built against an older NDK where stderr was a global symbol.
// In modern NDKs, stderr is a macro that expands to (&__sF[2]).
// We provide a global stderr symbol here to satisfy the linker.

#undef stderr
FILE *stderr;

// We can initialize it to a dummy value if needed, but the symbol itself is what the linker wants.
// At runtime, if libpng tries to write to it, it might crash if not initialized,
// but mupen64plus usually doesn't trigger png errors that write to stderr.
