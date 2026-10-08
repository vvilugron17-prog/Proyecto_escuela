package proyecto;

public class Alumnos {
    private String rut;
    private String nombre;
    private double notas;
    
    public Alumnos(String rut, String nombre, double notas) {
        this.rut = rut;
        this.nombre = nombre;
        this.notas = notas;
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public double getNotas() {
        return notas;
    }
    
    
    
}
