%include "io.inc"
extern printf

section .data
    
    float_array: dq 3.5,2.5,3.7,4.5,3.3
    float_arraylen: equ ($ - float_array)/8
    float_suma: dq 0
    msg_float_len: db "Suma de todo el array: %f",10,13,0

    msg_len: db "Len array: %d",10,13,0

section .text
global CMAIN
CMAIN:
    mov ebp, esp; for correct debugging
    ;write your code here

    call print_len

suma_vf:
    mov ebx, float_array
    mov ecx, float_arraylen
    xor edx, edx


    fld qword[float_suma]
       
float_loop:
    fld qword[ebx+edx*8]
    faddp
    inc edx
    
    cmp ecx, edx
    jg float_loop

    fstp qword[float_suma]    
    
    push dword[float_suma+4]
    push dword[float_suma]
    push msg_float_len
    call printf
    add esp, 12
    
    xor eax, eax
    ret

print_len:

    push ebp
    mov ebp, esp
    
    push float_arraylen
    push msg_len
    call printf
    add esp, 8
    
    mov esp, ebp
    pop ebp
    
    ret