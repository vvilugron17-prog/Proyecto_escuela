package colegio.gestion;

import colegio.enums.TipoOperacion;
import colegio.interfaces.Identificable;
import colegio.modelo.Alumno;
import colegio.modelo.Curso;
import colegio.modelo.Operacion;
import colegio.modelo.Profesor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Colegio implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String NOMBRE_COLEGIO = "Zapallar";

    // Listas.
    private final ArrayList<Alumno> alumnos = new ArrayList<>();
    private final ArrayList<Profesor> profesores = new ArrayList<>();
    private final ArrayList<Curso> cursos = new ArrayList<>();
    private final ArrayList<Operacion> historial = new ArrayList<>();

    // HashMaps.
    private final HashMap<String, Alumno> alumnosPorId = new HashMap<>();
    private final HashMap<String, Profesor> profesoresPorId = new HashMap<>();
    private final HashMap<String, Curso> cursosPorId = new HashMap<>();

    // Getters.
    public String getNombreColegio() {
        return NOMBRE_COLEGIO;
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public ArrayList<Operacion> getHistorial() {
        return historial;
    }

    // Método genérico para registrar alumnos, profesores y cursos.
    private <Generico extends Identificable> void Generico(
            Generico objeto,
            ArrayList<Generico> lista,
            HashMap<String, Generico> mapa,
            TipoOperacion tipo,
            String nombreTipo) {

        if (objeto == null) {
            throw new IllegalArgumentException(
                    "Debe indicar un " + nombreTipo + " válido.");
        }

        String id = objeto.obtenerId();

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El ID del " + nombreTipo + " no es válido.");
        }

        id = clave(id);

        if (mapa.containsKey(id)) {
            throw new IllegalArgumentException(
                    "Ya existe un " + nombreTipo + " con ese ID.");
        }

        lista.add(objeto);
        mapa.put(id, objeto);

        String descripcion = "Se registró el " + nombreTipo
                + " con ID " + id;

        registrar(tipo, descripcion);
    }

    // Registrar un alumno.
    public void agregarAlumno(Alumno alumno) {
        Generico(
                alumno,
                alumnos,
                alumnosPorId,
                TipoOperacion.REGISTRO_ALUMNO,
                "alumno"
        );
    }

    // Registrar un profesor.
    public void agregarProfesor(Profesor profesor) {
        Generico(
                profesor,
                profesores,
                profesoresPorId,
                TipoOperacion.REGISTRO_PROFESOR,
                "profesor"
        );
    }

    // Registrar un curso.
    public void agregarCurso(Curso curso) {
        Generico(
                curso,
                cursos,
                cursosPorId,
                TipoOperacion.REGISTRO_CURSO,
                "curso"
        );
    }

    // Normalizar identificadores. Los limpia de espacios y los pasa a mayusculas.
    private String clave(String id) {
        return id.trim().toUpperCase(java.util.Locale.ROOT);
    }

    // Registrar operaciones en el historial.
    private void registrar(TipoOperacion tipo, String descripcion) {
        Operacion operacion = new Operacion(tipo, descripcion);
        historial.add(operacion);
    }
}