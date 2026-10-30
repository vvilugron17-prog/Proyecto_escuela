package colegio.modelo;

import colegio.interfaces.Identificable;
import java.util.ArrayList;

// [REQUISITO: Package] [REQUISITO: Herencia] [REQUISITO: Interface] [REQUISITO: Persistencia]
public class Profesor extends Persona implements Identificable {

    private static final long serialVersionUID = 1L;

    // [REQUISITO: Static y Final]
    private static int contadorId = 1;

    private final String id;
    private String especialidad;

    public Profesor(String nombre, String apellido, String especialidad) {
        super(nombre, apellido);
        this.id = "P" + contadorId++;
        this.especialidad = especialidad.trim();
    }

    public static void sincronizarContadorId(ArrayList<Profesor> profesores) {
        contadorId = profesores.size() + 1;
    }


    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad.trim();
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(id + " / " + nombre + " / " + apellido + " / " + especialidad);
    }

    @Override
    public String obtenerId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " / " + nombre + " / " + apellido + " / " + especialidad;
    }
}
