package colegio.modelo;

import colegio.interfaces.Identificable;
import java.util.ArrayList;
public class Profesor extends Persona implements Identificable{

    // [REQUISITO: Persistencia]
    private static final long serialVersionUID = 1L;
    
    private static int contadorId = 1;
    private String id;
    private String especialidad;

    public Profesor(String nombre, String apellido, String especialidad) {
        super(nombre, apellido);
        this.id = "P" + contadorId++;
        this.especialidad = especialidad;
    }

    public static void sincronizarContadorId(ArrayList<Profesor> profesores) {
        contadorId = profesores.size() + 1;
    }
    
    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(id + "/" + nombre + "/" + apellido + "/" + especialidad);
    }
    
    @Override
    public String obtenerId(){
        return id;
    }
}

