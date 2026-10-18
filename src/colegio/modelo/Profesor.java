package colegio.modelo;

import colegio.interfaces.Identificable;

public class Profesor extends Persona implements Identificable {

    private String especialidad;

    public Profesor() {
        super();
    }

    @Override
    public String obtenerId() {
        return id;
    }
}

