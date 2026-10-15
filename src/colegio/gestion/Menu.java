package colegio.gestion;

import colegio.enums.NivelCurso;
import colegio.excepciones.CupoLlenoException;
import colegio.excepciones.NotaInvalidaException;
import colegio.modelo.Alumno;
import colegio.modelo.Arte;
import colegio.modelo.Ciencia;
import colegio.modelo.Curso;
import colegio.modelo.Matematica;
import colegio.modelo.Profesor;
import java.util.Scanner;

// Menú interactivo sencillo basado en Scanner y nextLine().
public class Menu {
    private final Colegio colegio;
    private final Scanner sc;

    // Constructor original conservado. 
    public Menu(Colegio colegio) {
        if (colegio == null) throw new IllegalArgumentException("El colegio no puede ser nulo.");
        this.colegio = colegio;
        this.sc = new Scanner(System.in);
    }

    public Menu() { this(new Colegio()); }

    public void iniciar() {
        int opcion = -1;
        do {
            mostrarMenu();
            try {
                opcion = Integer.parseInt(leerTexto("Seleccione una opción: ").trim());
                procesarOpcion(opcion);
            } catch (FinEntrada e) {
                System.out.println("\nFin de la entrada. Guardando los datos antes de salir.");
                colegio.guardarDatos();
                return;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            } catch (IllegalArgumentException e) {
                System.out.println("No se pudo completar la operación: " + e.getMessage());
            }
        } while (opcion != 0);
        // El guardado también ocurre al seleccionar 0; repetirlo no perjudica los datos.
        colegio.guardarDatos();
        System.out.println("Hasta luego.");
    }

    private void mostrarMenu() {
        System.out.println("       SISTEMA DE GESTIÓN ESCOLAR");
        System.out.println(" 1. Listar cursos");
        System.out.println(" 2. Registrar curso");
        System.out.println(" 3. Buscar / ver detalle de curso");
        System.out.println(" 4. Listar alumnos");
        System.out.println(" 5. Registrar alumno");
        System.out.println(" 6. Ver ficha de alumno");
        System.out.println(" 7. Registrar nota");
        System.out.println(" 8. Inscribir alumno en curso");
        System.out.println(" 9. Registrar profesor");
        System.out.println("10. Listar profesores");
        System.out.println("11. Asignar profesor a curso");
        System.out.println("12. Configurar ponderación de curso");
        System.out.println("13. Ranking de alumnos");
        System.out.println("14. Ranking de cursos");
        System.out.println("15. Ver historial de operaciones");
        System.out.println(" 0. Guardar y salir");
        System.out.println("--------------------------------------");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> colegio.mostrarCursos();
            case 2 -> registrarCurso();
            case 3 -> colegio.mostrarCurso(leerTexto("ID o nombre del curso: "));
            case 4 -> colegio.mostrarAlumnos();
            case 5 -> registrarAlumno();
            case 6 -> colegio.mostrarFichaAlumno(leerTexto("ID o nombre del alumno: "));
            case 7 -> registrarNota();
            case 8 -> inscribirAlumno();
            case 9 -> registrarProfesor();
            case 10 -> colegio.mostrarProfesores();
            case 11 -> {
                colegio.asignarProfesorACurso(leerTexto("ID del curso: "), leerTexto("ID del profesor: "));
                System.out.println("Profesor asignado correctamente.");
            }
            case 12 -> configurarPonderacion();
            case 13 -> colegio.mostrarRanking();
            case 14 -> colegio.mostrarRankingCursos();
            case 15 -> colegio.mostrarHistorial();
            case 0 -> {
            }
            default -> System.out.println("Opción fuera de rango. Elija una opción del menú.");
        }
    }

    private void registrarCurso() {
        System.out.println("Tipo de curso: 1. Matemática  2. Ciencia  3. Arte");
        int tipo = leerEntero("Tipo: ", 1, 3);
        String nombre = leerNoVacio("Nombre del curso: ");
        int horas = leerEntero("Horas semanales: ", 1, 50);
        int cupo = leerEntero("Cupo máximo: ", 1, 500);
        int evaluaciones = leerEntero("Cantidad de evaluaciones: ", 1, 100);
        int tareas = leerEntero("Cantidad de tareas: ", 1, 100);
        NivelCurso nivel = leerNivel();

        Curso curso;
        curso = switch (tipo) {
            case 1 -> new Matematica(nombre, horas, cupo, evaluaciones, tareas, nivel);
            case 2 -> new Ciencia(nombre, horas, cupo, evaluaciones, tareas, nivel);
            default -> new Arte(nombre, horas, cupo, evaluaciones, tareas, nivel);
        };
        colegio.registrarCurso(curso);
        System.out.println("Curso registrado. ID asignado: " + curso.getId());
    }

    private void registrarAlumno() {
        String id = leerNoVacio("ID del alumno: ");
        String nombre = leerNoVacio("Nombre: ");
        String apellido = leerNoVacio("Apellido: ");
        int edad = leerEntero("Edad: ", 1, 120);
        String matricula = leerNoVacio("Matrícula: ");
        Alumno alumno = new Alumno(id, nombre, apellido, edad, matricula);
        colegio.registrarAlumno(alumno);
        System.out.println("Alumno registrado correctamente.");
    }

    private void registrarProfesor() {
        String id = leerNoVacio("ID del profesor: ");
        String nombre = leerNoVacio("Nombre: ");
        String apellido = leerNoVacio("Apellido: ");
        int edad = leerEntero("Edad: ", 18, 120);
        String especialidad = leerNoVacio("Especialidad: ");
        double salario = leerDecimal("Salario: ", 0, 1000000000);
        colegio.registrarProfesor(new Profesor(id, nombre, apellido, edad, especialidad, salario));
        System.out.println("Profesor registrado correctamente.");
    }

    private void registrarNota() {
        String id = leerNoVacio("ID del alumno: ");
        double nota = leerDecimal("Nota (1.0 a 7.0): ", 1.0, 7.0);
        try {
            colegio.registrarNota(id, nota);
            System.out.println("Nota registrada correctamente.");
        } catch (NotaInvalidaException e) {
            System.out.println("Nota inválida: " + e.getMessage());
        }
    }

    private void inscribirAlumno() {
        String idAlumno = leerNoVacio("ID del alumno: ");
        String idCurso = leerNoVacio("ID o nombre del curso: ");
        try {
            colegio.inscribirAlumnoEnCurso(idAlumno, idCurso);
            System.out.println("Alumno inscrito correctamente.");
        } catch (CupoLlenoException e) {
            System.out.println("No se pudo inscribir: " + e.getMessage());
        }
    }

    private void configurarPonderacion() {
        String idCurso = leerNoVacio("ID o nombre del curso: ");
        double evaluaciones = leerDecimal("Porcentaje total de evaluaciones: ", 0, 100);
        double tareas = leerDecimal("Porcentaje total de tareas: ", 0, 100);
        colegio.configurarPonderacion(idCurso, evaluaciones, tareas);
    }

    private NivelCurso leerNivel() {
        System.out.println("Nivel: 1. Básico  2. Intermedio  3. Avanzado");
        int opcion = leerEntero("Nivel: ", 1, 3);
        if (opcion == 1) return NivelCurso.BASICO;
        if (opcion == 3) return NivelCurso.AVANZADO;
        return NivelCurso.INTERMEDIO;
    }

    private int leerEntero(String mensaje, int minimo, int maximo) {
        while (true) {
            String texto = leerTexto(mensaje).trim();
            try {
                int valor = Integer.parseInt(texto);
                if (valor < minimo || valor > maximo) {
                    System.out.println("Ingrese un valor entre " + minimo + " y " + maximo + ".");
                } else return valor;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private double leerDecimal(String mensaje, double minimo, double maximo) {
        while (true) {
            String texto = leerTexto(mensaje).trim().replace(',', '.');
            try {
                double valor = Double.parseDouble(texto);
                if (Double.isNaN(valor) || Double.isInfinite(valor) || valor < minimo || valor > maximo) {
                    System.out.println("Ingrese un valor entre " + minimo + " y " + maximo + ".");
                } else return valor;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido; puede usar punto o coma decimal.");
            }
        }
    }

    private String leerNoVacio(String mensaje) {
        while (true) {
            String texto = leerTexto(mensaje).trim();
            if (!texto.isEmpty()) return texto;
            System.out.println("Este campo no puede quedar vacío.");
        }
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        if (!sc.hasNextLine()) throw new FinEntrada();
        return sc.nextLine();
    }

    private static class FinEntrada extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }
}
