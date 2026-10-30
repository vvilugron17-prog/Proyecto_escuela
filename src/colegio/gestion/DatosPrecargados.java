package colegio.gestion;

import colegio.modelo.Alumno;
import colegio.modelo.Arte;
import colegio.modelo.Ciencia;
import colegio.modelo.Curso;
import colegio.modelo.Matematica;
import colegio.modelo.Profesor;

// [REQUISITO: Package] Carga datos de ejemplo.
public final class DatosPrecargados {

    private DatosPrecargados() {
    }

    public static void cargar(Colegio colegio) {

        if (colegio == null) {
            throw new IllegalArgumentException("Debe indicar un colegio válido.");
        }

        // Alumnos.
        Alumno alumno1 = new Alumno("Camila", "Rojas");
        Alumno alumno2 = new Alumno("Matías", "Pérez");
        Alumno alumno3 = new Alumno("Antonia", "Muñoz");
        Alumno alumno4 = new Alumno("Diego", "Soto");
        Alumno alumno5 = new Alumno("Fernanda", "Vera");

        colegio.agregarAlumno(alumno1);
        colegio.agregarAlumno(alumno2);
        colegio.agregarAlumno(alumno3);
        colegio.agregarAlumno(alumno4);
        colegio.agregarAlumno(alumno5);

        // Profesores.
        Profesor profesor1 = new Profesor(
                "Paula", "Rojas", "Matematica");

        Profesor profesor2 = new Profesor(
                "Rodrigo", "Pérez", "Ciencias");

        Profesor profesor3 = new Profesor(
                "Marcela", "Muñoz", "Artes");

        colegio.agregarProfesor(profesor1);
        colegio.agregarProfesor(profesor2);
        colegio.agregarProfesor(profesor3);

        // Cursos de Matematica.
        Curso matematica1 = new Matematica("Álgebra", 4, 30, 3, 4);
        matematica1.asignarNivelCurso();//Método para asignarle un nivel al curso creado
        Curso matematica2 = new Matematica("Cálculo", 3, 25, 2, 3);
        matematica2.asignarNivelCurso();//Método para asignarle un nivel al curso creado
        Curso matematica3 = new Matematica("Geometría", 3, 25, 2, 2);
        matematica3.asignarNivelCurso();//Método para asignarle un nivel al curso creado

        // Cursos de Ciencias.
        Curso ciencia1 = new Ciencia("Física", 4, 30, 3, 3);
        ciencia1.asignarNivelCurso();//Método para asignarle un nivel al curso creado
        Curso ciencia2 = new Ciencia("Biología", 4, 30, 3, 2);
        ciencia2.asignarNivelCurso();//Método para asignarle un nivel al curso creado
        Curso ciencia3 = new Ciencia("Química", 3, 25, 2, 3);

        // Cursos de Artes.
        Curso arte1 = new Arte("Dibujo", 2, 20, 2, 4);
        Curso arte2 = new Arte("Música", 2, 20, 2, 3);
        Curso arte3 = new Arte("Teatro", 2, 20, 2, 3);

        colegio.agregarCurso(matematica1);
        colegio.agregarCurso(matematica2);
        colegio.agregarCurso(matematica3);

        colegio.agregarCurso(ciencia1);
        colegio.agregarCurso(ciencia2);
        colegio.agregarCurso(ciencia3);

        colegio.agregarCurso(arte1);
        colegio.agregarCurso(arte2);
        colegio.agregarCurso(arte3);

        // [REQUISITO: Herencia] Datos iniciales para demostrar funciones del sistema.
        colegio.asignarProfesorACurso(profesor1.obtenerId(), matematica1.obtenerId());
        colegio.asignarProfesorACurso(profesor2.obtenerId(), ciencia1.obtenerId());
        colegio.asignarProfesorACurso(profesor3.obtenerId(), arte1.obtenerId());

        colegio.inscribirAlumnoEnCurso(alumno1.obtenerId(), matematica1.obtenerId());
        colegio.inscribirAlumnoEnCurso(alumno1.obtenerId(), ciencia1.obtenerId());
        colegio.inscribirAlumnoEnCurso(alumno2.obtenerId(), matematica1.obtenerId());
        colegio.inscribirAlumnoEnCurso(alumno3.obtenerId(), arte1.obtenerId());
        colegio.inscribirAlumnoEnCurso(alumno4.obtenerId(), ciencia1.obtenerId());

        colegio.registrarNota(alumno1.obtenerId(), 6.5);
        colegio.registrarNota(alumno1.obtenerId(), 5.8);
        colegio.registrarNota(alumno2.obtenerId(), 4.9);
        colegio.registrarNota(alumno3.obtenerId(), 6.8);
        colegio.registrarNota(alumno4.obtenerId(), 5.2);
        colegio.registrarNota(alumno5.obtenerId(), 6.0);

        System.out.println("Se cargaron 5 alumnos, 3 profesores, 9 cursos y datos de prueba.");
    }
}