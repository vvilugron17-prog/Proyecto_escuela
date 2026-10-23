package colegio.modelo;

import colegio.interfaces.Identificable;
import colegio.excepciones.NotaInvalidaException;
import java.util.ArrayList;

// [REQUISITO: Herencia]
public class Alumno extends Persona implements Identificable, Comparable<Alumno> {

    // [REQUISITO: Persistencia]
    private static final long serialVersionUID = 1L;
    private static int contadorId = 1;
    private String id;
    private ArrayList<Double> notas;
    private double promedioNotas;

    public Alumno(String nombre, String apellido) {
        super(nombre, apellido);
        this.id = "A" + contadorId++;
        this.notas = new ArrayList<>();
    }
    
    
    public static void sincronizarContadorId(ArrayList<Alumno> alumnos){
        contadorId = alumnos.size() + 1;
        
    }
   
    public void agregarNotas(double nota) {
        if (nota < 1.0 || nota > 7.0) {
            throw new NotaInvalidaException("La nota debe estar entre 1,0 y 7,0.");
        }

        notas.add(nota);
        calcularPromedioNotas(notas); //Esto para que cuando se agregue una nueva nota, el promedio se actualice con la nueva nota ingresada
        System.out.println("La nota ha sido agregada");
    }

    public void calcularPromedioNotas(ArrayList<Double> notas) {
        if (notas == null || notas.isEmpty()) {
            promedioNotas = 0;
            return;
        }

        double sumaNotas = 0;
        for (Double nota : notas) {
            sumaNotas += nota;
        }
        promedioNotas = sumaNotas / notas.size();
    }

    public double getPromedioNotas() {
        return promedioNotas;
    }

    public ArrayList<Double> getNotas() {
        return new ArrayList<>(notas);
    }

    // Promedio más alto primero, luego apellido y nombre.
    @Override
    public int compareTo(Alumno otro) {
        int resultadoPromedio = Double.compare(otro.getPromedioNotas(), this.getPromedioNotas()); // Compara el promedio final entre alumnos
        if (resultadoPromedio != 0) {
            return resultadoPromedio;
        }

        int resultadoApellido = this.getApellido().compareToIgnoreCase(otro.getApellido());
        if (resultadoApellido != 0) {
            return resultadoApellido;
        }

        return this.getNombre().compareToIgnoreCase(otro.getNombre());
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(id + "/" + nombre + "/" + apellido);
    }
    
    @Override
    public String obtenerId(){
        return id;
    }
}
