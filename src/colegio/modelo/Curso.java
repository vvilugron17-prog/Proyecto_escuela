package colegio.modelo;

import colegio.enums.NivelCurso;
import colegio.excepciones.CupoLlenoException;
import colegio.interfaces.Identificable;
import colegio.interfaces.Rankeable;
import java.io.Serializable;
import java.util.ArrayList;

// Clase base de Matemática, Ciencia y Arte. Mantiene los métodos del proyecto original.
public abstract class Curso implements Identificable, Rankeable, Serializable {
    private static final long serialVersionUID = 1L;
    private static int contadorId = 1;

    protected String id;
    protected String nombre;
    protected String descripcion;
    protected int horasSemanales;
    protected int cupoMaximo;
    protected NivelCurso nivel;
    protected Profesor profesor;
    protected ArrayList<Alumno> alumnos;

    // Lista que existía en el proyecto original; se mantiene para compatibilidad.
    protected ArrayList<Alumno> promedioNotas;

    // Constructor original que asigna el nivel intermedio por defecto.
    public Curso(String nombre, int horasSemanales, int cupoMaximo) {
        this(nombre, horasSemanales, cupoMaximo, NivelCurso.INTERMEDIO);
    }

    // Constructor que inicializa el curso con un nivel específico.
    public Curso(String nombre, int horasSemanales, int cupoMaximo, NivelCurso nivel) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del curso no puede estar vacío.");
        }
        if (horasSemanales < 1) {
            throw new IllegalArgumentException("Las horas semanales deben ser mayores que cero.");
        }
        if (cupoMaximo < 1) {
            throw new IllegalArgumentException("El cupo máximo debe ser mayor que cero.");
        }

        this.id = generarId();
        this.nombre = nombre.trim();
        this.horasSemanales = horasSemanales;
        this.cupoMaximo = cupoMaximo;
        this.nivel = nivel == null ? NivelCurso.INTERMEDIO : nivel;
        this.descripcion = "Curso de " + nombre.trim();
        this.alumnos = new ArrayList<>();
        this.promedioNotas = new ArrayList<>();
    }

    // Genera un identificador único para cada curso.
    private static String generarId() {
        return "CUR-" + String.format(java.util.Locale.US, "%03d", contadorId++);
    }

    // Avanza el contador de identificadores después de cargar cursos desde un archivo.
    public static void sincronizarContadorId(Iterable<Curso> cursos) {
        int mayor = 0;

        if (cursos != null) {
            for (Curso curso : cursos) {
                if (curso != null && curso.id != null && curso.id.startsWith("CUR-")) {
                    try {
                        mayor = Math.max(mayor, Integer.parseInt(curso.id.substring(4)));
                    } catch (NumberFormatException ignorada) {
                        // Se ignoran los identificadores que tengan un formato antiguo.
                    }
                }
            }
        }

        contadorId = Math.max(contadorId, mayor + 1);
    }

    // Inscribe a un alumno si no está repetido y todavía hay cupos disponibles.
    public void inscribirAlumno(Alumno alumnoNuevo) throws CupoLlenoException {
        if (alumnoNuevo == null) {
            throw new IllegalArgumentException("El alumno no puede ser nulo.");
        }

        for (Alumno alumno : alumnos) {
            if (alumno.obtenerId().equalsIgnoreCase(alumnoNuevo.obtenerId())) {
                throw new IllegalArgumentException("El alumno ya está inscrito en este curso.");
            }
        }

        if (alumnos.size() >= cupoMaximo) {
            throw new CupoLlenoException(
                    "El curso " + nombre + " ha alcanzado su cupo máximo de " + cupoMaximo + ".");
        }

        alumnos.add(alumnoNuevo);
        promedioNotas.add(alumnoNuevo);
    }

    // Devuelve el identificador del curso.
    public String getId() {
        return id;
    }

    // Devuelve el identificador mediante el método de la interfaz Identificable.
    @Override
    public String obtenerId() {
        return id;
    }

    // Devuelve el nombre del curso.
    public String getNombre() {
        return nombre;
    }

    // Devuelve las horas semanales del curso.
    public int getHorasSemanales() {
        return horasSemanales;
    }

    // Devuelve la descripción del curso.
    public String getDescripcion() {
        return descripcion;
    }

    // Devuelve el profesor asignado al curso.
    public Profesor getProfesor() {
        return profesor;
    }

    // Devuelve la lista de alumnos inscritos en el curso.
    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    // Devuelve el cupo máximo permitido para el curso.
    public int getCupoMaximo() {
        return cupoMaximo;
    }

    // Calcula cuántos cupos quedan disponibles.
    public int getCuposDisponibles() {
        return cupoMaximo - alumnos.size();
    }

    // Devuelve el nivel del curso.
    public NivelCurso getNivel() {
        return nivel;
    }

    // Devuelve la cantidad de alumnos inscritos.
    public int getCantidadAlumnos() {
        return alumnos.size();
    }

    // Modifica el nombre después de comprobar que no esté vacío.
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    // Modifica las horas semanales después de validar que sean mayores que cero.
    public void setHorasSemanales(int horasSemanales) {
        if (horasSemanales < 1) {
            throw new IllegalArgumentException("Las horas semanales deben ser mayores que cero.");
        }
        this.horasSemanales = horasSemanales;
    }

    // Asigna el profesor responsable del curso.
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    // Asigna un profesor al curso.
    public void asignarProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    // Modifica la descripción del curso.
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Modifica el cupo máximo sin permitir que sea menor que los alumnos inscritos.
    public void setCupoMaximo(int cupoMaximo) {
        if (cupoMaximo < alumnos.size() || cupoMaximo < 1) {
            throw new IllegalArgumentException(
                    "El nuevo cupo no puede ser menor que los alumnos inscritos.");
        }
        this.cupoMaximo = cupoMaximo;
    }

    // Modifica el nivel del curso.
    public void setNivel(NivelCurso nivel) {
        this.nivel = nivel;
    }

    // Define el método que cada tipo de curso debe implementar para calcular sus ponderaciones.
    public abstract void calcularPonderacion(double porcentajeEvaluaciones, double porcentajeTareas);

    // Define el método que cada tipo de curso debe implementar para mostrar sus ponderaciones.
    public abstract void datosPonderacion();

    // Calcula el promedio general de los alumnos que tienen notas para utilizarlo en el ranking.
    @Override
    public double obtenerPuntajeRanking() {
        double suma = 0;
        int cantidad = 0;

        for (Alumno alumno : alumnos) {
            if (alumno.getNotas() != null && !alumno.getNotas().isEmpty()) {
                suma += alumno.calcularPromedio();
                cantidad++;
            }
        }

        return cantidad == 0 ? 0.0 : suma / cantidad;
    }

    // Devuelve las horas semanales para demostrar el polimorfismo entre los tipos de curso.
    public double calcularCargaHoraria() {
        return horasSemanales;
    }

    // Devuelve una representación del curso con sus datos principales.
    @Override
    public String toString() {
        return id + " | " + nombre + " | " + getClass().getSimpleName()
                + " | Nivel: " + nivel + " | Horas: " + horasSemanales
                + " | Cupos: " + alumnos.size() + "/" + cupoMaximo
                + " | Profesor: " + (profesor == null
                ? "Sin asignar"
                : profesor.getNombre() + " " + profesor.getApellido());
    }
}

