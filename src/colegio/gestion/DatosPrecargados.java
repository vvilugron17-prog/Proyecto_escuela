package colegio.gestion;

import colegio.modelo.Alumno;
import colegio.modelo.Arte;
import colegio.modelo.Ciencia;
import colegio.modelo.Curso;
import colegio.modelo.Matematica;
import colegio.modelo.Profesor;

// Carga datos de ejemplo.
public final class DatosPrecargados {

    private DatosPrecargados() {
    }

    public static void cargar(Colegio colegio) {

        if (colegio == null) {
            throw new IllegalArgumentException("Debe indicar un colegio válido.");
        }

        // Alumnos.
        Alumno alumno1 = new Alumno("A1", "Alumno1", "Apellido1");
        Alumno alumno2 = new Alumno("A2", "Alumno2", "Apellido2");
        Alumno alumno3 = new Alumno("A3", "Alumno3", "Apellido3");
        Alumno alumno4 = new Alumno("A4", "Alumno4", "Apellido4");
        Alumno alumno5 = new Alumno("A5", "Alumno5", "Apellido5");

        colegio.agregarAlumno(alumno1);
        colegio.agregarAlumno(alumno2);
        colegio.agregarAlumno(alumno3);
        colegio.agregarAlumno(alumno4);
        colegio.agregarAlumno(alumno5);

        // Profesores.
        Profesor profesor1 = new Profesor(
                "P1", "Profesor1", "Apellido1", "Matematica");

        Profesor profesor2 = new Profesor(
                "P2", "Profesor2", "Apellido2", "Ciencias");

        Profesor profesor3 = new Profesor(
                "P3", "Profesor3", "Apellido3", "Artes");

        colegio.agregarProfesor(profesor1);
        colegio.agregarProfesor(profesor2);
        colegio.agregarProfesor(profesor3);

        // Cursos de Matematica.
        Curso matematica1 = new Matematica("Matematica1", 4, 30, 3, 4);
        Curso matematica2 = new Matematica("Matematica2", 3, 25, 2, 3);
        Curso matematica3 = new Matematica("Matematica3", 3, 25, 2, 2);

        // Cursos de Ciencias.
        Curso ciencia1 = new Ciencia("Ciencia1", 4, 30, 3, 3);
        Curso ciencia2 = new Ciencia("Ciencia2", 4, 30, 3, 2);
        Curso ciencia3 = new Ciencia("Ciencia3", 3, 25, 2, 3);

        // Cursos de Artes.
        Curso arte1 = new Arte("Arte1", 2, 20, 2, 4);
        Curso arte2 = new Arte("Arte2", 2, 20, 2, 3);
        Curso arte3 = new Arte("Arte3", 2, 20, 2, 3);

        colegio.agregarCurso(matematica1);
        colegio.agregarCurso(matematica2);
        colegio.agregarCurso(matematica3);

        colegio.agregarCurso(ciencia1);
        colegio.agregarCurso(ciencia2);
        colegio.agregarCurso(ciencia3);

        colegio.agregarCurso(arte1);
        colegio.agregarCurso(arte2);
        colegio.agregarCurso(arte3);

        System.out.println(
                "Se cargaron 5 alumnos, 3 profesores y 9 cursos.");
    }
}