package colegio.modelo;

import colegio.enums.NivelCurso;
import colegio.excepciones.CupoLlenoException;
import colegio.interfaces.Identificable;
import java.io.Serializable;
import java.util.ArrayList;

// [REQUISITO: Package] [REQUISITO: Herencia] [REQUISITO: Interface] [REQUISITO: Colecciones] [REQUISITO: Persistencia]
public abstract class Curso implements Identificable, Serializable, Comparable<Curso> {

    private static final long serialVersionUID = 1L;

    // [REQUISITO: Static y Final]
    private static int contadorId = 1;

    protected final String id;
    protected String nombre;
    protected int horasSemanales;
    protected int cupoMaximo;
    protected Profesor profesor;
    protected final ArrayList<Alumno> alumnos;
    protected NivelCurso nivelCurso;

    public Curso(String nombre, int horasSemanales, int cupoMaximo) {
        validarNombre(nombre);
        if (horasSemanales <= 0) {
            throw new IllegalArgumentException("Las horas semanales deben ser positivas.");
        }
        if (cupoMaximo <= 0) {
            throw new IllegalArgumentException("El cupo maximo debe ser positivo.");
        }

        this.id = "CUR-" + contadorId++;
        this.nombre = nombre.trim();
        this.horasSemanales = horasSemanales;
        this.cupoMaximo = cupoMaximo;
        this.alumnos = new ArrayList<>();
        asignarNivelCurso();
    }

    private static void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del curso es obligatorio.");
        }
    }

    // [REQUISITO: Persistencia] 
    public static void sincronizarContadorId(ArrayList<Curso> cursos) {
        contadorId = cursos.size() + 1;
    }

    // [REQUISITO: Excepciones]
    public void inscribirAlumno(Alumno alumno) {
        if (alumno == null) {
            throw new IllegalArgumentException("El alumno no puede ser nulo.");
        }

        for (Alumno inscrito : alumnos) {
            if (inscrito.obtenerId().equalsIgnoreCase(alumno.obtenerId())) {
                throw new IllegalArgumentException("El alumno ya esta inscrito en este curso.");
            }
        }

        if (alumnos.size() >= cupoMaximo) {
            throw new CupoLlenoException(cupoMaximo);
        }

        alumnos.add(alumno);
    }

    // Se calcula automaticamente al crear el curso o cambiar sus horas.
    private void asignarNivelCurso() {
        if (horasSemanales <= 6) {
            nivelCurso = NivelCurso.BASICO;
        } else if (horasSemanales < 12) {
            nivelCurso = NivelCurso.INTERMEDIO;
        } else {
            nivelCurso = NivelCurso.AVANZADO;
        }
    }

    public String getId() {
        return id;
    }

    @Override
    public String obtenerId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHorasSemanales() {
        return horasSemanales;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public ArrayList<Alumno> getAlumnos() {
        return new ArrayList<>(alumnos);
    }

    public NivelCurso getNivelCurso() {
        return nivelCurso;
    }

    public void setNombre(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre.trim();
    }

    public void setHorasSemanales(int horasSemanales) {
        if (horasSemanales <= 0) {
            throw new IllegalArgumentException("Las horas semanales deben ser positivas.");
        }
        this.horasSemanales = horasSemanales;
        asignarNivelCurso();
    }

    public void setProfesor(Profesor profesor) {
        if (profesor == null) {
            throw new IllegalArgumentException("El profesor no puede ser nulo.");
        }
        if (this.profesor != null) {
            throw new IllegalArgumentException("El curso ya tiene un profesor.");
        }
        this.profesor = profesor;
    }

    public void setCupoMaximo(int cupoMaximo) {
        if (cupoMaximo <= 0) {
            throw new IllegalArgumentException("El cupo maximo debe ser positivo.");
        }
        if (cupoMaximo < alumnos.size()) {
            throw new IllegalArgumentException("El cupo no puede ser menor que los alumnos inscritos.");
        }
        this.cupoMaximo = cupoMaximo;
    }

    // [REQUISITO: Polimorfismo] Cada subtipo informa su area y muestra sus datos.
    public abstract String obtenerClasificacionCurso();

    public abstract void mostrarInformacion();

    @Override
    public int compareTo(Curso otro) {
        // Ordena por el numero del ID, no alfabeticamente por el nombre.
        int numeroIdActual = Integer.parseInt(this.id.substring(4));
        int numeroIdOtro = Integer.parseInt(otro.id.substring(4));
        return Integer.compare(numeroIdActual, numeroIdOtro);
    }

    @Override
    public String toString() {
        return id + " / " + obtenerClasificacionCurso() + " / " + nombre + " / " + horasSemanales
                + " horas / cupo " + alumnos.size() + " de " + cupoMaximo + " / " + nivelCurso;
    }
}
