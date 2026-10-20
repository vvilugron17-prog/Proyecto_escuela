package colegio.modelo;

import java.io.Serializable;

// [REQUISITO: Persistencia]
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String id;
    protected String nombre;
    protected String apellido;

    public Persona(String id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
}
