package colegio.modelo;

public class Matematica extends Curso {

    // [REQUISITO: Persistencia]
    private static final long serialVersionUID = 1L;

    private double porcentajeEvaluaciones;
    private double porcentajeTareas;
    private final int cantidadEvaluaciones;
    private final int cantidadTareas;
    // [REQUISITO: Final].
    
    public Matematica(String nombre, int horasSemanales, int cupoMaximo,int cantidadEvaluaciones, int cantidadTareas){
        super(nombre, horasSemanales, cupoMaximo);
        this.cantidadEvaluaciones = cantidadEvaluaciones;
        this.cantidadTareas = cantidadTareas;
    }
    
    //Se inializan los porcentajes de ponderación en el método para que así se asegure de que llegue a un 100%
    @Override
    public void calcularPonderacion(double porcentajeEvaluaciones, double porcentajeTareas) {
        if (porcentajeEvaluaciones + porcentajeTareas == 100) { //Si el porcentaje entre Tareas y evaluaciones forman el 100%, los atributos de porcentaje tomarán esos valores.
            this.porcentajeEvaluaciones = porcentajeEvaluaciones;
            this.porcentajeTareas = porcentajeTareas;
        }
        else{
            System.out.println("Los porcentajes sumados no forman el 100%"); //Si no forma el 100%, los atributos de porcentaje seguirán sin valor.
        }
               
    }

    @Override 
    public void informacionPonderacion() {
        System.out.println("El curso de " + nombre + " tiene:");
        System.out.println(cantidadEvaluaciones + " que valen un " + porcentajeEvaluaciones + "% del curso");
        System.out.println(cantidadTareas + " que valen un " + porcentajeTareas + "% del curso");
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(id + "/" + nombre + "/" + horasSemanales + "/" + cupoMaximo + "/" + cantidadEvaluaciones + "/" + cantidadTareas);
    }
}




