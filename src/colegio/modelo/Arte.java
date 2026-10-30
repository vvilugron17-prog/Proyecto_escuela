package colegio.modelo;

// [REQUISITO: Package] [REQUISITO: Herencia] [REQUISITO: Persistencia]
public class Arte extends Curso {

    private static final long serialVersionUID = 1L;

    private final int cantidadEvaluaciones;
    private final int cantidadTareas;

    public Arte(String nombre, int horasSemanales, int cupoMaximo,
            int cantidadEvaluaciones, int cantidadTareas) {
        super(nombre, horasSemanales, cupoMaximo);

        if (cantidadEvaluaciones < 0 || cantidadTareas < 0) {
            throw new IllegalArgumentException(
                    "La cantidad de evaluaciones y tareas no puede ser negativa.");
        }

        this.cantidadEvaluaciones = cantidadEvaluaciones;
        this.cantidadTareas = cantidadTareas;
    }

    public int getCantidadEvaluaciones() {
        return cantidadEvaluaciones;
    }

    public int getCantidadTareas() {
        return cantidadTareas;
    }

    @Override
    public String obtenerClasificacionCurso() {
        return "Artes";
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(id + " / Artes / " + nombre + " / " + horasSemanales
                + " horas / cupo " + alumnos.size() + " de " + cupoMaximo
                + " / evaluaciones " + cantidadEvaluaciones
                + " / tareas " + cantidadTareas + " / " + nivelCurso);
    }
}
