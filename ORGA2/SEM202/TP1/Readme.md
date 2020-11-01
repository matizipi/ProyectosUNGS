# UNGS - 2<sup>do</sup> cuatrimestre del 2020 - Organizador del computador II - TP1

## Resolvente en ASM
Se creará un programa para la arquitectura IA32 que calcule las raíces de una funcion cuadratica ( a.x<sup>2</sup> + b.x + c = 0 ) a través de la formula [resolvente][square-root-wiki-link].

[![N|Solid](dsds)](#)

Teniendo en cuenta las siguientes condiciones:

- Que las constantes "a", "b" y "c" puedan tomar valores de puntos flotantes.
- b<sup>2</sup> - 4.a.c &geq; 0 / &forall; a,b,c &varepsilon; &reals;
- a &lt;&gt; 0, a &varepsilon; &reals;

En creará un programa en C que pida las constantes por consola y llame a la función desarrollada en ASM para que calcule las raices de la función cuadrática.

Por último se compilará y linkearán los archivos objetos de ambos programas de manera separada. Obteniendo un ejecutable que solicite por consola las variables "a", "b" y "c" ( parte desarrollada en C ) y calcule sus raices mostrandolas por consola ( parte desarrollada en ASM ). 

### Pasos de ejecución ( Ubuntu 16 )

- Se deberán descargar los siguientes archivos en la misma carpeta.
    - [resolvente.s](https://github.com/NFER179/ProyectosUNGS/blob/master/ORGA2/SEM202/TP1/resolvente/resolvente.s)
    - [main.c](https://github.com/NFER179/ProyectosUNGS/blob/master/ORGA2/SEM202/TP1/resolvente/main.c)
    - [exe.sh](https://github.com/NFER179/ProyectosUNGS/blob/master/ORGA2/SEM202/TP1/resolvente/exe.sh)

- Una vez descargado los archivos, por consola ingresar en la carpeta en donde se alojaron los mismos.

- Una vez en la ubicación de la carpeta ejecutar el siguiente comando:

    ```sh
    $ sh ./exe.sh
    ```

    Esto nos generará un archivo nombrado "executable" más los archivos objeto de los programas.

- Para ejecutar nuestro programa usaremos el siguiente comando:

    ```sh
    $ ./executable
    ```

### ¿Como funciona?

- Primero abrimos una consola y mediante ella nos ubicamos dentro de la carpeta en la que alojamos el archivo que generamos ( "executable" ).

- Una vez ya dentro de la carpeta, ejecutamos el siguiente comando desde consola.

    ```sh
    $ ./executable
    ```

- Como veremos, por consola nos pedidará que ingresemos cada uno de las constantes de la funcion cuadratica.
    - "a" &rightarrow; para la constante que acompaña la x elevada al cuadrado.

    - "b" &rightarrow; para la constante que acompaña a la x no eleveda.

    - "c" &rightarrow; para la constante que no acompaña a ninguna x.

- Cada vez que ingresamos una de las constantes deberemos presionar la tecla intro para poder ingresar la siguiente constante.

- Una vez ya ingresadas las tres constantes se mostrará por consola el resultado de las raices.

- En caso de que el parametro "a" ( parametro cuadrado ) sea igual a "0" o que las raices pertenezcan al conjunto de los [****], se mostrará un mensaje por consola informandolo.

## Entregas obligatorias

También se contará para el TP con las siguientes entregas obligatorias:

- [Ejercicio 4 - Gestión de memoria][ejercicio04-gestion-memoria]
- [Ejercicio 6 - Gestión de memoria][ejercicio06-gestion-memoria]
- [Ejercicio 7 - Gestión de memoria][ejercicio07-gestion-memoria]
- [Ejercicio 4 - FPU][ejercicio04-FPU]


[square-root-wiki-link]: https://es.wikipedia.org/wiki/Ecuaci%C3%B3n_de_segundo_grado

[ejercicio04-gestion-memoria]: #

[ejercicio06-gestion-memoria]: #

[ejercicio07-gestion-memoria]: #

[ejercicio04-FPU]: #
