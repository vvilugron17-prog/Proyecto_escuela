package colegio.modelo;

import colegio.interfaces.Identificable;
import java.util.ArrayList;

public class Alumno extends Persona implements Identificable {

    private ArrayList<Double> notas;
    private double promedioNotas;    

    public Alumno() {
        super();
        this.notas = new ArrayList<>();
    }

    public void agregarNotas(double nota){ //Método para agregar una nota a un alumno
        notas.add(nota);
        System.out.println("La nota ha sido agregada");
    }
    
    
    public void promedioNotas(ArrayList<Double> notas){ //Método para calcular el promedio de notas de un alumno
        double promedio;
        
        if(notas == null || notas.size() <= 1){ 
            System.out.println("El alumno no tiene notas suficientes para calcular su promedio");
            promedio = 0;
        }
        else{   
            double sumaNotas = 0;
            for (int i = 0; i < notas.size(); i++){
                sumaNotas += notas.get(i);
            }
            promedio = (sumaNotas / notas.size()); 
            
        }
        this.promedioNotas = promedio;
    }
    
    @Override //Interfaz de obtener ID
    public String obtenerId() {
        return id;
    }
    
    ///----------- Getters ---------------///

    public double getPromedioNotas() {
        return promedioNotas;
    }
    
    

}
