package colegio.modelo;

import colegio.interfaces.Identificable;
import colegio.interfaces.Rankeable;
import java.util.ArrayList;

public class Alumno extends Persona implements Identificable, Rankeable {

    private ArrayList<Nota> notas;

    public Alumno() {
        super();
        notas = new ArrayList<>();
    }

    @Override
    public String obtenerId() {
        return id;
    }

    @Override
    public double obtenerPuntajeRanking() {
        return 0.0;
    }
}
