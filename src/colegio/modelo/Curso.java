package colegio.modelo;

import colegio.excepciones.CupoLlenoException;
import colegio.interfaces.Identificable;
import colegio.interfaces.Rankeable;
import java.util.ArrayList;

public abstract class Curso implements Identificable, Rankeable {

    private static int contadorId = 1;
    protected String id;
    protected String nombre;
    protected String descripcion;
    protected int horasSemanales;
    protected int cupoMaximo;
    protected Profesor profesor;
    protected ArrayList<Alumno> alumnos; //Lista para guardar a todos los alumnos de un curso
    protected ArrayList<Alumno> promedioNotas; //Lista para guardar el promedio de notas de todos los alumnos del curso
    
    public Curso(String nombre, int horasSemanales, int cupoMaximo) {
        this.id = "CUR-" + contadorId++;
        this.nombre = nombre;
        this.horasSemanales = horasSemanales;
        this.cupoMaximo = cupoMaximo;
        alumnos = new ArrayList<>();
    }
    public void inscribirAlumno(Alumno alumno)throws CupoLlenoException {
        if (alumnos.size() >= cupoMaximo) {
            throw new CupoLlenoException("El Cupo " + nombre + " ha alcansado su cupo maximo " + cupoMaximo );
        }
        alumnos.add(alumno);
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
    
    public String getDescripcion(){
        return descripcion;
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
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }
    
    public abstract double calcularPonderacion();

    @Override
    public String obtenerId() {
        return id;
    }

    public abstract void agregarAlumnos(Alumno alumnoNuevo);
    
    public abstract void agregarPromedioNotas(Alumno alumnoNuevo);
    
    @Override
    public abstract double obtenerPuntajeRanking();

}