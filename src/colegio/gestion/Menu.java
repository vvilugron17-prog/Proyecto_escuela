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
            default -> System.out.println("Opción no válida. Elija una opción del menu."); //En caso de que el usuario coloque un número entero inválido
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
    
    //Método para pedirle un String a el usuario
    private String leerString(String mensaje){
        while (true){
            System.out.println(mensaje);
            try{
                String contenido = sc.nextLine().trim();
                if(contenido.isEmpty()){
                    throw new IllegalArgumentException("No puede ingresar un espacio vacio");
                }
                
                else{ 
                    return contenido;
                }
            }
            catch (IllegalArgumentException e){
                System.out.println("ERROR " + e.getMessage());
            }
        }
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
    
    //Método para asegurarse de que el usuario ingrese un número entero
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
                System.out.println("Por favor ingrese un número entero.");
            }
        }
    }
    
    //Método para asegurarse de que el usuario ingrese un número entero
    private double leerDecimal(String mensaje, double minimo) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = Double.parseDouble(sc.nextLine().trim());
                if (valor < minimo) {
                    System.out.println("El valor debe ser igual o mayor que " + minimo);
                } 
                else{
                return valor;
                }
            }
            
            catch (NumberFormatException error) {
                System.out.println("Por favor ingrese un número decimal.");
            }
        }
    }
    
    


    private void crearNuevoCurso() {
        int opcion;
        do{
            System.out.println("///////BIENVENIDO A LA CREACION DE CURSOS///////");
            System.out.println("\n\n");
            System.out.println("Nuestro colegio trabaja solo con 3 materias distintas, las cuales son: Matematicas, Ciencias y Artes");
            System.out.println("\nPara empezar, elija una de las 3 materias");
            System.out.println("1. Matematicas");
            System.out.println("2. Ciencias");
            System.out.println("3. Artes");
            System.out.println("\n0. Volver al menu principal");
            opcion = leerOpcion();
            
            switch(opcion){    
            case 1 -> cursoMatematica();
            case 2 -> cursoCiencia();
            case 3 -> cursoArte();
            
            case 0 -> System.out.println("Volviendo al menú principal.\n");
            
            case -1 -> {} //En caso de que el usuario coloque un dato que no es número entero 
            default -> System.out.println("Opción no válida. Elija una opción del menú."); //En caso de que el usuario coloque un número entero inválido
            }
            
        }
        
       while (opcion != 0);       
    }
    
    private void cursoMatematica(){
        System.out.println("\n");
        String nombre = leerString("Ingrese el nombre de su Curso de Matemáticas: ");
        System.out.println("""
                           AVISO: DEPENDIENDO DE LA CANTIDAD DE HORAS SEMANALES QUE ELIJA, SE ESCOGERÁ UN NIVEL PARA SU CURSO
                           
                           1. Menor a 6 horas = CURSO BASICO
                           2. Mayor a 6 horas y menor a 12 horas = CURSO INTERMEDIO
                           3. Mayor o igual a 12 horas = CURSO AVANZADO
                           """);
        int horasSemanales = leerEntero("Ingrese las horas semanales de su Curso: ", 1);
        int cupoMaximo = leerEntero("Ingrese el cupo máximo de su curso: ", 1);
        int cantidadEvaluaciones = leerEntero("Ingrese la cantidad de evaluaciones que tendrá su curso: ", 0);
        int cantidadTareas = leerEntero("Ingrese la cantidad de tareas que tendrá su curso: ", 0);
        //Creación del curso
        
        int preguntaSeguridad = opcionDeCancelado();
        
        if(preguntaSeguridad == 1){
            Matematica cursoNuevo = new Matematica(nombre, horasSemanales, cupoMaximo, cantidadEvaluaciones, cantidadTareas);
            colegio.agregarCurso(cursoNuevo);  
            System.out.println("Curso creado con éxito\n");
        }
        else{
            System.out.println("Regresando al último menú\n");
        }
    }

    private void cursoCiencia(){
        System.out.println("\n");
        String nombre = leerString("Ingrese el nombre de su Curso de Ciencias: ");
        System.out.println("""
                           AVISO: DEPENDIENDO DE LA CANTIDAD DE HORAS SEMANALES QUE ELIJA, SE ESCOGERÁ UN NIVEL PARA SU CURSO
                           
                           1. Menor a 6 horas = CURSO BASICO
                           2. Mayor a 6 horas y menor a 12 horas = CURSO INTERMEDIO
                           3. Mayor o igual a 12 horas = CURSO AVANZADO
                           """);        
        int horasSemanales = leerEntero("Ingrese las horas semanales de su Curso: ", 1);
        int cupoMaximo = leerEntero("Ingrese el cupo máximo de su curso: ", 1);
        int cantidadEvaluaciones = leerEntero("Ingrese la cantidad de evaluaciones que tendrá su curso: ", 0);
        int cantidadTareas = leerEntero("Ingrese la cantidad de tareas que tendrá su curso: ", 0);
        //Creación del curso
        int preguntaSeguridad = opcionDeCancelado();
        
        if(preguntaSeguridad == 1){
            Ciencia cursoNuevo = new Ciencia(nombre, horasSemanales, cupoMaximo, cantidadEvaluaciones, cantidadTareas);
            colegio.agregarCurso(cursoNuevo);  
            System.out.println("Curso creado con éxito\n");
        }
        else{
            System.out.println("Regresando al último menú\n");
        }
    }

    private void cursoArte(){
        System.out.println("\n");
        String nombre = leerString("Ingrese el nombre de su Curso de Artes: ");
        System.out.println("""
                           AVISO: DEPENDIENDO DE LA CANTIDAD DE HORAS SEMANALES QUE ELIJA, SE ESCOGERÁ UN NIVEL PARA SU CURSO
                           
                           1. Menor a 6 horas = CURSO BASICO
                           2. Mayor a 6 horas y menor a 12 horas = CURSO INTERMEDIO
                           3. Mayor o igual a 12 horas = CURSO AVANZADO
                           """);        
        int horasSemanales = leerEntero("Ingrese las horas semanales de su Curso: ", 1);
        int cupoMaximo = leerEntero("Ingrese el cupo máximo de su curso: ", 1);
        int cantidadEvaluaciones = leerEntero("Ingrese la cantidad de evaluaciones que tendrá su curso: ", 0);
        int cantidadTareas = leerEntero("Ingrese la cantidad de tareas que tendrá su curso: ", 0);
        //Creación del curso
        int preguntaSeguridad = opcionDeCancelado();
        
        if(preguntaSeguridad == 1){        
            Arte cursoNuevo = new Arte(nombre, horasSemanales, cupoMaximo, cantidadEvaluaciones, cantidadTareas);
            colegio.agregarCurso(cursoNuevo); 
            System.out.println("Curso creado con éxito\n");
        }
        else{
            System.out.println("Regresando al último menú\n");
        }
    }
    
    private void ingresarNuevoAlumno() {
        String nombre = leerString("Ingrese el nombre del Alumno");
        String apellido = leerString("Ingrese el apellido del Alumno");
        
        int preguntaSeguridad = opcionDeCancelado();
        
        if(preguntaSeguridad == 1){        
            Alumno nuevoAlumno = new Alumno(nombre, apellido);
            colegio.agregarAlumno(nuevoAlumno);
        }
        else{
            System.out.println("Regresando al último menú\n");
        }
    }

    private void ingresarNuevoProfesor() {
        String nombre = leerString("Ingrese el nombre del Profesor");
        String apellido = leerString("Ingrese el apellido del Profesor");
        System.out.println("Ingrese la especialidad del Profesor");
        String especialidad = sc.nextLine();
        
        int preguntaSeguridad = opcionDeCancelado();
        
        if(preguntaSeguridad == 1){        
            Profesor nuevoProfesor = new Profesor(nombre, apellido, especialidad);
            colegio.agregarProfesor(nuevoProfesor);
            System.out.println("Profesor ingresado correctamente");
        }
        else{
            System.out.println("Regresando al último menú\n");
        }
    }

    private void mostrarCursos() {
        System.out.println("-------Lista de Cursos creados-------\n");
        System.out.println("ID/NOMBRE/HORAS SEMANALES/CUPO MAXIMO/CANTIDAD EVALUACIONES/CANTIDAD TAREAS/NIVEL DE CURSO");
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
        System.out.println("ID/NOMBRE/APELLIDO\n");
        //Variable local para guardar la lista de todos los alumnos ingresados
        ArrayList<Alumno> listaAlumnos = colegio.getAlumnos();
        //Ciclo para mostrar la lista de alumnos
        for(int i = 0; i < listaAlumnos.size(); i++){
            listaAlumnos.get(i).mostrarInformacion();
        }
        System.out.println("\n");
    }

    private void mostrarProfesores() {
        System.out.println("-------Lista de Profesores ingresados-------");
        System.out.println("ID/NOMBRE/APELLIDO/ESPECIALIDAD\n");
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

        int opcion;
        do{  
            System.out.println(""" 
                            ///////BIENVENIDO A LA GESTION DE CURSOS///////

                            Aqui podra gestionar los cursos ya creados en el colegio """);

            System.out.println("---Lista de Cursos creados---");
            System.out.println("ID      NOMBRE");
            ArrayList<Curso> listaCursos = colegio.getCursos();

            //Ciclo para mostrar la lista de cursos
            for(int i = 0; i < listaCursos.size(); i++){
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
            
            switch(opcion){    
            case 1 -> asignarProfesorACurso();
            case 2 -> inscribirAlumnoEnCurso();
            case 3 -> registrarNota();
            case 4 -> notasAlumno();
            
            case 0 -> System.out.println("Volviendo al menu principal.\n");
            
            case -1 -> {} //En caso de que el usuario coloque un dato que no es número entero 
            default -> System.out.println("Opcion invalida. Elija una opcion del menu.\n"); //En caso de que el usuario coloque un número entero inválido
            }
            
        }
        
       while (opcion != 0);       
    }            

    private void buscarCursoPorId() {
        System.out.println("Ingrese un ID de un curso para buscar  (EJEMPLO: (CUR-3)");
        String id = sc.nextLine();
        //Variable local para guardar el curso encontrado
        Curso cursoEncontrado = colegio.buscarCursoPorId(id);
        
        if(cursoEncontrado == null){
            System.out.println("No se encontro ningun curso con esa ID\n");
        }
        else{
            System.out.println("Curso encontrado\n");
            cursoEncontrado.mostrarInformacion();
        } 
    }

    private void buscarAlumnoPorId() {
        System.out.println("Ingrese un ID de un alumno para buscar  (EJEMPLO: A3)");
        String id = sc.nextLine();
        //Variable local para guardar el alumno encontrado
        Alumno alumnoEncontrado = colegio.buscarAlumnoPorId(id);
        
        if(alumnoEncontrado == null){
            System.out.println("No se encontró ningún alumno con esa ID\n");
        }
        else{
            System.out.println("Alumno encontrado\n");
            alumnoEncontrado.mostrarInformacion();
        } 
    }

    private void buscarProfesorPorId() {
        System.out.println("Ingrese un ID de un profesor para buscar  (EJEMPLO: P2)");
        String id = sc.nextLine();
        //Variable local para guardar el profesor encontrado
        Profesor profesorEncontrado = colegio.buscarProfesorPorId(id);
        
        if(profesorEncontrado == null){
            System.out.println("No se encontró ningun profesor con esa ID\n");
        }
        else{
            System.out.println("Profesor encontrado\n");
            profesorEncontrado.mostrarInformacion();
        } 
    }

    private void mostrarRanking() {
        ArrayList<Alumno> ranking = colegio.obtenerRankingAlumnos();
        System.out.println("ALUMNO            PROMEDIO DE NOTAS");
        
        for(Alumno i : ranking){
            System.out.println( i.getNombre()+ " " + i.getApellido() +  " / " + i.getPromedioNotas());
        }
        System.out.println("\n");
        
    }
    
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
    
    

    private void inscribirAlumnoEnCurso() {
        System.out.println("""
                ------- INSCRIPCIÓN DE UN ALUMNO A UN CURSO -------           
                Para asignar un alumno a un curso debe ingresar el ID del alumno y tambien debe seleccionar el ID del curso
                           """);
        System.out.println("ID Alumnos");
        ArrayList<Alumno>listaAlumnos = colegio.getAlumnos();
        
        //Ciclo para mostrar la lista de Alumnos        
        for(int i = 0; i < listaAlumnos.size(); i++){
            String alumnoId = listaAlumnos.get(i).obtenerId();
            String nombreAlumno = listaAlumnos.get(i).getNombre();
            System.out.println(alumnoId + " " + nombreAlumno);    
        }
        
        System.out.println("Ingrese el ID del Alumno");
        String idAlumno = sc.nextLine();
        System.out.println("Ingrese el ID del curso");
        String cursoId = sc.nextLine();
        
        int preguntaSeguridad = opcionDeCancelado();
        
        if(preguntaSeguridad == 1){                
            try{
                colegio.inscribirAlumnoEnCurso(idAlumno, cursoId);
                System.out.println("El alumno fue ingresado con exito\n");
            }
            catch(RuntimeException e){
                System.out.println("ERROR " + e.getMessage()+ "\n");
            }
        }
        else{
            System.out.println("Regresando al ultimo menu\n");
        }
    }

    private void asignarProfesorACurso() {
        System.out.println("""
                ------- ASIGNACION DE UN PROFESOR A UN CURSO -------           
                Para asignar un profesor a un curso debe ingresar el ID del profesor y tambien debe seleccionar el ID del curso
                        
                AVISO: SOLO SE PUEDE INGRESAR 1 PROFESOR A CADA CURSO
                           """);
        System.out.println("ID Profesores");
        ArrayList<Profesor >listaProfesores = colegio.getProfesores();
        
        //Ciclo para mostrar la lista de profesores        
        for(int i = 0; i < listaProfesores.size(); i++){
            String profesorId = listaProfesores.get(i).obtenerId();
            String nombreProfesor = listaProfesores.get(i).getNombre();
            System.out.println(profesorId + " " + nombreProfesor);    
        }
        
        System.out.println("Ingrese el ID del profesor");
        String idProfesor = sc.nextLine();
        System.out.println("Ingrese el ID del curso");
        String cursoId = sc.nextLine();
        
        int preguntaSeguridad = opcionDeCancelado();
        
        if(preguntaSeguridad == 1){             
            try{
                colegio.asignarProfesorACurso(idProfesor, cursoId);
                System.out.println("El profesor fue asignado con exito");
            }
            catch(RuntimeException e){
                System.out.println("ERROR " + e.getMessage()+ "\n");
            }
        }
        else{
            System.out.println("Regresando al ultimo menu\n");
        }
    }

    
    private void notasAlumno(){
        System.out.println("Ingrese el ID del alumno para continuar");
        
        ArrayList<Alumno>listaAlumnos = colegio.getAlumnos();
        
        //Ciclo para mostrar la lista de Alumnos        
        for(int i = 0; i < listaAlumnos.size(); i++){
            String alumnoId = listaAlumnos.get(i).obtenerId();
            String nombreAlumno = listaAlumnos.get(i).getNombre();
            System.out.println(alumnoId + " " + nombreAlumno);    
        }
        
        System.out.println("Ingrese el ID del Alumno");
        String idAlumno = sc.nextLine();
        Alumno alumnoEncontrado = colegio.buscarAlumnoPorId(idAlumno);
        
        if(alumnoEncontrado == null){
            System.out.println("Alumno no encontrado");
            System.out.println("Regresando al ultimo menu \n");
        }
        
        else{
            System.out.println("Alumno Encontrado");
            System.out.println(alumnoEncontrado.getNombre() + " " + alumnoEncontrado.getApellido() + "\n");
            int opcion;
            do{

                System.out.println("""
                                   GESTION DE NOTAS DEL ALUMNO

                                   1.Mostrar promedio de notas del alumno
                                   2.Mostrar la lista de notas del alumno

                                   0.Salir al ultimo menu

                                   """);
                opcion = leerOpcion();

                switch(opcion){    
                case 1 -> promedioNotasAlumno(alumnoEncontrado);
                case 2 -> listaNotasAlumno(alumnoEncontrado);

                case 0 -> System.out.println("Volviendo al ultimo menu.\n");

                case -1 -> {} //En caso de que el usuario coloque un dato que no es número entero 
                default -> System.out.println("Opcion invalida. Elija una opcion del menu.\n"); //En caso de que el usuario coloque un número entero inválido
                }
            }
            
            while (opcion != 0);      
            
        }    
    }

    private void listaNotasAlumno(Alumno alumnoEncontrado) {
        ArrayList<Double> notas = alumnoEncontrado.getNotas();
        if(notas.isEmpty()){
            System.out.println("El alumno no tiene notas\n");
        }
        
        else{
            System.out.println("----Lista de notas----");
            for(int i = 0; i < notas.size(); i++){
                double nota = notas.get(i);
                System.out.println(nota + "\n");    
            
            }
        }
    }

    private void promedioNotasAlumno(Alumno alumnoEncontrado){
        if(alumnoEncontrado.getNotas().isEmpty()){
            System.out.println("El alumno no tiene notas, por tanto no hay promedio\n");
        }
        else{
        System.out.println("El promedio de notas del alumno es: " + alumnoEncontrado.getPromedioNotas() + "\n" );
        }
    }
    
    private void configurarPonderacion(Curso curso) {
        //Código
    }

    private void mostrarPonderacion(Curso curso) {
        curso.informacionPonderacion();
    }

    private void registrarNota() {
        System.out.println("""
               ------- REGISTRO DE NOTA DE UN ALUMNO -------           
                Para registrar una nota de un alumno en particular, tendrá que colocar su ID y la nota que desea ingresar 
                           
                AVISO: LA NOTA MÍNIMA ES UN 1.0 Y LA NOTA MAXIMA ES UN 7.0
                           """);
        
        ArrayList<Alumno>listaAlumnos = colegio.getAlumnos();
        
        //Ciclo para mostrar la lista de Alumnos        
        for(int i = 0; i < listaAlumnos.size(); i++){
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
        
        try{
            colegio.registrarNota(idAlumno, nota);
            System.out.println("La nota del alumno fue asignada con exito");
        }
        catch(RuntimeException e){
            System.out.println("ERROR " + e.getMessage()+ "\n");
        }        
    }
    
    //Método para preguntarle al usuario si está seguro de su decisión
    private int opcionDeCancelado(){
        int opcion;
        do{
            System.out.println("""
                           ¿Estas seguro de que quieres continuar?
                           
                           1.Si
                           2.No""");
            opcion = leerOpcion();
        
        
            switch (opcion) {
                case 1 ->{System.out.println("Decidio continuar con el proceso\n");}
                case 2 ->{System.out.println("Decidio cancelar el proceso\n");}
                case -1 -> {} //En caso de que el usuario coloque un dato que no es número entero 
                default -> System.out.println("Opción no válida. Elija una opción del menu.\n"); //En caso de que el usuario coloque un número entero inválido    
            }
        }
        
        while (opcion != 1 && opcion != 2);
        
        return opcion;
    }
}
