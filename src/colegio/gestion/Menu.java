package colegio.gestion;

import java.util.Scanner;

public class Menu {

    private final Colegio colegio;
    private final Scanner sc = new Scanner(System.in);

    public Menu(Colegio colegio) {
        this.colegio = colegio;
    }

    public void iniciar() {
    int opcion;
    do {
        mostrarMenu();
        opcion = leerOpcion();

        switch (opcion) {
            case 1 -> crearNuevoCurso();
            case 2 -> ingresarNuevoAlumno();
            case 3 -> ingresarNuevoProfesor();
            case 4 -> mostrarCursos();
            case 5 -> mostrarAlumnos();
            case 6 -> mostrarProfesores();
            case 7 -> gestionarCursos();
            case 8 -> buscarCursoPorId();
            case 9 -> buscarAlumnoPorId();
            case 10 -> buscarProfesorPorId();
            case 11 -> mostrarRanking();
            case 12 -> mostrarHistorial();
            case 0 -> {
                colegio.guardar();
                System.out.println("Saliendo.");
            }
            case -67 -> {} 
            default -> System.out.println("Opción no válida. Elija una opción del menú.");
        }
        System.out.println();
    } while (opcion != 0);
}

    private void mostrarMenu() {
    System.out.println("""
            ------------ MENÚ DEL COLEGIO ------------
            
            1. Crear un nuevo curso
            2. Ingresar un nuevo alumno
            3. Ingresar un nuevo profesor
            4. Mostrar cursos creados
            5. Mostrar alumnos ingresados
            6. Mostrar profesores ingresados
            7. Gestionar cursos ya creados
            8. Búsqueda de curso por ID
            9. Búsqueda de alumno por ID
            10. Búsqueda de profesor por ID
            11. Mostrar ranking de alumnos
            12. Mostrar historial
            
            0. Salir y guardar""");
}

    private int leerOpcion() {
    System.out.print("Ingrese una opción: ");
    try {
        return Integer.parseInt(sc.nextLine().trim());
    } catch (NumberFormatException error) {
        System.out.println("Dato inválido. Por favor, ingrese un número entero.");
        return -67;
    }
}

    public void crearNuevoCurso() {
        // Pendiente de implementar.
    }

    private void ingresarNuevoAlumno() {
        // Pendiente de implementar.
    }

    private void ingresarNuevoProfesor() {
        // Pendiente de implementar.
    }

    private void mostrarCursos() {
        // Pendiente de implementar.
    }

    private void mostrarAlumnos() {
        // Pendiente de implementar.
    }

    private void mostrarProfesores() {
        // Pendiente de implementar.
    }

    private void gestionarCursos() {
        // Pendiente de implementar.
    }

    private void buscarCursoPorId() {
        // Pendiente de implementar.
    }

    private void buscarAlumnoPorId() {
        // Pendiente de implementar.
    }

    private void buscarProfesorPorId() {
        // Pendiente de implementar.
    }

    private void mostrarRanking() {
        // Pendiente de implementar.
    }

    private void mostrarHistorial() {
        // Pendiente de implementar.
    }

    private void inscribirAlumnoEnCurso() {
        // Pendiente de implementar.
    }

    private void asignarProfesorACurso() {
        // Pendiente de implementar.
    }

    private void configurarPonderacion() {
        // Pendiente de implementar.
    }

    private void mostrarPonderacion() {
        // Pendiente de implementar.
    }

    private void registrarNota() {
        // Pendiente de implementar.
    }
}
