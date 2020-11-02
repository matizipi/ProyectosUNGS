nasm -f elf32 resolvente.s -o resolvente.o
gcc -m32 -o executable resolvente.o main.c
