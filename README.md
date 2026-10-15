# Sistema de Gestión Escolar

Proyecto Java de consola desarrollado para NetBeans (paquete principal: `colegio`).

## Ejecutar en NetBeans
1. Descomprimir el ZIP.
2. Abrir la carpeta `Proyecto_escuela-main` como proyecto existente de NetBeans.
3. Ejecutar el proyecto con F6. La clase principal es `colegio.Main`.

También se puede compilar desde la carpeta del proyecto con `ant clean jar` y ejecutar el JAR generado en `dist/`.

## Funciones
- Gestión de cursos de Matemática, Ciencia y Arte, con niveles, cupos, horas y ponderación de evaluaciones/tareas.
- Registro, búsqueda y ficha de alumnos; registro de notas y cálculo de promedios.
- Registro de profesores y asignación a cursos.
- Inscripción de alumnos en cursos con validación de cupos.
- Ranking de alumnos y cursos, más historial de operaciones.
- Datos de ejemplo (9 cursos, 5 alumnos y profesores) para comenzar a probar.
- Guardado automático al elegir “Guardar y salir” en `colegio_datos.dat`, que se carga al iniciar.

Las notas permitidas van de 1,0 a 7,0. El archivo `colegio_datos.dat` se crea en el directorio de trabajo desde el que se ejecuta el programa.
