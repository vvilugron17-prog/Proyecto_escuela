package colegio.modelo;

import colegio.excepciones.CupoLlenoException;
import colegio.interfaces.Identificable;
import colegio.interfaces.Rankeable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

// [REQUISITO: Persistencia] [REQUISITO: Herencia]
public abstract class Curso implements Identificable, Rankeable, Serializable, Comparable<Curso> {

    private static final long serialVersionUID = 1L;

    private static int contadorId = 1;
    protected String id;
    protected String nombre;
    protected int horasSemanales;
    protected int cupoMaximo;
    protected Profesor profesor;
    protected ArrayList<Alumno> alumnos; // Lista para guardar a todos los alumnos de un curso
    
    public Curso(String nombre, int horasSemanales, int cupoMaximo) {
        this.id = "CUR-" + contadorId++;
        this.nombre = nombre;
        this.horasSemanales = horasSemanales;
        this.cupoMaximo = cupoMaximo;
        alumnos = new ArrayList<>();
    }

    // [REQUISITO: Persistencia] 
    public static void sincronizarContadorId(ArrayList<Curso> cursos) {
    contadorId = cursos.size() + 1;
}

    // [REQUISITO: Excepciones]
    public void inscribirAlumno(Alumno alumno) throws CupoLlenoException {
        if (alumnos.size() >= cupoMaximo) {
            throw new CupoLlenoException(cupoMaximo);
        }
        alumnos.add(alumno);
        System.out.println("El alumno fue agregado al curso correctamente");
    }
    

    
    ///----------- Getters ---------------///
    
    public String getId(){
        return id;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public int getHorasSemanales(){
        return horasSemanales;
    }

    public int getCupoMaximo(){
        return cupoMaximo;
    }
    
    public Profesor getProfesor(){
        return profesor;
    }
    
    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }
    
    ///------------- Setters ------------------///
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setHorasSemanales(int horasSemanales) {
        this.horasSemanales = horasSemanales;
    }
    
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    
    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }
    
    public abstract void calcularPonderacion(double porcentajeEvaluaciones, double porcentajeTareas); //Método abstracto para calcular la ponderación de cada curso
    
    public abstract void informacionPonderacion(); //Método abstracto para mostrar por pantalla la ponderación de cada curso

    @Override
    public String obtenerId() { //Interfaz que devuelve el ID del curso
        return id;
    }
   
    
    @Override
    public void obtenerPuntajeRanking(ArrayList<Alumno> alumnos) {
        if (alumnos == null) {
            throw new IllegalArgumentException("La lista de alumnos no puede ser nula.");
        }
        // Implementa comparable
        Collections.sort(alumnos);
    }

    // Ordena por orden alfabetico
    @Override
    public int compareTo(Curso otro) {
        return this.nombre.compareToIgnoreCase(otro.nombre);
    }
    
    public abstract void mostrarInformacion();
}