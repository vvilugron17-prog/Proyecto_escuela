package colegio.modelo;

import colegio.interfaces.Identificable;
import java.util.ArrayList;

// [REQUISITO: Herencia]
public class Alumno extends Persona implements Identificable, Comparable<Alumno> {

    private ArrayList<Double> notas;
    private double promedioNotas;

    public Alumno(String id, String nombre, String apellido) {
        super(id, nombre, apellido);
        this.notas = new ArrayList<>();
    }

    public void agregarNotas(double nota) {
        notas.add(nota);
        calcularPromedioNotas(notas);
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

    @Override
    public String obtenerId() {
        return id;
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
        int resultadoPromedio = Double.compare(otro.getPromedioNotas(), this.getPromedioNotas());
        if (resultadoPromedio != 0) {
            return resultadoPromedio;
        }

        int resultadoApellido = this.getApellido().compareToIgnoreCase(otro.getApellido());
        if (resultadoApellido != 0) {
            return resultadoApellido;
        }

        return this.getNombre().compareToIgnoreCase(otro.getNombre());
    }
}
