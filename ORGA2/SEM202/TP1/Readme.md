# UNGS - 2<sup>do</sup> cuatrimestre del 2020 - Organizador del computador II - TP1


## Resolvente en ASM
Se creará un programa para la arquitectura IA32 que calcule las raíces de una funcion cuadratica ( a&xscr;<sup>2</sup> + b&xscr; + c = 0 ) a través de la formula [resolvente][square-root-wiki-link].


![equation](http://latex.codecogs.com/svg.latex?\frac{&space;&space;-b&space;\pm&space;\sqrt{b^{2}&space;-&space;4&space;\times&space;a&space;\times&space;c}}{2&space;\times&space;a})


Teniendo en cuenta las siguientes condiciones:


- Que las constantes "a", "b" y "c" puedan tomar valores de puntos flotantes.
- b<sup>2</sup> - 4.a.c &geq; 0 / &forall; a,b,c &varepsilon; &reals;
- a &ne; 0, a &varepsilon; &reals;


En creará un programa en C que pida las constantes por consola y llame a la función desarrollada en ASM para que calcule las raices de la función cuadrática.


Por último se compilará y linkearán los archivos objetos de ambos programas de manera separada. Obteniendo un ejecutable que solicite por consola las variables "a", "b" y "c" ( parte desarrollada en C ) y calcule sus raices mostrandolas por consola ( parte desarrollada en ASM ). 


### Pasos de ejecución ( Ubuntu 16 )


- Se deberán descargar los siguientes archivos en la misma carpeta.
    - [resolvente.s](https://github.com/NFER179/ProyectosUNGS/blob/master/ORGA2/SEM202/TP1/resolvente/resolvente.s)
    - [main.c](https://github.com/NFER179/ProyectosUNGS/blob/master/ORGA2/SEM202/TP1/resolvente/main.c)
    - [exe.sh](https://github.com/NFER179/ProyectosUNGS/blob/master/ORGA2/SEM202/TP1/resolvente/exe.sh)


- Una vez descargado los archivos, por consola ingresar en la carpeta en donde se alojaron los mismos.


[instalacion_01][instalacion_01]


- Una vez en la ubicación de la carpeta ejecutar el siguiente comando:

    ```sh
    $ sh ./exe.sh
    ```

    Esto nos generará un archivo nombrado "executable" más los archivos objeto de los programas.


[instalacion_02][instalacion_02]


- Para ejecutar nuestro programa usaremos el siguiente comando:

    ```sh
    $ ./executable
    ```


### ¿Como funciona?


- Primero abrimos una consola y mediante ella nos ubicamos dentro de la carpeta en la que alojamos el archivo que generamos ( "executable" ).


[ejecucion_01][ejecucion_01]


- Una vez ya dentro de la carpeta, ejecutamos el siguiente comando desde consola.

    ```sh
    $ ./executable
    ```

[ejecucion_02][ejecucion_02]

- Como veremos, por consola nos pedidará que ingresemos cada uno de las constantes de la funcion cuadratica.
    - "a" &rightarrow; para la constante que acompaña la x elevada al cuadrado.

    - "b" &rightarrow; para la constante que acompaña a la x no eleveda.

    - "c" &rightarrow; para la constante que no acompaña a ninguna x.


- Cada vez que ingresamos una de las constantes deberemos presionar la tecla intro para poder ingresar la siguiente constante.


- Una vez ya ingresadas las tres constantes se mostrará por consola el resultado de las raices.


[ejecucion_03][ejecucion_03]


- En caso de que el parametro "a" ( parametro cuadrado ) sea igual a "0" o que las raices pertenezcan al conjunto de los &Im;( Conjunto imaginario &ImaginaryI; ), se mostrará un mensaje por consola informandolo.


[error_no_cuadratic][error_no_cuadratic]


[error_no_cuadratic][error_no_cuadratic]


## Entregas obligatorias


También se contará para el TP con las siguientes [entregas obligatorias][entregas_obligatorias]:


- [Ejercicio 4 - Gestión de memoria]
- [Ejercicio 6 - Gestión de memoria]
- [Ejercicio 7 - Gestión de memoria]
- [Ejercicio 4 - FPU]



[square-root-wiki-link]: https://es.wikipedia.org/wiki/Ecuaci%C3%B3n_de_segundo_grado

[entregas_obligatorias]: #

[instalacion_01]: https://github.com/NFER179/ProyectosUNGS/blob/master/ORGA2/SEM202/TP1/img/instalacion_01.png

[instalacion_02]: https://github.com/NFER179/ProyectosUNGS/blob/master/ORGA2/SEM202/TP1/img/instalacion_02.png

[ejecucion_01]: https://github.com/NFER179/ProyectosUNGS/blob/master/ORGA2/SEM202/TP1/img/ejecucion_01.png

[ejecucion_02]: https://github.com/NFER179/ProyectosUNGS/blob/master/ORGA2/SEM202/TP1/img/ejecucion_02.png

[ejecucion_03]: https://github.com/NFER179/ProyectosUNGS/blob/master/ORGA2/SEM202/TP1/img/ejecucion_03.png

[error_no_cuadratic]: https://github.com/NFER179/ProyectosUNGS/blob/master/ORGA2/SEM202/TP1/img/error_no_cuadratic.png

[error_imaginary]: https://github.com/NFER179/ProyectosUNGS/blob/master/ORGA2/SEM202/TP1/img/error_imaginary.png
