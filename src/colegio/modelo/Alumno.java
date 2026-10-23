package colegio.modelo;

import colegio.excepciones.NotaInvalidaException;
import java.util.ArrayList;

// [REQUISITO: Herencia]
public class Alumno extends Persona implements Comparable<Alumno> {

    // [REQUISITO: Persistencia]
    private static final long serialVersionUID = 1L;

    private ArrayList<Double> notas;
    private double promedioNotas;

    public Alumno(String id, String nombre, String apellido) {
        super(id, nombre, apellido);
        this.notas = new ArrayList<>();
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
}
