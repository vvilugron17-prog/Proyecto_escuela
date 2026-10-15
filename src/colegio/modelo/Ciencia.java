package colegio.modelo;

public class Ciencia extends Curso {

    private final double porcentajeEvaluaciones;
    private final double porcentajeTareas;
    
    public Ciencia(String nombre, int horasSemanales, int cupoMaximo, double porcentajeEvaluaciones, double porcentajeTareas){
        super(nombre, horasSemanales, cupoMaximo);
        this.porcentajeEvaluaciones = porcentajeEvaluaciones;
        this.porcentajeTareas = porcentajeTareas;
    }
    
    @Override
    public void agregarAlumnos(Alumno alumnoNuevo){
        alumnos.add(alumnoNuevo);
    }

    @Override
    public double obtenerPuntajeRanking() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void agregarPromedioNotas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public double calcularPonderacion() {
        return this.porcentajeEvaluaciones + this.porcentajeTareas;
    }
}