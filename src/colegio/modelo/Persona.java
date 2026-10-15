package colegio.modelo;

import colegio.interfaces.Identificable;
import java.io.Serializable;

// Datos comunes a estudiantes y profesores.
public abstract class Persona implements Identificable, Serializable {
    private static final long serialVersionUID = 1L;

    protected String id;
    protected String nombre;
    protected String apellido;
    protected int edad;

    // Constructor conservado para compatibilidad con el código original.
    public Persona() {
        this("", "", "", 0);
    }

    // Constructor que inicializa los datos de la persona.
    public Persona(String id, String nombre, String apellido, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    // Devuelve el identificador de la persona.
    @Override
    public String obtenerId() {
        return id;
    }

    // Devuelve el identificador de la persona.
    public String getId() {
        return id;
    }

    // Devuelve el nombre de la persona.
    public String getNombre() {
        return nombre;
    }

    // Devuelve el apellido de la persona.
    public String getApellido() {
        return apellido;
    }

    // Devuelve la edad de la persona.
    public int getEdad() {
        return edad;
    }

    // Modifica el nombre de la persona.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Modifica el apellido de la persona.
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Modifica la edad de la persona y verifica que no sea negativa.
    public void setEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa.");
        }
        this.edad = edad;
    }

    // Método abstracto que muestra la información de la persona.
    public abstract void mostrarInformacion();
}