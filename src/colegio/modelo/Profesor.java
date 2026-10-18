package colegio.modelo;

import colegio.interfaces.Identificable;

public class Profesor extends Persona implements Identificable {

    private String especialidad;

    public Profesor(String id, String nombre, String apellido, String especialidad) {
        super(id, nombre, apellido);
        this.especialidad = especialidad;
    }


    @Override
    public String obtenerId() {
        return id;
    }
}

