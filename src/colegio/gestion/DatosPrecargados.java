package colegio.gestion;

import colegio.modelo.Alumno;
import colegio.modelo.Arte;
import colegio.modelo.Ciencia;
import colegio.modelo.Curso;
import colegio.modelo.Matematica;
import colegio.modelo.Profesor;

// Datos de ejemplo para probar el funcionamiento del colegio.
public final class DatosPrecargados {

    private DatosPrecargados() {
    }

    // Carga alumnos, profesores, cursos y algunos registros iniciales.
    public static void cargar(Colegio colegio) {
        if (colegio == null) {
            throw new IllegalArgumentException("Debe indicar un colegio valido.");
        }

        // Alumnos.
        Alumno alumno1 = new Alumno("Camila", "Rojas");
        Alumno alumno2 = new Alumno("Matias", "Perez");
        Alumno alumno3 = new Alumno("Antonia", "Munoz");
        Alumno alumno4 = new Alumno("Diego", "Soto");
        Alumno alumno5 = new Alumno("Fernanda", "Vera");

        colegio.agregarAlumno(alumno1);
        colegio.agregarAlumno(alumno2);
        colegio.agregarAlumno(alumno3);
        colegio.agregarAlumno(alumno4);
        colegio.agregarAlumno(alumno5);

        // Profesores.
        Profesor profesor1 = new Profesor("Paula", "Rojas", "Matematica");
        Profesor profesor2 = new Profesor("Rodrigo", "Perez", "Ciencias");
        Profesor profesor3 = new Profesor("Marcela", "Munoz", "Artes");

        colegio.agregarProfesor(profesor1);
        colegio.agregarProfesor(profesor2);
        colegio.agregarProfesor(profesor3);

        // Los cursos tienen distintas horas para mostrar los tres niveles.
        Curso matematica1 = new Matematica("Algebra", 4, 30, 3, 4);
        Curso matematica2 = new Matematica("Geometria", 8, 25, 3, 3);
        Curso matematica3 = new Matematica("Calculo", 12, 20, 4, 4);

        Curso ciencia1 = new Ciencia("Biologia", 5, 30, 3, 3);
        Curso ciencia2 = new Ciencia("Fisica", 9, 25, 4, 3);
        Curso ciencia3 = new Ciencia("Quimica", 13, 20, 4, 4);

        Curso arte1 = new Arte("Dibujo", 3, 20, 2, 4);
        Curso arte2 = new Arte("Musica", 7, 20, 3, 3);
        Curso arte3 = new Arte("Teatro", 12, 18, 3, 4);

        colegio.agregarCurso(matematica1);
        colegio.agregarCurso(matematica2);
        colegio.agregarCurso(matematica3);
        colegio.agregarCurso(ciencia1);
        colegio.agregarCurso(ciencia2);
        colegio.agregarCurso(ciencia3);
        colegio.agregarCurso(arte1);
        colegio.agregarCurso(arte2);
        colegio.agregarCurso(arte3);

        // Cada profesor queda a cargo de los cursos de su especialidad.
        colegio.asignarProfesorACurso(profesor1.obtenerId(), matematica1.obtenerId());
        colegio.asignarProfesorACurso(profesor1.obtenerId(), matematica2.obtenerId());
        colegio.asignarProfesorACurso(profesor1.obtenerId(), matematica3.obtenerId());

        colegio.asignarProfesorACurso(profesor2.obtenerId(), ciencia1.obtenerId());
        colegio.asignarProfesorACurso(profesor2.obtenerId(), ciencia2.obtenerId());
        colegio.asignarProfesorACurso(profesor2.obtenerId(), ciencia3.obtenerId());

        colegio.asignarProfesorACurso(profesor3.obtenerId(), arte1.obtenerId());
        colegio.asignarProfesorACurso(profesor3.obtenerId(), arte2.obtenerId());
        colegio.asignarProfesorACurso(profesor3.obtenerId(), arte3.obtenerId());

        // Inscripciones de ejemplo
        colegio.inscribirAlumnoEnCurso(alumno1.obtenerId(), matematica1.obtenerId());
        colegio.inscribirAlumnoEnCurso(alumno1.obtenerId(), ciencia1.obtenerId());
        colegio.inscribirAlumnoEnCurso(alumno1.obtenerId(), arte2.obtenerId());
        colegio.inscribirAlumnoEnCurso(alumno2.obtenerId(), matematica2.obtenerId());
        colegio.inscribirAlumnoEnCurso(alumno2.obtenerId(), ciencia2.obtenerId());
        colegio.inscribirAlumnoEnCurso(alumno3.obtenerId(), arte1.obtenerId());
        colegio.inscribirAlumnoEnCurso(alumno3.obtenerId(), matematica3.obtenerId());
        colegio.inscribirAlumnoEnCurso(alumno4.obtenerId(), ciencia1.obtenerId());
        colegio.inscribirAlumnoEnCurso(alumno4.obtenerId(), arte3.obtenerId());
        colegio.inscribirAlumnoEnCurso(alumno5.obtenerId(), ciencia3.obtenerId());

        colegio.registrarNota(alumno1.obtenerId(), 6.5);
        colegio.registrarNota(alumno1.obtenerId(), 5.8);
        colegio.registrarNota(alumno1.obtenerId(), 6.2);
        colegio.registrarNota(alumno2.obtenerId(), 4.9);
        colegio.registrarNota(alumno2.obtenerId(), 5.4);
        colegio.registrarNota(alumno3.obtenerId(), 6.8);
        colegio.registrarNota(alumno3.obtenerId(), 6.6);
        colegio.registrarNota(alumno4.obtenerId(), 5.2);
        colegio.registrarNota(alumno4.obtenerId(), 4.7);
        colegio.registrarNota(alumno5.obtenerId(), 6.0);

        System.out.println("Se cargaron 5 alumnos, 3 profesores, 9 cursos y datos de prueba.");
    }
}
