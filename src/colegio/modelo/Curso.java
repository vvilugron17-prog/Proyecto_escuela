package colegio.modelo;

import colegio.interfaces.Identificable;
import java.util.ArrayList;

public abstract class Curso implements Identificable {

    protected String id;
    protected String nombre;
    protected int cupoMaximo;
    protected Profesor profesor;

    protected ArrayList<Alumno> alumnos;

    public Curso() {
        alumnos = new ArrayList<>();
    }

    public abstract double calcularPonderacion();

    @Override
    public String obtenerId() {
        return id;
    }
}