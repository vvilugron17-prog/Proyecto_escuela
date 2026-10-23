package colegio.modelo;

public class Profesor extends Persona{

    // [REQUISITO: Persistencia]
    private static final long serialVersionUID = 1L;

    private String especialidad;

    public Profesor(String id, String nombre, String apellido, String especialidad) {
        super(id, nombre, apellido);
        this.especialidad = especialidad;
    }


    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(id + "/" + nombre + "/" + apellido + "/ especialidad");
    }
}

