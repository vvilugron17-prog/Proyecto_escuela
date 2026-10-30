package colegio.gestion;

import colegio.enums.TipoOperacion;
import colegio.modelo.Alumno;
import colegio.modelo.Curso;
import colegio.modelo.Operacion;
import colegio.modelo.Profesor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;

// [REQUISITO: Package]
// Esta clase coordina los registros y las operaciones principales del colegio.
public class Colegio implements Serializable {

    
    
    // ===== DATOS DEL COLEGIO =====

    // [REQUISITO: Static y Final]
    private static final long serialVersionUID = 1L;
    private static final String NOMBRE_COLEGIO = "Liceo Bicentenario Zapallar de Curico";
    // [REQUISITO: Persistencia]
    private static final String ARCHIVO_DATOS = "colegio_datos.dat";

    // [REQUISITO: Colecciones] Listas para recorrer y mostrar los registros.
    private final ArrayList<Alumno> alumnos = new ArrayList<>();
    private final ArrayList<Profesor> profesores = new ArrayList<>();
    private final ArrayList<Curso> cursos = new ArrayList<>();
    private final ArrayList<Operacion> historial = new ArrayList<>();

    // [REQUISITO: Colecciones] Mapas para buscar directamente por ID.
    private final HashMap<String, Alumno> alumnosPorId = new HashMap<>();
    private final HashMap<String, Profesor> profesoresPorId = new HashMap<>();
    private final HashMap<String, Curso> cursosPorId = new HashMap<>();

    public String getNombreColegio() {
        return NOMBRE_COLEGIO;
    }

    // Se devuelven copias para evitar cambios externos en las listas internas.
    public ArrayList<Alumno> getAlumnos() {
        return new ArrayList<>(alumnos);
    }

    public ArrayList<Profesor> getProfesores() {
        return new ArrayList<>(profesores);
    }

    public ArrayList<Curso> getCursos() {
        return new ArrayList<>(cursos);
    }

    public ArrayList<Operacion> getHistorial() {
        return new ArrayList<>(historial);
    }

    
    
    // ===== REGISTRO DE ELEMENTOS =====

    public void agregarAlumno(Alumno alumno) {
        if (alumno == null) {
            throw new IllegalArgumentException("El alumno no puede ser nulo.");
        }

        String id = normalizarId(alumno.obtenerId());
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID del alumno no es valido.");
        }
        if (alumnosPorId.containsKey(id)) {
            throw new IllegalArgumentException("Ya existe un alumno con ese ID.");
        }

        alumnos.add(alumno);
        alumnosPorId.put(id, alumno);
        registrar(TipoOperacion.REGISTRO_ALUMNO,
                "Se registro el alumno " + alumno.getNombre() + " " + alumno.getApellido() + ".",
                id);
    }

    public void agregarProfesor(Profesor profesor) {
        if (profesor == null) {
            throw new IllegalArgumentException("El profesor no puede ser nulo.");
        }

        String id = normalizarId(profesor.obtenerId());
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID del profesor no es valido.");
        }
        if (profesoresPorId.containsKey(id)) {
            throw new IllegalArgumentException("Ya existe un profesor con ese ID.");
        }

        profesores.add(profesor);
        profesoresPorId.put(id, profesor);
        registrar(TipoOperacion.REGISTRO_PROFESOR,
                "Se registro el profesor con ID " + id + ".");
    }

    public void agregarCurso(Curso curso) {
        if (curso == null) {
            throw new IllegalArgumentException("El curso no puede ser nulo.");
        }

        String id = normalizarId(curso.obtenerId());
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID del curso no es valido.");
        }
        if (cursosPorId.containsKey(id)) {
            throw new IllegalArgumentException("Ya existe un curso con ese ID.");
        }

        cursos.add(curso);
        cursosPorId.put(id, curso);
        registrar(TipoOperacion.REGISTRO_CURSO,
                "Se registro el curso " + curso.getNombre() + " con ID " + id + ".");
    }

    
    
    // ===== BUSQUEDAS =====

    // [REQUISITO: Static y Final] Metodo utilitario que no depende de un objeto Colegio.
    public static String normalizarId(String id) {
        if (id == null) {
            return null;
        }
        return id.trim().toUpperCase(Locale.ROOT);
    }

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

    // Busca por nombre recorriendo la lista de cursos.
    public Curso buscarCursoPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }

        for (Curso curso : cursos) {
            if (curso.getNombre().equalsIgnoreCase(nombre.trim())) {
                return curso;
            }
        }
        return null;
    }

    
    
    // ===== CONSULTAS =====

    // Devuelve los cursos en los que esta inscrito un alumno.
    public ArrayList<Curso> obtenerCursosAlumno(String alumnoId) {
        ArrayList<Curso> resultado = new ArrayList<>();
        String id = normalizarId(alumnoId);
        if (id == null || id.isEmpty()) {
            return resultado;
        }

        for (Curso curso : cursos) {
            for (Alumno inscrito : curso.getAlumnos()) {
                if (id.equals(normalizarId(inscrito.obtenerId()))) {
                    resultado.add(curso);
                    break;
                }
            }
        }
        return resultado;
    }

    // Devuelve solamente las operaciones relacionadas con el alumno indicado.
    public ArrayList<Operacion> obtenerHistorialAlumno(String alumnoId) {
        ArrayList<Operacion> resultado = new ArrayList<>();
        String id = normalizarId(alumnoId);
        if (id == null || id.isEmpty()) {
            return resultado;
        }

        for (Operacion operacion : historial) {
            if (id.equals(normalizarId(operacion.getIdAlumno()))) {
                resultado.add(operacion);
            }
        }
        return resultado;
    }

    // [REQUISITO: Polimorfismo] Se ordena una copia de los alumnos por promedio.
    public ArrayList<Alumno> obtenerRankingAlumnos() {
        ArrayList<Alumno> ranking = new ArrayList<>(alumnos);
        Collections.sort(ranking);
        return ranking;
    }

    // ===== INSCRIPCIONES Y NOTAS =====

    // [REQUISITO: Herencia] [REQUISITO: Excepciones]
    public void inscribirAlumnoEnCurso(String alumnoId, String cursoId) {
        Alumno alumno = buscarAlumnoPorId(alumnoId);
        Curso curso = buscarCursoPorId(cursoId);

        if (alumno == null) {
            throw new IllegalArgumentException("No existe un alumno con ese ID.");
        }
        if (curso == null) {
            throw new IllegalArgumentException("No existe un curso con ese ID.");
        }

        curso.inscribirAlumno(alumno);
        registrar(TipoOperacion.INSCRIPCION,
                "Alumno " + alumno.getNombre() + " " + alumno.getApellido()
                + " inscrito en curso " + curso.getNombre() + ".",
                alumno.obtenerId());
    }

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
                "Profesor " + profesor.getNombre() + " asignado al curso " + curso.getNombre() + ".");
    }

    public void registrarNota(String alumnoId, double nota) {
        Alumno alumno = buscarAlumnoPorId(alumnoId);
        if (alumno == null) {
            throw new IllegalArgumentException("No existe un alumno con ese ID.");
        }

        alumno.agregarNotas(nota);
        registrar(TipoOperacion.REGISTRO_NOTA,
                "Se registro la nota " + nota + " para el alumno " + alumno.obtenerId() + ".",
                alumno.obtenerId());
    }

    
    
    // ===== PERSISTENCIA =====

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
            System.out.println("No se encontro una clase necesaria para cargar los datos.");
        } catch (ClassCastException e) {
            System.out.println("El archivo no contiene datos validos de un colegio.");
        }

        return null;
    }

    
    
    // ===== HISTORIAL =====

    // Operaciones generales sin alumno asociado.
    private void registrar(TipoOperacion tipo, String descripcion) {
        historial.add(new Operacion(tipo, descripcion));
    }

    // Operaciones que deben aparecer tambien en el perfil de un alumno.
    private void registrar(TipoOperacion tipo, String descripcion, String alumnoId) {
        historial.add(new Operacion(tipo, descripcion, alumnoId));
    }
}
