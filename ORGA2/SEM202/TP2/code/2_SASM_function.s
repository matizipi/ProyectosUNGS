global enmascarar_asm

section .data
   
    blanco db 255,255,255,255,255,255,255,255

section .text

enmascarar_asm:
    
    push ebp ;enter
    mov ebp, esp ;enter
    
    mov ebx,0        ;limpio ebx para usarlo para moverme en la direccion a la que apunta cada imagen
    mov edx,[EBP+20] ;cant bytes por img
    movq mm3,qword[blanco]
    
ciclo:
    
    cmp edx,0
    je fin
    
    mov eax,[ebp+16]   ;mascara
    movq mm0,[eax+ebx] ;guardo contenido de mascara en mm0 y me desplazo con ebx
    mov eax,[ebp+8]    ;img1
    movq mm1,[eax+ebx] ;guardo contenido de img1 en mm1 y me desplazo con ebx
    mov eax,[ebp+12]   ;img2
    movq mm2,[eax+ebx] ;guardo contenido de img2 en mm2 y me desplazo con ebx
        
    pand mm2,mm0  ;borro de la img 2 los canales de pixeles donde la mascara es negra 
    pxor mm0,mm3  ;creo la negacion de la mascara y mm3 = (0,0,0,0,0,0,0,0) -> negro en los 8
    pand mm1, mm0 ;borro de la img 1 los canales de pixeles donde la imagen es blanca
    por mm1, mm2  ;sumamos las dos imagenes alteradas y guardo el resultado en la imagen 1
      
    mov eax,[ebp+8]
    movq qword[eax+ebx], mm1
    
    sub edx,8 ;bajo en 8 (porque cada posicion en registros mmx tiene esa cantidad de bytes) 
    add ebx,8 ;aumento en 8 el desplazamiento en memoria | misma situacion que caso anterior
    jmp ciclo
    
fin:
   
    mov ebp,esp ;Reset Stack (leave)
    pop ebp ;Restore (leave)
    
    ret
