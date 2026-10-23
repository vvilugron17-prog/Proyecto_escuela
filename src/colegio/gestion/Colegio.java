package colegio.gestion;

import colegio.enums.TipoOperacion;
import colegio.modelo.Alumno;
import colegio.modelo.Curso;
import colegio.modelo.Operacion;
import colegio.modelo.Profesor;

import java.io.*;
import java.util.*;

// [REQUISITO: Package]
// Esta clase coordina los registros y las operaciones principales del colegio.
public class Colegio implements Serializable {

    // [REQUISITO: Static y Final]
    private static final long serialVersionUID = 1L;
    private static final String NOMBRE_COLEGIO = "Zapallar";
    // [REQUISITO: Persistencia] Archivo donde se guarda el colegio.
    private static final String ARCHIVO_DATOS = "colegio_datos.dat";

    // Las listas conservan el orden en que se registran los objetos.
    // [REQUISITO: Colecciones]
    private final ArrayList<Alumno> alumnos = new ArrayList<>();
    private final ArrayList<Profesor> profesores = new ArrayList<>();
    private final ArrayList<Curso> cursos = new ArrayList<>();
    private final ArrayList<Operacion> historial = new ArrayList<>();

    // Los mapas permiten encontrar objetos directamente por su ID.
    // [REQUISITO: Colecciones]
    private final HashMap<String, Alumno> alumnosPorId = new HashMap<>();
    private final HashMap<String, Profesor> profesoresPorId = new HashMap<>();
    private final HashMap<String, Curso> cursosPorId = new HashMap<>();

    public String getNombreColegio() {
        return NOMBRE_COLEGIO;
    }

    // Devolvemos copias de las listas para evitar que desde fuera se agreguen
    // o eliminen elementos sin actualizar los HashMap de búsqueda.
    public ArrayList<Alumno> getAlumnos() { return new ArrayList<>(alumnos); }

    public ArrayList<Profesor> getProfesores() { return new ArrayList<>(profesores); }

    public ArrayList<Curso> getCursos() { return new ArrayList<>(cursos); }

    public ArrayList<Operacion> getHistorial() { return new ArrayList<>(historial); }

    // Registro de alumnos
    public void agregarAlumno(Alumno alumno) {
        if (alumno == null) {
            throw new IllegalArgumentException("El alumno no puede ser nulo.");
        }

        String id = normalizarId(alumno.obtenerId());
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID del alumno no es válido.");
        }
        if (alumnosPorId.containsKey(id)) {
            throw new IllegalArgumentException("Ya existe un alumno con ese ID.");
        }

        alumnos.add(alumno);
        alumnosPorId.put(id, alumno);
        registrar(TipoOperacion.REGISTRO_ALUMNO,
                "Se registró el alumno con ID " + id + ".");
    }

    // Registro de profesores
    public void agregarProfesor(Profesor profesor) {
        if (profesor == null) {
            throw new IllegalArgumentException("El profesor no puede ser nulo.");
        }

        String id = normalizarId(profesor.obtenerId());
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID del profesor no es válido.");
        }
        if (profesoresPorId.containsKey(id)) {
            throw new IllegalArgumentException("Ya existe un profesor con ese ID.");
        }

        profesores.add(profesor);
        profesoresPorId.put(id, profesor);
        registrar(TipoOperacion.REGISTRO_PROFESOR,
                "Se registró el profesor con ID " + id + ".");
    }

    // Registro de cursos
    public void agregarCurso(Curso curso) {
        if (curso == null) {
            throw new IllegalArgumentException("El curso no puede ser nulo.");
        }

        String id = normalizarId(curso.obtenerId());
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID del curso no es válido.");
        }
        if (cursosPorId.containsKey(id)) {
            throw new IllegalArgumentException("Ya existe un curso con ese ID.");
        }

        cursos.add(curso);
        cursosPorId.put(id, curso);
        registrar(TipoOperacion.REGISTRO_CURSO,
                "Se registró el curso con ID " + id + ".");
    }

    // [REQUISITO: Static y Final]
    // Normaliza IDs para que, por ejemplo, "c1" y " C1 " se igualen
    // Si es nulo, se devuelve el mismo. 
    // Si no, se quita espacios del inicio y final y se pone mayuscula todo.
    public static String normalizarId(String id) {
        if (id == null) {
            return null;
        }
        return id.trim().toUpperCase(Locale.ROOT);
    }

    // Búsquedas directas por ID usando los HashMap
    public Alumno buscarAlumnoPorId(String id) {
        String idNormalizado = normalizarId(id);
        if (idNormalizado == null || idNormalizado.isEmpty()) {
            return null;
        }
        return alumnosPorId.get(idNormalizado);
    }

    public Profesor buscarProfesorPorId(String id) {
        String idNormalizado = normalizarId(id);
        if (idNormalizado == null || idNormalizado.isEmpty()) {
            return null;
        }
        return profesoresPorId.get(idNormalizado);
    }

    public Curso buscarCursoPorId(String id) {
        String idNormalizado = normalizarId(id);
        if (idNormalizado == null || idNormalizado.isEmpty()) {
            return null;
        }
        return cursosPorId.get(idNormalizado);
    }

    // [REQUISITO: Polimorfismo]
    // El ranking utiliza compareTo de Alumno y ordena desde el promedio más alto
    public ArrayList<Alumno> obtenerRankingAlumnos() {
        ArrayList<Alumno> ranking = new ArrayList<>(alumnos);
        Collections.sort(ranking);
        return ranking;
    }

    // [REQUISITO: Herencia] [REQUISITO: Excepciones]
    // Inscribe al alumno y deja constancia de la operación en el historial.
    public void inscribirAlumnoEnCurso(String alumnoId, String cursoId) {
        Alumno alumno = buscarAlumnoPorId(alumnoId);
        Curso curso = buscarCursoPorId(cursoId);

        if (alumno == null) {
            throw new IllegalArgumentException("No existe un alumno con ese ID.");
        }
        if (curso == null) {
            throw new IllegalArgumentException("No existe un curso con ese ID.");
        }

        for (Alumno inscrito : curso.getAlumnos()) {
            if (normalizarId(inscrito.obtenerId()).equals(normalizarId(alumno.obtenerId()))) {
                throw new IllegalArgumentException("El alumno ya está inscrito en ese curso.");
            }
        }

        curso.inscribirAlumno(alumno);
        registrar(TipoOperacion.INSCRIPCION,
                "Alumno " + alumno.obtenerId() 
                + " inscrito en curso " + curso.obtenerId() + ".");
    }

    // Asigna un profesor al curso y registra el cambio.
    public void asignarProfesorACurso(String profesorId, String cursoId) {
        Profesor profesor = buscarProfesorPorId(profesorId);
        Curso curso = buscarCursoPorId(cursoId);

        if (profesor == null) {
            throw new IllegalArgumentException("No existe un profesor con ese ID.");
        }
        if (curso == null) {
            throw new IllegalArgumentException("No existe un curso con ese ID.");
        }
        if (curso.getProfesor() != null) {
            throw new IllegalArgumentException("El curso ya tiene un profesor.");
        }

        curso.setProfesor(profesor);
        registrar(TipoOperacion.ASIGNACION_PROFESOR,
                "Profesor " + profesor.obtenerId() + " asignado al curso " + curso.obtenerId() + ".");
    }

    // Registra una nota y deja constancia en el historial.
    public void registrarNota(String alumnoId, double nota) {
        Alumno alumno = buscarAlumnoPorId(alumnoId);

        if (alumno == null) {
            throw new IllegalArgumentException("No existe un alumno con ese ID.");
        }

        alumno.agregarNotas(nota);
        registrar(TipoOperacion.REGISTRO_NOTA,
                "Se registró la nota " + nota + " para el alumno " + alumno.obtenerId() + ".");
    }

    // [REQUISITO: Persistencia]
    public void guardar() {
    try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(ARCHIVO_DATOS))) {

        oos.writeObject(this);
        System.out.println("Datos guardados correctamente.");

    } catch (IOException e) {
        System.out.println("Error al guardar: " + e.getMessage());
    }
}

    // [REQUISITO: Persistencia]
    public static Colegio cargar() {
    File archivo = new File(ARCHIVO_DATOS);

    if (!archivo.exists() || archivo.length() == 0) {
        System.out.println("No hay datos guardados.");
        return null;
    }

    try (ObjectInputStream ois = new ObjectInputStream(
            new FileInputStream(archivo))) {

        Colegio colegio = (Colegio) ois.readObject();

        Curso.sincronizarContadorId(colegio.getCursos());
        Alumno.sincronizarContadorId(colegio.getAlumnos());
        Profesor.sincronizarContadorId(colegio.getProfesores());

        System.out.println("Datos cargados correctamente.");
        return colegio;

    } catch (IOException e) {
        System.out.println("Error al cargar: " + e.getMessage());
    } catch (ClassNotFoundException e) {
        System.out.println("No se encontró una clase necesaria para cargar los datos.");
    } catch (ClassCastException e) {
        System.out.println("El archivo no contiene datos válidos de un colegio.");
    }

    return null;
}

    // Agrega una operación al historial.
    private void registrar(TipoOperacion tipo, String descripcion) {
        historial.add(new Operacion(tipo, descripcion));
    }
}
