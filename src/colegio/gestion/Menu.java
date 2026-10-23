package colegio.gestion;

import colegio.modelo.Profesor;
import colegio.modelo.Alumno;
import colegio.modelo.Curso;
import colegio.modelo.Matematica;
import colegio.modelo.Arte;
import colegio.modelo.Ciencia;
import colegio.modelo.Operacion;
import java.util.ArrayList;



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

        //Cada opción que elija el usuario lo llevará a un método específico
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
            case -1 -> {} //En caso de que el usuario coloque un dato que no es número entero 
            default -> System.out.println("Opción no válida. Elija una opción del menú."); //En caso de que el usuario coloque un número entero inválido
        }
    } 
    while (opcion != 0);
    }

    private void mostrarMenu() {
    System.out.println("""
            ------------ MENU DEL COLEGIO ------------
            
            1. Crear un nuevo curso
            2. Ingresar un nuevo alumno
            3. Ingresar un nuevo profesor
            4. Mostrar cursos creados
            5. Mostrar alumnos ingresados
            6. Mostrar profesores ingresados
            7. Gestionar cursos ya creados
            8. Busqueda de curso por ID
            9. Busqueda de alumno por ID
            10. Busqueda de profesor por ID
            11. Mostrar ranking de alumnos
            12. Mostrar historial
            
            0. Salir y guardar""");
}
    //Método para pedirle una opción del Menú al usuario
    private int leerOpcion() {
    System.out.print("Ingrese una opcion: ");
    try {
        return Integer.parseInt(sc.nextLine().trim()); //Te devuelve tu string en un número entero y quitandole los espacios, si es que los tiene
    } catch (NumberFormatException error) {
        System.out.println("Dato invalido. Por favor, ingrese un numero entero.");
        return -1;
    }
}

    private int leerEntero(String mensaje, int minimo) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(sc.nextLine().trim());
                if (valor < minimo) {
                    System.out.println("El valor debe ser igual o mayor que " + minimo);
                } 
            else{
                return valor;
                }
            }
            
            catch (NumberFormatException error) {
                System.out.println("Entrada inválida. Ingrese un número entero.");
            }
        }
    }

    private void crearNuevoCurso() {
        System.out.println("///////BIENVENIDO A LA CREACION DE CURSOS///////");
        System.out.println("\n\n");
        System.out.println("Nuestro colegio trabaja solo con 3 materias distintas, las cuales son: Matematicas, Ciencias y Artes");
        System.out.println("\nPara empezar, elija una de las 3 materias");
        System.out.println("1. Matematicas");
        System.out.println("2. Ciencias");
        System.out.println("3. Artes");
        System.out.println("\n0. Volver al menú principal");
        int opcion;
        do{
            opcion = leerOpcion();
            
            switch(opcion){    
            case 1 -> cursoMatematica();
            case 2 -> cursoCiencia();
            case 3 -> cursoArte();
            
            case 0 -> System.out.println("Volviendo al menú principal.");
            
            case -1 -> {} //En caso de que el usuario coloque un dato que no es número entero 
            default -> System.out.println("Opción no válida. Elija una opción del menú."); //En caso de que el usuario coloque un número entero inválido
            }
            
        }
        
       while (opcion != 0);       
    }
    
    private void cursoMatematica(){
        System.out.println("\n");
        System.out.println("Ingrese el nombre de su Curso de Matemáticas: ");
        String nombre = sc.nextLine();
        int horasSemanales = leerEntero("Ingrese las horas semanales de su Curso: ", 1);
        int cupoMaximo = leerEntero("Ingrese el cupo máximo de su curso: ", 1);
        int cantidadEvaluaciones = leerEntero("Ingrese la cantidad de evaluaciones que tendrá su curso: ", 0);
        int cantidadTareas = leerEntero("Ingrese la cantidad de tareas que tendrá su curso: ", 0);
        //Creación del curso
        Matematica cursoNuevo = new Matematica(nombre, horasSemanales, cupoMaximo, cantidadEvaluaciones, cantidadTareas);
        colegio.agregarCurso(cursoNuevo);  
    }

    private void cursoCiencia(){
        System.out.println("\n");
        System.out.println("Ingrese el nombre de su Curso de Ciencias: ");
        String nombre = sc.nextLine();
        int horasSemanales = leerEntero("Ingrese las horas semanales de su Curso: ", 1);
        int cupoMaximo = leerEntero("Ingrese el cupo máximo de su curso: ", 1);
        int cantidadEvaluaciones = leerEntero("Ingrese la cantidad de evaluaciones que tendrá su curso: ", 0);
        int cantidadTareas = leerEntero("Ingrese la cantidad de tareas que tendrá su curso: ", 0);
        //Creación del curso
        Ciencia cursoNuevo = new Ciencia(nombre, horasSemanales, cupoMaximo, cantidadEvaluaciones, cantidadTareas);
        colegio.agregarCurso(cursoNuevo);  
    }

    private void cursoArte(){
        System.out.println("\n");
        System.out.println("Ingrese el nombre de su Curso de Artes: ");
        String nombre = sc.nextLine();
        int horasSemanales = leerEntero("Ingrese las horas semanales de su Curso: ", 1);
        int cupoMaximo = leerEntero("Ingrese el cupo máximo de su curso: ", 1);
        int cantidadEvaluaciones = leerEntero("Ingrese la cantidad de evaluaciones que tendrá su curso: ", 0);
        int cantidadTareas = leerEntero("Ingrese la cantidad de tareas que tendrá su curso: ", 0);
        //Creación del curso
        Arte cursoNuevo = new Arte(nombre, horasSemanales, cupoMaximo, cantidadEvaluaciones, cantidadTareas);
        colegio.agregarCurso(cursoNuevo);  
    }
    
    private void ingresarNuevoAlumno() {
        System.out.println("Ingrese el nombre del Alumno");
        String nombre = sc.nextLine();
        System.out.println("Ingrese el apellido del Alumno");
        String apellido = sc.nextLine();
        
        Alumno nuevoAlumno = new Alumno(nombre, apellido);
        colegio.agregarAlumno(nuevoAlumno);
    }

    private void ingresarNuevoProfesor() {
        System.out.println("Ingrese el nombre del Profesor");
        String nombre = sc.nextLine();
        System.out.println("Ingrese el apellido del Profesor");
        String apellido = sc.nextLine();
        System.out.println("Ingrese la especialidad del Profesor");
        String especialidad = sc.nextLine();
        
        Profesor nuevoProfesor = new Profesor(nombre, apellido, especialidad);
        colegio.agregarProfesor(nuevoProfesor);
    }

    private void mostrarCursos() {
        System.out.println("-------Lista de Cursos creados-------");
        System.out.println("ID/NOMBRE/HORAS SEMANALES/CUPO MAXIMO/CANTIDAD EVALUACIONES/CANTIDAD TAREAS");
        //Variable local para guardar la lista de todos los cursos creados
        ArrayList<Curso> listaCursos = colegio.getCursos();
        //Ciclo para mostrar la lista de cursos
        for(int i = 0; i < listaCursos.size(); i++){
            listaCursos.get(i).mostrarInformacion();
        }
        System.out.println("\n");
    }

    private void mostrarAlumnos() {
        System.out.println("-------Lista de Alumnos ingresados-------");
        System.out.println("ID/NOMBRE/APELLIDO/");
        //Variable local para guardar la lista de todos los alumnos ingresados
        ArrayList<Alumno> listaAlumnos = colegio.getAlumnos();
        //Ciclo para mostrar la lista de alumnos
        for(int i = 0; i < listaAlumnos.size(); i++){
            listaAlumnos.get(i).mostrarInformacion();
        }
        System.out.println("\n");
    }

    private void mostrarProfesores() {
        System.out.println("-------Lista de Prfesores ingresados-------");
        System.out.println("ID/NOMBRE/APELLIDO/ESPECIALIDAD");
        //Variable local para guardar la lista de todos los profesores ingresados
        ArrayList<Profesor> listaProfesores = colegio.getProfesores();
        //Ciclo para mostrar la lista de profesores
        for(int i = 0; i < listaProfesores.size(); i++){
            listaProfesores.get(i).mostrarInformacion();
        }
        System.out.println("\n");
    }
    
    // Falta implementar
    private void gestionarCursos() {
        //Implentar código
    }

    private void buscarCursoPorId() {
        System.out.println("Ingrese un ID de un curso para buscar  (EJEMPLO: (CUR-3)");
        String id = sc.nextLine();
        //Variable local para guardar el curso encontrado
        Curso cursoEncontrado = colegio.buscarCursoPorId(id);
        
        if(cursoEncontrado == null){
            System.out.println("No se encontró ningún curso con esa ID");
        }
        else{
            System.out.println("Curso encontrado");
            cursoEncontrado.mostrarInformacion();
        } 
    }

    private void buscarAlumnoPorId() {
        System.out.println("Ingrese un ID de un alumno para buscar  (EJEMPLO: (ALU-3)");
        String id = sc.nextLine();
        //Variable local para guardar el alumno encontrado
        Alumno alumnoEncontrado = colegio.buscarAlumnoPorId(id);
        
        if(alumnoEncontrado == null){
            System.out.println("No se encontró ningún alumno con esa ID");
        }
        else{
            System.out.println("Alumno encontrado");
            alumnoEncontrado.mostrarInformacion();
        } 
    }

    private void buscarProfesorPorId() {
        System.out.println("Ingrese un ID de un profesor para buscar");
        String id = sc.nextLine();
        //Variable local para guardar el profesor encontrado
        Profesor profesorEncontrado = colegio.buscarProfesorPorId(id);
        
        if(profesorEncontrado == null){
            System.out.println("No se encontró ningún profesor con esa ID");
        }
        else{
            System.out.println("Profesor encontrado");
            profesorEncontrado.mostrarInformacion();
        } 
    }

    // Falta implementar
    private void mostrarRanking() {
        // Falta implementar
        }
    
    private void mostrarHistorial() {
        ArrayList<Operacion> historial = colegio.getHistorial();
        System.out.println("------- HISTORIAL DE OPERACIONES -------");
        if (historial.isEmpty()) {
            System.out.println("No hay operaciones registradas.");
            return;
        }
        for (int i = 0; i < historial.size(); i++) {
            System.out.println((i + 1) + ". " + historial.get(i));
        }
    }
    
    

    private void inscribirAlumnoEnCurso(Curso curso) {
        //Código
    }

    private void asignarProfesorACurso(Curso curso) {
        //Código
    }

    private void configurarPonderacion(Curso curso) {
        //Código
    }

    private void mostrarPonderacion(Curso curso) {
        curso.informacionPonderacion();
    }

    private void registrarNota(Curso curso) {
        //Código
    }
}
