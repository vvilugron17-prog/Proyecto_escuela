package colegio.modelo;

import java.io.Serializable;

// [REQUISITO: Package] [REQUISITO: Herencia] [REQUISITO: Persistencia]
public abstract class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String nombre;
    protected String apellido;

    public Persona(String nombre, String apellido) {
        this.nombre = nombre.trim();
        this.apellido = apellido.trim();
    }


    public abstract void mostrarInformacion();

    public void setNombre(String nombre) {
        this.nombre = nombre.trim();
    }

    public void setApellido(String apellido) {
        this.apellido = apellido.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
}
