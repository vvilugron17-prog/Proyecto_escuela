package colegio.gestion;

import colegio.enums.NivelCurso;
import colegio.enums.TipoOperacion;
import colegio.excepciones.CupoLlenoException;
import colegio.excepciones.NotaInvalidaException;
import colegio.modelo.Alumno;
import colegio.modelo.Arte;
import colegio.modelo.Ciencia;
import colegio.modelo.Curso;
import colegio.modelo.Matematica;
import colegio.modelo.Operacion;
import colegio.modelo.Persona;
import colegio.modelo.Profesor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// Clase principal del colegio.
public final class Colegio {

    // Listas principales del sistema.
    private ArrayList<Alumno> alumnos;
    private ArrayList<Profesor> profesores;
    private ArrayList<Curso> cursos;
    private ArrayList<Operacion> historial;

    // Mapas para buscar por ID.
    private final HashMap<String, Alumno> alumnosPorId;
    private final HashMap<String, Profesor> profesoresPorId;
    private final HashMap<String, Curso> cursosPorId;

    // Archivo donde se guardan los datos.
    private static final String ARCHIVO_DATOS = "colegio_datos.dat";

    public Colegio() {
        alumnos = new ArrayList<>();
        profesores = new ArrayList<>();
        cursos = new ArrayList<>();
        historial = new ArrayList<>();

        alumnosPorId = new HashMap<>();
        profesoresPorId = new HashMap<>();
        cursosPorId = new HashMap<>();

        // Carga los datos guardados.
        cargarDatos();

        // Agrega datos de ejemplo si no hay registros.
        if (alumnos.isEmpty() && profesores.isEmpty() && cursos.isEmpty()) {
            precargarDatos();
        }
    }

    // Crea datos iniciales para probar el sistema.
    private void precargarDatos() {
        Profesor p1 = new Profesor("P01", "Diego", "Fernandez", 50, "Física", 2000);
        Profesor p2 = new Profesor("P02", "Benjita", "Pobletete", 42, "Matemática", 1850);
        Profesor p3 = new Profesor("P03", "Tabatata", "Fernanda", 38, "Artes", 1600);

        registrarProfesor(p1);
        registrarProfesor(p2);
        registrarProfesor(p3);

        registrarCurso(new Matematica("Álgebra", 6, 30, 3, 4, NivelCurso.INTERMEDIO));
        registrarCurso(new Matematica("Geometría", 5, 25, 2, 3, NivelCurso.BASICO));
        registrarCurso(new Matematica("Cálculo", 7, 20, 4, 2, NivelCurso.AVANZADO));
        registrarCurso(new Ciencia("Biología", 5, 28, 3, 4, NivelCurso.INTERMEDIO));
        registrarCurso(new Ciencia("Física", 5, 24, 3, 3, NivelCurso.AVANZADO));
        registrarCurso(new Ciencia("Química", 4, 26, 2, 4, NivelCurso.BASICO));
        registrarCurso(new Arte("Dibujo", 4, 20, 2, 5, NivelCurso.BASICO));
        registrarCurso(new Arte("Música", 3, 22, 3, 4, NivelCurso.INTERMEDIO));
        registrarCurso(new Arte("Pintura", 4, 18, 2, 4, NivelCurso.AVANZADO));

        // Asigna profesores a los cursos.
        asignarProfesorACurso("CUR-001", "P02");
        asignarProfesorACurso("CUR-002", "P02");
        asignarProfesorACurso("CUR-003", "P02");
        asignarProfesorACurso("CUR-004", "P01");
        asignarProfesorACurso("CUR-005", "P01");
        asignarProfesorACurso("CUR-006", "P01");
        asignarProfesorACurso("CUR-007", "P03");
        asignarProfesorACurso("CUR-008", "P03");
        asignarProfesorACurso("CUR-009", "P03");

        // Crea los alumnos de ejemplo.
        Alumno a1 = new Alumno("A01", "Juan", "Pérez", 15, "MAT-01");
        Alumno a2 = new Alumno("A02", "María", "González", 16, "MAT-02");
        Alumno a3 = new Alumno("A03", "Pedro", "Soto", 15, "MAT-03");
        Alumno a4 = new Alumno("A04", "Camila", "Rojas", 17, "MAT-04");
        Alumno a5 = new Alumno("A05", "Diego", "Muñoz", 16, "MAT-05");

        registrarAlumno(a1);
        registrarAlumno(a2);
        registrarAlumno(a3);
        registrarAlumno(a4);
        registrarAlumno(a5);

        // Inscribe alumnos y registra sus notas.
        try {
            inscribirAlumnoEnCurso("A01", "CUR-001");
            inscribirAlumnoEnCurso("A02", "CUR-001");
            inscribirAlumnoEnCurso("A03", "CUR-004");
            inscribirAlumnoEnCurso("A04", "CUR-007");
            inscribirAlumnoEnCurso("A05", "CUR-002");

            registrarNota("A01", 6.2);
            registrarNota("A01", 5.8);
            registrarNota("A02", 6.7);
            registrarNota("A03", 5.9);
            registrarNota("A04", 6.5);
            registrarNota("A05", 5.5);

        } catch (CupoLlenoException | NotaInvalidaException e) {
            System.out.println("No fue posible completar una parte de los datos iniciales: " + e.getMessage());
        }

        registrarOperacion(TipoOperacion.PONDERACION, "Datos de ejemplo preparados para la demostración.");
    }

    // Limpia y normaliza los identificadores.
    private String clave(String id) {
        if (id == null) {
            return "";
        } else {
            return id.trim().toUpperCase(java.util.Locale.ROOT);
        }
    }

    // Registra un alumno.
    public void registrarAlumno(Alumno alumno) {
        if (alumno == null) {
            throw new IllegalArgumentException("El alumno no puede ser nulo.");
        }
        
        if (alumno.obtenerId() == null || alumno.obtenerId().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del alumno no puede estar vacío.");
        }

        String k = clave(alumno.obtenerId());

        if (alumnosPorId.containsKey(k)) {
            throw new IllegalArgumentException("Ya existe un alumno con ID " + alumno.obtenerId() + ".");
        }

        // Evita matrículas repetidas.
        for (Alumno existente : alumnos) {
            boolean tieneMatricula = existente.getMatricula() != null;
            boolean matriculaCoincide = tieneMatricula && existente.getMatricula().equalsIgnoreCase(alumno.getMatricula());
            
            if (matriculaCoincide) {
                throw new IllegalArgumentException("Ya existe un alumno con matrícula " + alumno.getMatricula() + ".");
            }
        }

        alumnos.add(alumno);
        alumnosPorId.put(k, alumno);

        String descripcion = "Registrado alumno " + alumno.getNombre() + " " + alumno.getApellido() + " (" + alumno.obtenerId() + ").";
        registrarOperacion(TipoOperacion.INSCRIPCION_ALUMNO, descripcion);
    }

    // Registra un profesor.
    public void registrarProfesor(Profesor profesor) {
        if (profesor == null) {
            throw new IllegalArgumentException("El profesor no puede ser nulo.");
        }
        
        if (profesor.obtenerId() == null || profesor.obtenerId().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del profesor no puede estar vacío.");
        }

        String k = clave(profesor.obtenerId());

        if (profesoresPorId.containsKey(k)) {
            throw new IllegalArgumentException("Ya existe un profesor con ID " + profesor.obtenerId() + ".");
        }

        profesores.add(profesor);
        profesoresPorId.put(k, profesor);

        String descripcion = "Registrado profesor " + profesor.getNombre() + " " + profesor.getApellido() + ".";
        registrarOperacion(TipoOperacion.REGISTRO_PROFESOR, descripcion);
    }

    // Registra un curso.
    public void registrarCurso(Curso curso) {
        if (curso == null) {
            throw new IllegalArgumentException("El curso no puede ser nulo.");
        }

        // Evita nombres de cursos repetidos.
        for (Curso existente : cursos) {
            if (existente.getNombre().equalsIgnoreCase(curso.getNombre())) {
                throw new IllegalArgumentException("Ya existe un curso con el nombre " + curso.getNombre() + ".");
            }
        }

        cursos.add(curso);
        cursosPorId.put(clave(curso.obtenerId()), curso);

        String descripcion = "Creado curso " + curso.getNombre() + " (" + curso.obtenerId() + ").";
        registrarOperacion(TipoOperacion.CREACION_CURSO, descripcion);
    }

    // Agrega una nota a un alumno.
    public void registrarNota(String idAlumno, double nota) throws NotaInvalidaException {
        Alumno alumno = buscarAlumno(idAlumno);

        if (alumno == null) {
            throw new IllegalArgumentException("No existe un alumno con ID " + idAlumno + ".");
        }

        alumno.agregarNota(nota);

        String descripcion = "Nota " + nota + " añadida a " + alumno.getNombre() + " " + alumno.getApellido() + ".";
        registrarOperacion(TipoOperacion.REGISTRO_NOTA, descripcion);
    }

    // Inscribe a un alumno en un curso.
    public void inscribirAlumnoEnCurso(String idAlumno, String idCurso) throws CupoLlenoException {
        Alumno alumno = buscarAlumno(idAlumno);
        Curso curso = buscarCurso(idCurso);

        if (alumno == null) {
            throw new IllegalArgumentException("No existe un alumno con ID " + idAlumno + ".");
        }
        
        if (curso == null) {
            throw new IllegalArgumentException("No existe un curso con ID o nombre " + idCurso + ".");
        }

        curso.inscribirAlumno(alumno);

        String descripcion = "Alumno " + alumno.getNombre() + " inscrito en " + curso.getNombre() + ".";
        registrarOperacion(TipoOperacion.INSCRIPCION, descripcion);
    }

    // Asigna un profesor a un curso.
    public void asignarProfesorACurso(String idCurso, String idProfesor) {
        Curso curso = buscarCurso(idCurso);
        Profesor profesor = buscarProfesor(idProfesor);

        if (curso == null) {
            throw new IllegalArgumentException("No existe un curso con ID o nombre " + idCurso + ".");
        }
        
        if (profesor == null) {
            throw new IllegalArgumentException("No existe un profesor con ID " + idProfesor + ".");
        }

        curso.setProfesor(profesor);

        String descripcion = "Profesor " + profesor.getNombre() + " asignado al curso " + curso.getNombre() + ".";
        registrarOperacion(TipoOperacion.ASIGNACION_PROFESOR, descripcion);
    }

    // Configura los porcentajes de evaluación y tareas.
    public void configurarPonderacion(String idCurso, double porcentajeEvaluaciones, double porcentajeTareas) {
        Curso curso = buscarCurso(idCurso);

        if (curso == null) {
            throw new IllegalArgumentException("No existe un curso con ID o nombre " + idCurso + ".");
        }

        // Comprueba que los porcentajes sean válidos dividiendo la condición para mejor lectura
        boolean negativos = (porcentajeEvaluaciones < 0) || (porcentajeTareas < 0);
        boolean sonNulos = Double.isNaN(porcentajeEvaluaciones) || Double.isNaN(porcentajeTareas);
        boolean noSumanCien = Math.abs(porcentajeEvaluaciones + porcentajeTareas - 100.0) >= 0.001;

        if (negativos || sonNulos || noSumanCien) {
            throw new IllegalArgumentException("Los porcentajes no pueden ser negativos y deben sumar exactamente 100%.");
        }

        curso.calcularPonderacion(porcentajeEvaluaciones, porcentajeTareas);

        String descripcion = "Configurada ponderación del curso " + curso.getNombre() + ".";
        registrarOperacion(TipoOperacion.PONDERACION, descripcion);
    }

    // Busca un alumno por ID o nombre.
    public Alumno buscarAlumno(String id) {
        if (id == null) {
            return null;
        }

        Alumno encontrado = alumnosPorId.get(clave(id));
        if (encontrado != null) {
            return encontrado;
        }

        for (Alumno alumno : alumnos) {
            String nombreSolo = alumno.getNombre();
            String nombreCompleto = alumno.getNombre() + " " + alumno.getApellido();
            
            if (nombreSolo.equalsIgnoreCase(id.trim()) || nombreCompleto.equalsIgnoreCase(id.trim())) {
                return alumno;
            }
        }

        return null;
    }

    // Busca un profesor por ID.
    public Profesor buscarProfesor(String id) {
        if (id == null) {
            return null;
        } else {
            return profesoresPorId.get(clave(id));
        }
    }

    // Busca un curso por ID o nombre.
    public Curso buscarCurso(String idONombre) {
        if (idONombre == null) {
            return null;
        }

        Curso encontrado = cursosPorId.get(clave(idONombre));
        if (encontrado != null) {
            return encontrado;
        }

        for (Curso curso : cursos) {
            if (curso.getNombre().equalsIgnoreCase(idONombre.trim())) {
                return curso;
            }
        }

        return null;
    }

    // Guarda una operación en el historial.
    private void registrarOperacion(TipoOperacion tipo, String descripcion) {
        historial.add(new Operacion(tipo, descripcion));
    }

    // Muestra los cursos ordenados por nombre.
    public void mostrarCursos() {
        System.out.println("\n--- Cursos registrados por nombre (" + cursos.size() + ") ---");

        if (cursos.isEmpty()) {
            System.out.println("No hay cursos registrados.");
            return;
        }

        ArrayList<Curso> cursosOrdenados = new ArrayList<>(cursos);
        cursosOrdenados.sort(Comparator.comparing(Curso::getNombre, String.CASE_INSENSITIVE_ORDER));

        for (Curso curso : cursosOrdenados) {
            System.out.println(curso);
        }
    }

    // Muestra una lista de alumnos o profesores.
    private void mostrarPersonas(String titulo, ArrayList<? extends Persona> lista) {
        System.out.println("\n--- " + titulo + " (" + lista.size() + ") ---");

        if (lista.isEmpty()) {
            System.out.println("No hay registros.");
            return;
        }

        for (Persona persona : lista) {
            persona.mostrarInformacion();
        }
    }

    // Muestra todos los alumnos.
    public void mostrarAlumnos() {
        mostrarPersonas("Alumnos registrados", alumnos);
    }

    // Muestra todos los profesores.
    public void mostrarProfesores() {
        mostrarPersonas("Profesores registrados", profesores);
    }

    // Muestra la ficha de un alumno.
    public void mostrarFichaAlumno(String id) {
        Alumno alumno = buscarAlumno(id);

        if (alumno == null) {
            System.out.println("No se encontró el alumno.");
            return;
        }

        System.out.println("\n--- Ficha del alumno ---");
        alumno.mostrarInformacion();

        System.out.println("Cursos inscritos:");
        boolean encontrado = false;

        // Busca los cursos del alumno.
        for (Curso curso : cursos) {
            for (Alumno inscrito : curso.getAlumnos()) {
                if (inscrito.obtenerId().equalsIgnoreCase(alumno.obtenerId())) {
                    System.out.println("  " + curso.getId() + " - " + curso.getNombre());
                    encontrado = true;
                    break;
                }
            }
        }

        if (!encontrado) {
            System.out.println("  No está inscrito en cursos.");
        }

        System.out.println("Operaciones del historial relacionadas con este alumno:");
        boolean tieneOperaciones = false;

        String idAlumno = alumno.obtenerId().toUpperCase(java.util.Locale.ROOT);
        String nombreAlumno = (alumno.getNombre() + " " + alumno.getApellido()).toUpperCase(java.util.Locale.ROOT);

        // Busca las operaciones relacionadas con el alumno.
        for (Operacion operacion : historial) {
            String descripcion = "";
            if (operacion.getDescripcion() != null) {
                descripcion = operacion.getDescripcion().toUpperCase(java.util.Locale.ROOT);
            }

            if (descripcion.contains(idAlumno) || descripcion.contains(nombreAlumno)) {
                System.out.println("  " + operacion);
                tieneOperaciones = true;
            }
        }

        if (!tieneOperaciones) {
            System.out.println("  No hay operaciones asociadas al alumno.");
        }
    }

    // Muestra la información de un curso.
    public void mostrarCurso(String id) {
        Curso curso = buscarCurso(id);

        if (curso == null) {
            System.out.println("No se encontró el curso.");
            return;
        }

        System.out.println("\n" + curso);
        curso.datosPonderacion();

        System.out.println("Carga horaria calculada: " + curso.calcularCargaHoraria() + " horas semanales.");
        System.out.println("Alumnos inscritos:");

        if (curso.getAlumnos().isEmpty()) {
            System.out.println("  Ninguno");
        }

        for (Alumno alumno : curso.getAlumnos()) {
            System.out.println("  " + alumno.getId() + " - " + alumno.getNombre() + " " + alumno.getApellido());
        }
    }

    // Muestra el ranking de alumnos.
    public void mostrarRanking() {
        ArrayList<Alumno> ranking = new ArrayList<>(alumnos);

        ranking.sort(Comparator.comparingDouble(Alumno::obtenerPuntajeRanking).reversed()
                .thenComparing(Alumno::getNombre, String.CASE_INSENSITIVE_ORDER));

        System.out.println("\n--- Ranking de alumnos por promedio ---");

        if (ranking.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
            return;
        }

        int puesto = 1;

        for (Alumno alumno : ranking) {
            String promedioFormateado = String.format(java.util.Locale.US, "%.2f", alumno.calcularPromedio());
            String mensaje = puesto + ". " + alumno.getNombre() + " " + alumno.getApellido() 
                             + " (" + alumno.getId() + ") - Promedio: " + promedioFormateado;
            
            System.out.println(mensaje);
            puesto++;
        }
    }

    // Muestra el ranking de cursos.
    public void mostrarRankingCursos() {
        ArrayList<Curso> ranking = new ArrayList<>(cursos);

        ranking.sort(Comparator.comparingDouble(Curso::obtenerPuntajeRanking).reversed());

        System.out.println("\n--- Ranking de cursos por promedio de sus alumnos ---");

        if (ranking.isEmpty()) {
            System.out.println("No hay cursos registrados.");
            return;
        }

        int puesto = 1;

        for (Curso curso : ranking) {
            String puntajeFormateado = String.format(java.util.Locale.US, "%.2f", curso.obtenerPuntajeRanking());
            String mensaje = puesto + ". " + curso.getNombre() + " (" + curso.getId() + ") - Puntaje: " + puntajeFormateado;
            
            System.out.println(mensaje);
            puesto++;
        }
    }

    // Muestra el historial de operaciones.
    public void mostrarHistorial() {
        System.out.println("\n--- Historial de operaciones (" + historial.size() + ") ---");

        if (historial.isEmpty()) {
            System.out.println("No hay operaciones registradas.");
            return;
        }

        for (Operacion operacion : historial) {
            System.out.println(operacion);
        }
    }

    // Guarda los datos en un archivo.
    public void guardarDatos() {
        File temporal = new File(ARCHIVO_DATOS + ".tmp");

        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(temporal))) {
            // Guarda las listas en el mismo orden en que se cargan.
            salida.writeObject(alumnos);
            salida.writeObject(profesores);
            salida.writeObject(cursos);
            salida.writeObject(historial);
            salida.flush();

        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
            temporal.delete();
            return;
        }

        // Reemplaza el archivo anterior por el nuevo.
        try {
            Files.move(temporal.toPath(), new File(ARCHIVO_DATOS).toPath(), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            try {
                Files.move(temporal.toPath(), new File(ARCHIVO_DATOS).toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException segundoError) {
                System.out.println("Error al finalizar el guardado: " + segundoError.getMessage());
                return;
            }
        }

        System.out.println("Datos guardados correctamente en " + ARCHIVO_DATOS + ".");
    }

    // Carga los datos guardados.
    @SuppressWarnings("unchecked")
    public void cargarDatos() {
        File archivo = new File(ARCHIVO_DATOS);

        if (!archivo.exists()) {
            return;
        }

        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
            // Lee las listas en el mismo orden en que se guardaron.
            Object datosAlumnos = entrada.readObject();
            Object datosProfesores = entrada.readObject();
            Object datosCursos = entrada.readObject();
            Object datosHistorial = entrada.readObject();

            boolean formatoInvalido = !(datosAlumnos instanceof ArrayList) 
                    || !(datosProfesores instanceof ArrayList)
                    || !(datosCursos instanceof ArrayList) 
                    || !(datosHistorial instanceof ArrayList);

            if (formatoInvalido) {
                throw new IOException("El archivo de datos tiene un formato distinto al esperado.");
            }

            alumnos = (ArrayList<Alumno>) datosAlumnos;
            profesores = (ArrayList<Profesor>) datosProfesores;
            cursos = (ArrayList<Curso>) datosCursos;
            historial = (ArrayList<Operacion>) datosHistorial;

            reconstruirIndices();
            Curso.sincronizarContadorId(cursos);

            System.out.println("Datos anteriores cargados: " + alumnos.size() + " alumnos, " + cursos.size() + " cursos.");

        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("No se pudieron cargar los datos guardados. Se iniciará con datos de ejemplo. Detalle: " + e.getMessage());

            alumnos.clear();
            profesores.clear();
            cursos.clear();
            historial.clear();

            alumnosPorId.clear();
            profesoresPorId.clear();
            cursosPorId.clear();
        }
    }

    // Actualiza los mapas con los datos cargados.
    private void reconstruirIndices() {
        alumnosPorId.clear();
        for (Alumno alumno : alumnos) {
            if (alumno != null) {
                alumnosPorId.put(clave(alumno.obtenerId()), alumno);
            }
        }

        profesoresPorId.clear();
        for (Profesor profesor : profesores) {
            if (profesor != null) {
                profesoresPorId.put(clave(profesor.obtenerId()), profesor);
            }
        }

        cursosPorId.clear();
        for (Curso curso : cursos) {
            if (curso != null) {
                cursosPorId.put(clave(curso.obtenerId()), curso);
            }
        }
    }

    // Devuelve una copia de la lista de alumnos.
    public ArrayList<Alumno> getAlumnos() {
        return new ArrayList<>(alumnos);
    }

    // Devuelve una copia de la lista de profesores.
    public ArrayList<Profesor> getProfesores() {
        return new ArrayList<>(profesores);
    }

    // Devuelve una copia de la lista de cursos.
    public ArrayList<Curso> getCursos() {
        return new ArrayList<>(cursos);
    }

    // Devuelve una copia del historial.
    public ArrayList<Operacion> getHistorial() {
        return new ArrayList<>(historial);
    }

    // Devuelve el mapa de alumnos sin permitir modificaciones.
    public Map<String, Alumno> getAlumnosPorId() {
        return Collections.unmodifiableMap(alumnosPorId);
    }

    // Devuelve el mapa de profesores sin permitir modificaciones.
    public Map<String, Profesor> getProfesoresPorId() {
        return Collections.unmodifiableMap(profesoresPorId);
    }

    // Devuelve el mapa de cursos sin permitir modificaciones.
    public Map<String, Curso> getCursosPorId() {
        return Collections.unmodifiableMap(cursosPorId);
    }
}