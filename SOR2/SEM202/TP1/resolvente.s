extern printf
global resolvente

section .data
    A: dq 0
    B: dq 0
    C: dq 0

    resol_error_flag: dd 0

    fUnoN: dd -1.0
    fCero: dd 0.0
    fDos: dd 2.0
    fCuatro: dd 4.0

    suma: dq 0.0
    prt00: dq 0.0
    prt01: dq 0.0
    prt02: dq 0.0
    prt03: dq 0.0
    prt04: dq 0.0
    prt05: dq 0.0
    Sprt06: dq 0.0
    Rprt06: dq 0.0
    rootA: dq 0.0
    rootB: dq 0.0
    
    formatox1: db "X1: %f",10,13,0
    formatox2: db "X2: %f",10,13,0

    msg_conj_im: db "Esta resolvente no resuelve raices imaginarias",10,13,0
    msg_div_error: db "Math Error: Parametro  'a' no puede ser 0",10,13,0

    prta: db "[A]:%.2f",10,13,0
    prtb: db "[B]:%.2f",10,13,0
    prtc: db "[C]:%.2f",10,13,0
    formato00: db "prt00  -> -[B]:          %.2f",10,13,0
    formato01: db "prt01  -> [B]*[B]:       %.2f",10,13,0
    formato02: db "prt02  -> 4*[A]*[C]:     %.2f",10,13,0
    formato03: db "prt03  -> prt01 - prt02: %.2f",10,13,0
    formato04: db "prt04  -> raiz( prt03 ): %.2f",10,13,0
    formato05: db "prt05  -> 2*[A]:         %.2f",10,13,0
    formato06: db "Sprt06 -> prt00 + prt04: %.2f",10,13,0
    formato07: db "Rprt06 -> prt00 - prt04: %.2f",10,13,0

section .bss

section .text
resolvente:
    ;Guardo puntero inicial de la pila.
    push ebp
    mov ebp, esp

    ;Tomo los valores desde C
    ;            +------------+
    ;esp, ebp -> |     ...    |
    ;            +------------+
    ;            | resolvente | +4
    ;            +------------+
    ;            |      A     | +8
    ;            +------------+
    ;            |      B     | +12
    ;            +------------+
    ;            |      C     | +16
    ;            +------------+
    fld dword[ebp+8]
    fst qword[A]
    fld dword[ebp+12]
    fst qword[B]
    fld dword[ebp+16]
    fst qword[C]

    mov esp, ebp    ;enter 0,0
    pop ebp ;Enter a la pila (restauro el puntero inicial).

    ; Validaciones
    call validaciones

    ; Si valida algún error termina la ejecución.
    mov eax, 0
    cmp [resol_error_flag], eax
    jne resolvente_end

    ; calculo resolvente
    ; ( -[B] + { raiz( [B]*[B] - 4*[A]*[C] ) } ) / 2*[A]
    ; ( -[B] - { raiz( [B]*[B] - 4*[A]*[C] ) } ) / 2*[A]
    ; dividimos la equacion en:
    ; prt00 = -[B]
    ; prt01 = [B]*[B]
    ; prt02 = 4*[A]*[C]
    ; prt03 = prt01 - prt02
    ; prt04 = raiz( prt03 )
    ; prt05 = 2*[A]
    ; Aca dividimos en dos casos, en la suma ("S") y la resta ("R")
    ; Sprt06 = prt00 + prt04
    ; Rprt06 = prt00 - prt04
    ; rootA = Sprt06 / prt05
    ; rootB = Rprt06 / prt05

    ; prt00 = -[B]
    call negativeB

    ; prt01 = [B]*[B]
    call cuadrado
    
    ; prt02 = 4*[A]*[C]
    call mult02
    
    ; prt03 = prt01 - prt02
    call sub03

    ; prt04 = raiz( prt03 )
    call squaredRoot
    
    ; prt05 = 2*[A]
    call divisor

    ; Sprt06 = prt00 + prt04
    call Sdividendo
    
    ; Rprt06 = prt00 - prt04
    call Rdividendo

    ; rootA = Sprt06 / prt05
    call roota
    
    ; rootB = Rprt06 / prt05
    call rootb

    ; imprimo raicez
    call printRoots

    ; print stepByStep
    ;call stepByStep

    xor eax, eax
    ret

; validaciones
validaciones:
    
    call division_por_cero
    
    call raices_negativas
    
    ret
    
division_por_cero:
    ; A <> 0
    fld qword[A]
    fcomp dword[fCero]
    
    fstsw ax
    fwait
    sahf

    jz  div_error
    
    ret

;Error division por 0
div_error:
    mov eax, 1
    mov [resol_error_flag], eax

    push msg_div_error
    call printf
    add esp, 4

    ret

raices_negativas:
    ; B*B - 4*A*C != 0
    fld qword[B]
    fmul st0, st0   ;B*B

    fld dword[fCuatro]
    fld qword[A]
    fld qword[C]

    fmulp   ;A*C
    fmulp   ;4*A*C

    fsubp   ;B*B - 4*A*C

    fcomp dword[fCero]  ;B*B - 4*A*C = 0 ??

    fstsw ax
    fwait
    sahf
    
    jb conj_im    ;B*B - 4*A*C < 0 (La resolvente entra en conjunto de imaginarios)

    ret

;Error las raices pertenecen al conjunto imaginario
conj_im:
    mov eax, 1
    mov [resol_error_flag], eax

    push msg_conj_im
    call printf
    add esp, 4

    ret

resolvente_end:
    xor eax, eax
    ret    

; prt00 = -[B]
negativeB:
    ;Cargo valores en FPU
    fld qword[B]
    fld dword[fUnoN]
    fmulp
    
    fstp qword[prt00]

    ret

; prt01 = [B]*[B]
cuadrado:
    ;Cargo valores en FPU
    fld qword[B]
    fmul st0, st0;multiplico = potencia cuadrada

    fstp qword[prt01];pop y guardo el valor en prt01

    ret

; prt02 = 4*[A]*[C]
mult02:
    ;Cargo valores en FPU
    fld dword[fCuatro]
    fld qword[C]
    fmulp;multiplicacion guardo en st0 y pop st1

    fld qword[A]
    fmulp;multiplicacion guardo en st0 y pop st1
    
    fstp qword[prt02];pop y guardo dato

    ret

; prt03 = prt01 - prt02
sub03:
    ;Cargo valores en FPU
    fld qword[prt01]
    fld qword[prt02]
    
    fsubp;resta guardo en st0 y pop st1
    
    fstp qword[prt03];pop y guardo
    
    ret

; prt04 = raiz( prt03 )
squaredRoot:
    ;Cargo valores en FPU
    fld qword[prt03]
    
    fsqrt;raiz cuadrada de st0

    fstp qword[prt04]
    
    ret

; prt05 = 2*[A]
divisor:
    ;Cargo valores en FPU
    fld dword[fDos]
    fld qword[A]
    
    fmulp;multiplico, guardo en st0 y pop st1
    
    fstp qword[prt05]

    ret

; Sprt06 = prt00 + prt04
Sdividendo:
    ;Cargo valores en FPU
    fld qword[prt00]
    fld qword[prt04]
    
    faddp;suma guardo en st0 y pop st1
    
    fstp qword[Sprt06]
    
    ret

; roota = Sprt06 / prt05
roota:
    ;Cargo valores en FPU
    fld qword[Sprt06]
    fld qword[prt05]
    
    fdivp;division guardo en st0 y pop st1
    
    fstp qword[rootA]
    
    ret
    
; Rprt06 = [B] - prt04
Rdividendo:
    ;Cargo valores en FPU
    fld qword[prt00]
    fld qword[prt04]
    
    fsubp;resta guardo en st0 y pop st1
    
    fstp qword[Rprt06]
    
    ret

; rootb = Rprt06 / prt05
rootb:
    ;Cargo valores en FPU
    fld qword[Rprt06]
    fld qword[prt05]
    
    fdivp;division guardo en st0 y pop st1
    
    fstp qword[rootB]
    
    ret

; Impresion de raices por consola.
printRoots:

    push dword[rootA+4]
    push dword[rootA]
    push formatox1
    call printf
    add esp, 12

    push dword[rootB+4]
    push dword[rootB]
    push formatox2
    call printf
    add esp, 12

    ret

; Imprime los valores de todas las variables.
stepByStep:

    push dword[A+4]
    push dword[A]
    push prta
    call printf
    add esp, 12

    push dword[B+4]
    push dword[B]
    push prtb
    call printf
    add esp, 12

    push dword[C+4]
    push dword[C]
    push prtc
    call printf
    add esp, 12

    ; prt00 = -[B]
    push dword[prt00+4]
    push dword[prt00]
    push formato00
    call printf
    add esp, 12

    ; prt01 = [B]*[B]
    push dword[prt01+4]
    push dword[prt01]
    push formato01
    call printf
    add esp, 12
    
    ; prt02 = 4*[A]*[C]
    push dword[prt02+4]
    push dword[prt02]
    push formato02
    call printf
    add esp, 12
    
    ; prt03 = prt01 - prt02
    push dword[prt03+4]
    push dword[prt03]
    push formato03
    call printf
    add esp, 12

    ; prt04 = raiz( prt03 )
    push dword[prt04+4]
    push dword[prt04]
    push formato04
    call printf
    add esp, 12

    ; prt05 = 2*[A]
    push dword[prt05+4]
    push dword[prt05]
    push formato05
    call printf
    add esp, 12

    ; Sprt06 = prt00 + prt04
    push dword[Sprt06+4]
    push dword[Sprt06]
    push formato06
    call printf
    add esp, 12
    
    ; Rprt06 = prt00 - prt04
    push dword[Rprt06+4]
    push dword[Rprt06]
    push formato07
    call printf
    add esp, 12

    ret