package colegio.gestion;

import colegio.excepciones.CupoLlenoException;
import colegio.excepciones.NotaInvalidaException;
import colegio.interfaces.Rankeable;
import colegio.modelo.Alumno;
import colegio.modelo.Arte;
import colegio.modelo.Ciencia;
import colegio.modelo.Curso;
import colegio.modelo.Matematica;
import colegio.modelo.Operacion;
import colegio.modelo.Profesor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// [REQUISITO: Package] [REQUISITO: Menu-Scanner]
public class Menu {

    private final Colegio colegio;
    private final Scanner sc = new Scanner(System.in);

    public Menu(Colegio colegio) {
        this.colegio = colegio;
    }

    
    // ===== MENU PRINCIPAL =====

    // Inicia el menu y ejecuta la opcion que elija el usuario.
    public void iniciar() {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion();

            switch (opcion) {
                // Opcion 1: Crear un curso nuevo.
                case 1 -> crearNuevoCurso();
                // Opcion 2: Registrar un alumno.
                case 2 -> ingresarNuevoAlumno();
                // Opcion 3: Registrar un profesor.
                case 3 -> ingresarNuevoProfesor();
                // Opcion 4: Mostrar todos los cursos.
                case 4 -> mostrarCursos();
                // Opcion 5: Mostrar todos los alumnos.
                case 5 -> mostrarAlumnos();
                // Opcion 6: Mostrar todos los profesores.
                case 6 -> mostrarProfesores();
                // Opcion 7: Entrar a la gestion de cursos.
                case 7 -> gestionarCursos();
                // Opcion 8: Buscar un curso.
                case 8 -> buscarCursoPorId();
                // Opcion 9: Buscar un alumno.
                case 9 -> buscarAlumnoPorId();
                // Opcion 10: Buscar un profesor.
                case 10 -> buscarProfesorPorId();
                // Opcion 11: Mostrar el ranking.
                case 11 -> mostrarRanking();
                // Opcion 12: Mostrar el historial.
                case 12 -> mostrarHistorial();
                // Opcion 0: Guardar y salir.
                case 0 -> {
                    colegio.guardar();
                    System.out.println("Saliendo.");
                }
                case -1 -> {
                }
                default -> System.out.println("Opcion no valida. Elija una opcion del menu.");
            }
        } while (opcion != 0);
    }


    // Muestra el menu principal.
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
            8. Busqueda de curso por ID o nombre
            9. Busqueda de alumno por ID
            10. Busqueda de profesor por ID
            11. Mostrar ranking de alumnos
            12. Mostrar historial

            0. Salir y guardar""");
    }

    
    
    // ===== LECTURA DE DATOS =====

    // Lee un texto y evita que quede vacio.
    private String leerString(String mensaje) {
        while (true){
            System.out.println(mensaje);
            try {
                String contenido = sc.nextLine().trim();
                if (contenido.isEmpty()){
                    throw new IllegalArgumentException("No puede ingresar un espacio vacio");
                }

                else {
                    return contenido;
                }
            }
            catch (IllegalArgumentException e){
                System.out.println("ERROR " + e.getMessage());
            }
        }
    }


    // Lee una opcion del menu.
    private int leerOpcion() {
        System.out.print("Ingrese una opcion: ");
        try {
            return Integer.parseInt(sc.nextLine().trim()); // Convierte el texto ingresado a un numero entero.
        } catch (NumberFormatException error) {
            System.out.println("Dato invalido. Por favor, ingrese un numero entero.");
            return -1;
        }
    }

    // Lee un numero entero y revisa su valor minimo.
    private int leerEntero(String mensaje, int minimo) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(sc.nextLine().trim());
                if (valor < minimo) {
                    System.out.println("El valor debe ser igual o mayor que " + minimo);
                }
                else {
                    return valor;
                }
            }

            catch (NumberFormatException error) {
                System.out.println("Por favor ingrese un numero entero.");
            }
        }
    }

    // Lee un numero decimal y revisa su valor minimo.
    private double leerDecimal(String mensaje, double minimo) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = Double.parseDouble(sc.nextLine().trim());
                if (!Double.isFinite(valor)) {
                    System.out.println("Ingrese un numero decimal valido.");
                } else if (valor < minimo) {
                    System.out.println("El valor debe ser igual o mayor que " + minimo);
                } else {
                    return valor;
                }
            }

            catch (NumberFormatException error) {
                System.out.println("Por favor ingrese un numero decimal.");
            }
        }
    }



    // ===== CREACION DE CURSOS =====

    // Muestra las areas disponibles para crear un curso.
    private void crearNuevoCurso() {
        int opcion;
        do {
            System.out.println("///////BIENVENIDO A LA CREACION DE CURSOS///////");
            System.out.println("\n\n");
            System.out.println("Nuestro colegio trabaja solo con 3 materias distintas, las cuales son: Matematicas, Ciencias y Artes");
            System.out.println("\nPara empezar, elija una de las 3 materias");
            System.out.println("1. Matematicas");
            System.out.println("2. Ciencias");
            System.out.println("3. Artes");
            System.out.println("\n0. Volver al menu principal");
            opcion = leerOpcion();

            switch (opcion) {
                // Opcion 1: Crear un curso de matematica.
                case 1 -> cursoMatematica();
                // Opcion 2: Crear un curso de ciencias.
                case 2 -> cursoCiencia();
                // Opcion 3: Crear un curso de artes.
                case 3 -> cursoArte();

                // Opcion 0: Volver al menu principal.
                case 0 -> System.out.println("Volviendo al menu principal.\n");

                case -1 -> {}
                default -> System.out.println("Opcion no valida. Elija una opcion del menu.");
            }

        }

        while (opcion != 0);
    }

    // Crea un curso del area de matematica.
    private void cursoMatematica() {
        System.out.println("\n");
        String nombre = leerString("Ingrese el nombre de su Curso de Matematicas: ");
        System.out.println("""
                           AVISO: DEPENDIENDO DE LA CANTIDAD DE HORAS SEMANALES QUE ELIJA, SE ESCOGERA UN NIVEL PARA SU CURSO

                           1. Menor a 6 horas = CURSO BASICO
                           2. Mayor a 6 horas y menor a 12 horas = CURSO INTERMEDIO
                           3. Mayor o igual a 12 horas = CURSO AVANZADO
                           """);
        int horasSemanales = leerEntero("Ingrese las horas semanales de su Curso: ", 1);
        int cupoMaximo = leerEntero("Ingrese el cupo maximo de su curso: ", 1);
        int cantidadEvaluaciones = leerEntero("Ingrese la cantidad de evaluaciones que tendra su curso: ", 0);
        int cantidadTareas = leerEntero("Ingrese la cantidad de tareas que tendra su curso: ", 0);
        //Creacion del curso

        int confirmacion = confirmarOperacion();

        if (confirmacion == 1){
            Matematica cursoNuevo = new Matematica(nombre, horasSemanales, cupoMaximo, cantidadEvaluaciones, cantidadTareas);
            colegio.agregarCurso(cursoNuevo);
            System.out.println("Curso creado con exito\n");
        }
        else {
            System.out.println("Regresando al ultimo menu\n");
        }
    }

    // Crea un curso del area de ciencias.
    private void cursoCiencia() {
        System.out.println("\n");
        String nombre = leerString("Ingrese el nombre de su Curso de Ciencias: ");
        System.out.println("""
                           AVISO: DEPENDIENDO DE LA CANTIDAD DE HORAS SEMANALES QUE ELIJA, SE ESCOGERA UN NIVEL PARA SU CURSO

                           1. Menor a 6 horas = CURSO BASICO
                           2. Mayor a 6 horas y menor a 12 horas = CURSO INTERMEDIO
                           3. Mayor o igual a 12 horas = CURSO AVANZADO
                           """);
        int horasSemanales = leerEntero("Ingrese las horas semanales de su Curso: ", 1);
        int cupoMaximo = leerEntero("Ingrese el cupo maximo de su curso: ", 1);
        int cantidadEvaluaciones = leerEntero("Ingrese la cantidad de evaluaciones que tendra su curso: ", 0);
        int cantidadTareas = leerEntero("Ingrese la cantidad de tareas que tendra su curso: ", 0);
        //Creacion del curso
        int confirmacion = confirmarOperacion();

        if (confirmacion == 1){
            Ciencia cursoNuevo = new Ciencia(nombre, horasSemanales, cupoMaximo, cantidadEvaluaciones, cantidadTareas);
            colegio.agregarCurso(cursoNuevo);
            System.out.println("Curso creado con exito\n");
        }
        else {
            System.out.println("Regresando al ultimo menu\n");
        }
    }

    // Crea un curso del area de artes.
    private void cursoArte() {
        System.out.println("\n");
        String nombre = leerString("Ingrese el nombre de su Curso de Artes: ");
        System.out.println("""
                           AVISO: DEPENDIENDO DE LA CANTIDAD DE HORAS SEMANALES QUE ELIJA, SE ESCOGERA UN NIVEL PARA SU CURSO

                           1. Menor a 6 horas = CURSO BASICO
                           2. Mayor a 6 horas y menor a 12 horas = CURSO INTERMEDIO
                           3. Mayor o igual a 12 horas = CURSO AVANZADO
                           """);
        int horasSemanales = leerEntero("Ingrese las horas semanales de su Curso: ", 1);
        int cupoMaximo = leerEntero("Ingrese el cupo maximo de su curso: ", 1);
        int cantidadEvaluaciones = leerEntero("Ingrese la cantidad de evaluaciones que tendra su curso: ", 0);
        int cantidadTareas = leerEntero("Ingrese la cantidad de tareas que tendra su curso: ", 0);
        //Creacion del curso
        int confirmacion = confirmarOperacion();

        if (confirmacion == 1){
            Arte cursoNuevo = new Arte(nombre, horasSemanales, cupoMaximo, cantidadEvaluaciones, cantidadTareas);
            colegio.agregarCurso(cursoNuevo);
            System.out.println("Curso creado con exito\n");
        }
        else {
            System.out.println("Regresando al ultimo menu\n");
        }
    }

    
    
    // ===== REGISTRO DE PERSONAS =====

    // Registra un alumno nuevo.
    private void ingresarNuevoAlumno() {
        String nombre = leerString("Ingrese el nombre del Alumno");
        String apellido = leerString("Ingrese el apellido del Alumno");

        int confirmacion = confirmarOperacion();

        if (confirmacion == 1){
            Alumno nuevoAlumno = new Alumno(nombre, apellido);
            colegio.agregarAlumno(nuevoAlumno);
        }
        else {
            System.out.println("Regresando al ultimo menu\n");
        }
    }

    // Registra un profesor nuevo.
    private void ingresarNuevoProfesor() {
        String nombre = leerString("Ingrese el nombre del Profesor");
        String apellido = leerString("Ingrese el apellido del Profesor");
        String especialidad = leerString("Ingrese la especialidad del Profesor");

        int confirmacion = confirmarOperacion();

        if (confirmacion == 1){
            Profesor nuevoProfesor = new Profesor(nombre, apellido, especialidad);
            colegio.agregarProfesor(nuevoProfesor);
            System.out.println("Profesor ingresado correctamente");
        }
        else {
            System.out.println("Regresando al ultimo menu\n");
        }
    }

    
    
    // ===== LISTADOS =====

    // Muestra los cursos ordenados por su ID.
    private void mostrarCursos() {
        System.out.println("-------Lista de Cursos creados (ordenados por ID)-------\n");
        System.out.println("ID / AREA / NOMBRE / HORAS / CUPO / EVALUACIONES / TAREAS / NIVEL");
        //Variable local para guardar la lista de todos los cursos creados
        ArrayList<Curso> listaCursos = colegio.getCursos();
        Collections.sort(listaCursos); // Curso.compareTo ordena por el numero del ID.

        // La misma variable Curso ejecuta la implementacion de cada subtipo.
        for (Curso curso : listaCursos) {
            System.out.println("AREA: " + curso.obtenerClasificacionCurso());
            curso.mostrarInformacion();

            // Muestra el profesor asignado a este curso.
            Profesor profesor = curso.getProfesor();
            if (profesor == null) {
                System.out.println("Profesor: Sin asignar");
            } else {
                System.out.println("Profesor: " + profesor.getNombre() + " " + profesor.getApellido());
            }
        }
        System.out.println("\n");
    }

    // Muestra todos los alumnos registrados.
    private void mostrarAlumnos() {
        System.out.println("-------Lista de Alumnos ingresados-------");
        System.out.println("ID/NOMBRE/APELLIDO/PROMEDIO\n");
        //Variable local para guardar la lista de todos los alumnos ingresados
        ArrayList<Alumno> listaAlumnos = colegio.getAlumnos();
        //Ciclo para mostrar la lista de alumnos
        for (int i = 0; i < listaAlumnos.size(); i++){
            listaAlumnos.get(i).mostrarInformacion();
        }
        System.out.println("\n");
    }

    // Muestra profesores y si tienen cursos asignados.
    private void mostrarProfesores() {
        System.out.println("-------Lista de Profesores ingresados-------");
        System.out.println("ID/NOMBRE/APELLIDO/ESPECIALIDAD\n");
        //Variable local para guardar la lista de todos los profesores ingresados
        ArrayList<Profesor> listaProfesores = colegio.getProfesores();
        ArrayList<Curso> listaCursos = colegio.getCursos();

        //Ciclo para mostrar los profesores y si tienen algun curso asignado
        for (Profesor profesor : listaProfesores) {
            profesor.mostrarInformacion();

            int cursos_asignados = 0;
            for (Curso curso : listaCursos) {
                if (curso.getProfesor() != null
                && curso.getProfesor().obtenerId().equals(profesor.obtenerId())) {
                    cursos_asignados++;
                }
            }

            System.out.println("Tiene " + cursos_asignados + " cursos asignados.");
        }
        System.out.println("\n");
    }
    
    

    // ===== GESTION DE CURSOS =====

    // Muestra las acciones disponibles para los cursos.
    private void gestionarCursos() {

        int opcion;
        do {
            System.out.println("""
                            ///////BIENVENIDO A LA GESTION DE CURSOS///////

                            Aqui podra gestionar los cursos ya creados en el colegio """);

            System.out.println("---Lista de Cursos creados---");
            System.out.println("ID      NOMBRE");
            ArrayList<Curso> listaCursos = colegio.getCursos();

            //Ciclo para mostrar la lista de cursos
            for (int i = 0; i < listaCursos.size(); i++){
                String cursoId = listaCursos.get(i).getId();
                String nombreCurso = listaCursos.get(i).getNombre();
                System.out.println(cursoId + " " + nombreCurso);
            }
            System.out.println("\n");
            System.out.println("1. Asignar profesor a un curso");
            System.out.println("2. Inscribir un alumno a un curso");
            System.out.println("3. Registrar nota de un alumno");
            System.out.println("4. Mostrar notas de un alumno");
            System.out.println("\n");
            System.out.println("0. Volver al menu principal");

            opcion = leerOpcion();

            switch (opcion) {
                // Opcion 1: Asignar un profesor.
                case 1 -> asignarProfesorACurso();
                // Opcion 2: Inscribir un alumno.
                case 2 -> inscribirAlumnoEnCurso();
                // Opcion 3: Registrar una nota.
                case 3 -> registrarNota();
                // Opcion 4: Consultar las notas de un alumno.
                case 4 -> notasAlumno();

                // Opcion 0: Volver al menu principal.
                case 0 -> System.out.println("Volviendo al menu principal.\n");

                case -1 -> {}
                default -> System.out.println("Opcion invalida. Elija una opcion del menu.\n");
            }

        }

        while (opcion != 0);
    }

    
    
    
    // ===== BUSQUEDAS Y CONSULTAS =====

    // Busca un curso por ID o por nombre.
    private void buscarCursoPorId() {
        System.out.println("Ingrese el ID o el nombre del curso:");
        String dato = sc.nextLine().trim();
        // Primero se intenta buscar por ID y, si no existe, por nombre.
        Curso cursoEncontrado = colegio.buscarCursoPorId(dato);
        if (cursoEncontrado == null) {
            cursoEncontrado = colegio.buscarCursoPorNombre(dato);
        }

        if (cursoEncontrado == null){
            System.out.println("No se encontro ningun curso con ese ID o nombre.\n");
        }
        else {
            System.out.println("Curso encontrado\n");
            cursoEncontrado.mostrarInformacion();
        }
    }

    // Busca un alumno por su ID y muestra sus datos.
    private void buscarAlumnoPorId() {
        System.out.println("Ingrese un ID de un alumno para buscar  (EJEMPLO: A3)");
        String id = sc.nextLine();
        //Variable local para guardar el alumno encontrado
        Alumno alumnoEncontrado = colegio.buscarAlumnoPorId(id);

        if (alumnoEncontrado == null){
            System.out.println("No se encontro ningun alumno con esa ID\n");
        }
        else {
            System.out.println("Alumno encontrado\n");
            mostrarPerfilAlumno(alumnoEncontrado);
        }
    }

    // Muestra las notas, cursos e historial de un alumno.
    private void mostrarPerfilAlumno(Alumno alumno) {
        System.out.println("------- PERFIL DEL ALUMNO -------");
        alumno.mostrarInformacion();

        ArrayList<Double> notas = alumno.getNotas();
        if (notas.isEmpty()) {
            System.out.println("Notas: todavia no tiene calificaciones.");
        } else {
            System.out.println("Notas: " + notas);
            System.out.println("Promedio: " + alumno.getPromedioNotas());
        }

        ArrayList<Curso> cursosAlumno = colegio.obtenerCursosAlumno(alumno.obtenerId());
        System.out.println("Cursos inscritos:");
        if (cursosAlumno.isEmpty()) {
            System.out.println("No esta inscrito en ningun curso.");
        } else {
            for (Curso curso : cursosAlumno) {
                System.out.println(curso.getId() + " / " + curso.getNombre());
            }
        }

        ArrayList<Operacion> historialAlumno = colegio.obtenerHistorialAlumno(alumno.obtenerId());
        System.out.println("Historial de operaciones:");
        if (historialAlumno.isEmpty()) {
            System.out.println("No tiene operaciones registradas.");
        } else {
            for (int i = 0; i < historialAlumno.size(); i++) {
                System.out.println((i + 1) + ". " + historialAlumno.get(i));
            }
        }
        System.out.println();
    }

    // Busca un profesor por su ID.
    private void buscarProfesorPorId() {
        System.out.println("Ingrese un ID de un profesor para buscar  (EJEMPLO: P2)");
        String id = sc.nextLine();
        //Variable local para guardar el profesor encontrado
        Profesor profesorEncontrado = colegio.buscarProfesorPorId(id);

        if (profesorEncontrado == null){
            System.out.println("No se encontro ningun profesor con esa ID\n");
        }
        else {
            System.out.println("Profesor encontrado\n");
            profesorEncontrado.mostrarInformacion();
        }
    }

    // Muestra los alumnos ordenados por promedio.
    private void mostrarRanking() {
        ArrayList<Alumno> ranking = colegio.obtenerRankingAlumnos();
        System.out.println("ALUMNO            PROMEDIO DE NOTAS");

        for (Alumno alumno : ranking) {
            Rankeable elemento = alumno;
            System.out.println(alumno.getNombre() + " " + alumno.getApellido()
            + " / " + elemento.obtenerPuntajeRanking());
        }
        System.out.println("\n");

    }

    // Muestra las operaciones realizadas en el colegio.
    private void mostrarHistorial() {
        ArrayList<Operacion> historial = colegio.getHistorial();
        System.out.println("------- HISTORIAL DE OPERACIONES -------");
        if (historial.isEmpty()) {
            System.out.println("No hay un historial de operaciones.");
            return;
        }
        for (int i = 0; i < historial.size(); i++) {
            System.out.println((i + 1) + ". " + historial.get(i));
        }
    }



    // ===== INSCRIPCIONES Y NOTAS =====

    // Inscribe un alumno en un curso.
    private void inscribirAlumnoEnCurso() {
        System.out.println("""
                ------- INSCRIPCION DE UN ALUMNO A UN CURSO -------
                Para asignar un alumno a un curso debe ingresar el ID del alumno y tambien debe seleccionar el ID del curso
                           """);
        System.out.println("ID Alumnos");
        ArrayList<Alumno> listaAlumnos = colegio.getAlumnos();

        //Ciclo para mostrar la lista de Alumnos
        for (int i = 0; i < listaAlumnos.size(); i++){
            String alumnoId = listaAlumnos.get(i).obtenerId();
            String nombreAlumno = listaAlumnos.get(i).getNombre();
            System.out.println(alumnoId + " " + nombreAlumno);
        }

        System.out.println("Ingrese el ID del Alumno");
        String idAlumno = sc.nextLine();
        System.out.println("Ingrese el ID del curso");
        String cursoId = sc.nextLine();

        int confirmacion = confirmarOperacion();

        if (confirmacion == 1){
            try {
                colegio.inscribirAlumnoEnCurso(idAlumno, cursoId);
                System.out.println("El alumno fue ingresado con exito\n");
            }
            catch (CupoLlenoException | IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage() + "\n");
            }
        }
        else {
            System.out.println("Regresando al ultimo menu\n");
        }
    }

    // Asigna un profesor a un curso.
    private void asignarProfesorACurso() {
        System.out.println("""
                ------- ASIGNACION DE UN PROFESOR A UN CURSO -------
                Para asignar un profesor a un curso debe ingresar el ID del profesor y tambien debe seleccionar el ID del curso

                AVISO: SOLO SE PUEDE INGRESAR 1 PROFESOR A CADA CURSO
                           """);
        System.out.println("ID Profesores");
        ArrayList<Profesor> listaProfesores = colegio.getProfesores();

        //Ciclo para mostrar la lista de profesores
        for (int i = 0; i < listaProfesores.size(); i++){
            String profesorId = listaProfesores.get(i).obtenerId();
            String nombreProfesor = listaProfesores.get(i).getNombre();
            System.out.println(profesorId + " " + nombreProfesor);
        }

        System.out.println("Ingrese el ID del profesor");
        String idProfesor = sc.nextLine();
        System.out.println("Ingrese el ID del curso");
        String cursoId = sc.nextLine();

        int confirmacion = confirmarOperacion();

        if (confirmacion == 1){
            try {
                colegio.asignarProfesorACurso(idProfesor, cursoId);
                System.out.println("El profesor fue asignado con exito");
            }
            catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage() + "\n");
            }
        }
        else {
            System.out.println("Regresando al ultimo menu\n");
        }
    }


    // Permite revisar las notas de un alumno.
    private void notasAlumno() {
        System.out.println("Ingrese el ID del alumno para continuar");

        ArrayList<Alumno> listaAlumnos = colegio.getAlumnos();

        //Ciclo para mostrar la lista de Alumnos
        for (int i = 0; i < listaAlumnos.size(); i++){
            String alumnoId = listaAlumnos.get(i).obtenerId();
            String nombreAlumno = listaAlumnos.get(i).getNombre();
            System.out.println(alumnoId + " " + nombreAlumno);
        }

        System.out.println("Ingrese el ID del Alumno");
        String idAlumno = sc.nextLine();
        Alumno alumnoEncontrado = colegio.buscarAlumnoPorId(idAlumno);

        if (alumnoEncontrado == null){
            System.out.println("Alumno no encontrado");
            System.out.println("Regresando al ultimo menu \n");
        }

        else {
            System.out.println("Alumno Encontrado");
            System.out.println(alumnoEncontrado.getNombre() + " " + alumnoEncontrado.getApellido() + "\n");
            int opcion;
            do {

                System.out.println("""
                                   GESTION DE NOTAS DEL ALUMNO

                                   1.Mostrar promedio de notas del alumno
                                   2.Mostrar la lista de notas del alumno

                                   0.Salir al ultimo menu

                                   """);
                opcion = leerOpcion();

                switch (opcion) {
                    // Opcion 1: Mostrar el promedio.
                    case 1 -> promedioNotasAlumno(alumnoEncontrado);
                    // Opcion 2: Mostrar la lista de notas.
                    case 2 -> listaNotasAlumno(alumnoEncontrado);

                    // Opcion 0: Volver al menu anterior.
                    case 0 -> System.out.println("Volviendo al ultimo menu.\n");

                    case -1 -> {}
                    default -> System.out.println("Opcion invalida. Elija una opcion del menu.\n");
                }
            }

            while (opcion != 0);

        }
    }

    // Muestra las notas que tiene registradas un alumno.
    private void listaNotasAlumno(Alumno alumnoEncontrado) {
        ArrayList<Double> notas = alumnoEncontrado.getNotas();
        if (notas.isEmpty()){
            System.out.println("El alumno no tiene notas\n");
        }

        else {
            System.out.println("----Lista de notas----");
            for (int i = 0; i < notas.size(); i++){
                double nota = notas.get(i);
                System.out.println(nota + "\n");

            }
        }
    }

    // Muestra el promedio de un alumno.
    private void promedioNotasAlumno(Alumno alumnoEncontrado) {
        if (alumnoEncontrado.getNotas().isEmpty()){
            System.out.println("El alumno no tiene notas, por tanto no hay promedio\n");
        }
        else {
            System.out.println("El promedio de notas del alumno es: " + alumnoEncontrado.getPromedioNotas() + "\n" );
        }
    }

    // Registra una nota para un alumno.
    private void registrarNota() {
        System.out.println("""
               ------- REGISTRO DE NOTA DE UN ALUMNO -------
                Para registrar una nota de un alumno en particular, tendra que colocar su ID y la nota que desea ingresar

                AVISO: LA NOTA MINIMA ES UN 1.0 Y LA NOTA MAXIMA ES UN 7.0
                           """);

        ArrayList<Alumno> listaAlumnos = colegio.getAlumnos();

        //Ciclo para mostrar la lista de Alumnos
        for (int i = 0; i < listaAlumnos.size(); i++){
            String alumnoId = listaAlumnos.get(i).obtenerId();
            String nombreAlumno = listaAlumnos.get(i).getNombre();
            System.out.println(alumnoId + " " + nombreAlumno);
        }

        System.out.println("Ingrese el ID del alumno:");
        String idAlumno = sc.nextLine();
        double nota = leerDecimal("""
                                  Ingrese la nota que desea ingresar (Si es un numero decimal, coloque un . para separar)
                                  EJEMPLO = 5.8
                                  """, 1.0);

        try {
            colegio.registrarNota(idAlumno, nota);
            System.out.println("La nota del alumno fue asignada con exito");
        }
        catch (NotaInvalidaException | IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage() + "\n");
        }
    }

    
    
    // ===== CONFIRMACION DE ACCIONES =====

    // Pide confirmar o cancelar la operacion.
    private int confirmarOperacion() {
        int opcion;
        do {
            System.out.println("""
                           Confirme la operacion:

                           1.Si
                           2.No""");
            opcion = leerOpcion();


            switch (opcion) {
                // Opcion 1: Confirmar la operacion.
                case 1 -> {System.out.println("Decidio continuar con el proceso\n");}
                // Opcion 2: Cancelar la operacion.
                case 2 -> {System.out.println("Decidio cancelar el proceso\n");}
                case -1 -> {}
                default -> System.out.println("Opcion no valida. Elija una opcion del menu.\n");
            }
        }

        while (opcion != 1 && opcion != 2);

        return opcion;
    }
}
