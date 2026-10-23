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
    
    //Aún no funciona ni lo implementamos
    @Override
    public void calcularPonderacion(double porcentajeEvaluaciones, double porcentajeTareas) {
        if (porcentajeEvaluaciones + porcentajeTareas == 100) { 
            this.porcentajeEvaluaciones = porcentajeEvaluaciones;
            this.porcentajeTareas = porcentajeTareas;
        }
        else{
            System.out.println("Los porcentajes sumados no forman el 100%");
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




