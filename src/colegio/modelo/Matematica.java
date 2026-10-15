package colegio.modelo;

public class Matematica extends Curso {

    private final double porcentajeEvaluaciones;
    private final double porcentajeTareas;
    
    public Matematica(String nombre, int horasSemanales, int cupoMaximo, double porcentajeEvaluaciones, double porcentajeTareas){
        super(nombre, horasSemanales, cupoMaximo);
        this.porcentajeEvaluaciones = porcentajeEvaluaciones;
        this.porcentajeTareas = porcentajeTareas;
    }
    
    @Override
    public void agregarAlumnos(Alumno alumnoNuevo){ //Método abstracto para añadir alumnos a la lista de un curso, de parámetros se coloca la clase Alumno y su instancia alumnoNuevo
        alumnos.add(alumnoNuevo);
    }

    @Override
    public void agregarPromedioNotas(Alumno alumnoNuevo) {
        double promedioAlumno = alumnoNuevo.getPromedioNotas();
        promedioNotas.add(promedioAlumno);
        
        
    }

    @Override
    public double obtenerPuntajeRanking() {
    
    }
    
    @Override
    public double calcularPonderacion() {
        return this.porcentajeEvaluaciones + this.porcentajeTareas;
    }
}





