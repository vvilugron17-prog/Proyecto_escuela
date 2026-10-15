package colegio.modelo;

import colegio.enums.NivelCurso;

// Curso de matemática, con ponderación para evaluaciones y tareas.
public class Matematica extends Curso {
    private static final long serialVersionUID = 1L;

    private double porcentajePorEvaluacion = 0;
    private double porcentajePorTarea = 0;
    private final int cantidadEvaluaciones;
    private final int cantidadTareas;

    // Constructor original conservado.
    public Matematica(String nombre, int horasSemanales, int cupoMaximo,
            int cantidadEvaluaciones, int cantidadTareas) {
        this(nombre, horasSemanales, cupoMaximo, cantidadEvaluaciones,
                cantidadTareas, NivelCurso.INTERMEDIO);
    }

    // Constructor sobrecargado para seleccionar el nivel sin perder el original.
    public Matematica(String nombre, int horasSemanales, int cupoMaximo,
            int cantidadEvaluaciones, int cantidadTareas, NivelCurso nivel) {
        super(nombre, horasSemanales, cupoMaximo, nivel);

        if (cantidadEvaluaciones < 1 || cantidadTareas < 1) {
            throw new IllegalArgumentException(
                    "Debe existir al menos una evaluación y una tarea.");
        }

        this.cantidadEvaluaciones = cantidadEvaluaciones;
        this.cantidadTareas = cantidadTareas;
    }

    // Devuelve el porcentaje asignado a cada evaluación.
    public double getPorcentajePorEvaluacion() {
        return porcentajePorEvaluacion;
    }

    // Devuelve el porcentaje asignado a cada tarea.
    public double getPorcentajePorTarea() {
        return porcentajePorTarea;
    }

    // Devuelve la cantidad de evaluaciones del curso.
    public int getCantidadEvaluaciones() {
        return cantidadEvaluaciones;
    }

    // Devuelve la cantidad de tareas del curso.
    public int getCantidadTareas() {
        return cantidadTareas;
    }

    // Calcula y guarda la ponderación de las evaluaciones y las tareas.
    @Override
    public void calcularPonderacion(double porcentajeEvaluaciones, double porcentajeTareas) {
        if (porcentajesValidos(porcentajeEvaluaciones, porcentajeTareas)) {
            porcentajePorEvaluacion = porcentajeEvaluaciones / cantidadEvaluaciones;
            porcentajePorTarea = porcentajeTareas / cantidadTareas;
            System.out.println("Ponderación de Matematica guardada.");
        } else {
            System.out.println("Error: los porcentajes deben ser no negativos y sumar 100.");
        }
    }

    private boolean porcentajesValidos(double evaluaciones, double tareas) {
    return evaluaciones >= 0 && tareas >= 0 && evaluaciones + tareas == 100;
}

    // Muestra la ponderación configurada para el curso de Matemática.
    @Override
    public void datosPonderacion() {
        System.out.println("Matematica - " + nombre);
        System.out.println("Cada evaluación vale "
                + String.format(java.util.Locale.US, "%.2f", porcentajePorEvaluacion) + "%.");
        System.out.println("Cada tarea vale "
                + String.format(java.util.Locale.US, "%.2f", porcentajePorTarea) + "%.");
    }

    // Devuelve la cantidad de horas semanales del curso.
    @Override
    public double calcularCargaHoraria() {
        return horasSemanales;
    }

    // Devuelve el puntaje del curso para calcular el ranking.
    @Override
    public double obtenerPuntajeRanking() {
        return super.obtenerPuntajeRanking();
    }
}