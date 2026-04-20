import sys
import re
import os

def main():
    if len(sys.argv) < 3:
        print("Usage: python gen_asm_defines.py <dest_dir> <obj_file>")
        sys.exit(1)

    dest_dir = sys.argv[1]
    obj_file = sys.argv[2]

    nasm_file_path = os.path.join(dest_dir, "asm_defines_nasm.h")
    gas_file_path = os.path.join(dest_dir, "asm_defines_gas.h")

    # Regular expression to match @ASM_DEFINE patterns in binary/text
    # @ASM_DEFINE offsetof_struct_xxx 0xYYY
    pattern = re.compile(b'@ASM_DEFINE\s+([a-zA-Z_0-9]+)\s+(0x[0-9a-fA-F]+)')

    nasm_content = []
    gas_content = []

    try:
        with open(obj_file, 'rb') as f:
            content = f.read()
            for match in pattern.finditer(content):
                name = match.group(1).decode('ascii')
                value = match.group(2).decode('ascii')
                nasm_content.append(f"%define {name} ({value})")
                gas_content.append(f"#define {name} ({value})")
    except Exception as e:
        print(f"Error reading {obj_file}: {e}")
        sys.exit(1)

    try:
        with open(nasm_file_path, 'w') as f:
            f.write('\n'.join(nasm_content) + '\n')
        with open(gas_file_path, 'w') as f:
            f.write('\n'.join(gas_content) + '\n')
    except Exception as e:
        print(f"Error writing output files: {e}")
        sys.exit(1)

if __name__ == "__main__":
    main()
