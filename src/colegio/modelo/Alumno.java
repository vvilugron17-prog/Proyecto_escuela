package colegio.modelo;

import colegio.excepciones.NotaInvalidaException;
import colegio.interfaces.Rankeable;
import java.util.ArrayList;
import java.util.Collections;

// Alumno con matrícula, notas y cálculo de promedio.
public class Alumno extends Persona implements Rankeable {
    private static final long serialVersionUID = 1L;

    private String matricula;
    private ArrayList<Double> notas;
    private double promedioNotas = 0;

    // Constructor original sin parámetros.
    public Alumno() {
        super();
        this.matricula = "";
        this.notas = new ArrayList<>();
    }

    public Alumno(String id, String nombre, String apellido, int edad, String matricula) {
        super(id, nombre, apellido, edad);
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("La matrícula no puede estar vacía.");
        }
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa.");
        }
        this.matricula = matricula.trim();
        this.notas = new ArrayList<>();
    }

    // Método original que agrega una nota y recalcula el promedio.
    public void agregarNotas(double nota) {
        if (Double.isNaN(nota) || Double.isInfinite(nota) || nota < 1.0 || nota > 7.0) {
            throw new IllegalArgumentException("La nota debe estar entre 1.0 y 7.0.");
        }
        notas.add(nota);
        recalcularPromedio();
    }

    // Agrega una nota utilizando una excepción propia para validar el valor.
    public void agregarNota(double nota) throws NotaInvalidaException {
        if (Double.isNaN(nota) || Double.isInfinite(nota) || nota < 1.0 || nota > 7.0) {
            throw new NotaInvalidaException("La nota debe estar entre 1.0 y 7.0.");
        }
        notas.add(nota);
        recalcularPromedio();
    }

    // Calcula el promedio de una lista de notas entregada como parámetro.
    public void promedioNotas(ArrayList<Double> notas) {
        if (notas == null || notas.isEmpty()) {
            System.out.println("El alumno no tiene notas.");
            promedioNotas = 0;
            return;
        }
        double sumaNotas = 0;
        for (double nota : notas) {
            sumaNotas += nota;
        }
        this.promedioNotas = sumaNotas / notas.size();
    }

    // Recalcula el promedio utilizando las notas almacenadas del alumno.
    private void recalcularPromedio() {
        if (notas.isEmpty()) {
            promedioNotas = 0;
            return;
        }
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        promedioNotas = suma / notas.size();
    }

    // Devuelve el promedio actual de las notas.
    public double getPromedioNotas() {
        return promedioNotas;
    }

    // Devuelve la matrícula del alumno.
    public String getMatricula() {
        return matricula;
    }

    // Modifica la matrícula después de validar que no esté vacía.
    public void setMatricula(String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("La matrícula no puede estar vacía.");
        }
        this.matricula = matricula.trim();
    }

    // Devuelve una copia de la lista de notas del alumno.
    public ArrayList<Double> getNotas() {
        return new ArrayList<>(notas);
    }

    // Devuelve el promedio del alumno como puntaje para el ranking.
    @Override
    public double obtenerPuntajeRanking() {
        return calcularPromedio();
    }

    // Calcula y devuelve el promedio de todas las notas del alumno.
    public double calcularPromedio() {
        if (notas == null || notas.isEmpty()) {
            return promedioNotas;
        }
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        promedioNotas = suma / notas.size();
        return promedioNotas;
    }

    // Muestra los datos personales, la matrícula, el promedio y las notas.
    @Override
    public void mostrarInformacion() {
        System.out.println("Alumno: " + nombre + " " + apellido
                + " | ID: " + id + " | Matrícula: " + matricula
                + " | Edad: " + edad + " | Promedio: "
                + String.format(java.util.Locale.US, "%.2f", calcularPromedio())
                + " | Notas: " + (notas == null ? Collections.emptyList() : notas));
    }

    // Devuelve una representación del alumno en forma de texto.
    @Override
    public String toString() {
        return "Alumno{" + "id='" + id + '\'' + ", nombre='" + nombre + ' ' + apellido
                + "', matricula='" + matricula + '\'' + ", promedio="
                + String.format(java.util.Locale.US, "%.2f", calcularPromedio()) + '}';
    }
}