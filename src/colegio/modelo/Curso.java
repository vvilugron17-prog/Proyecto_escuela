package colegio.modelo;

import colegio.excepciones.CupoLlenoException;
import colegio.interfaces.Identificable;
import colegio.interfaces.Rankeable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import colegio.enums.NivelCurso;
// [REQUISITO: Persistencia] [REQUISITO: Herencia]
public abstract class Curso implements Identificable, Rankeable, Serializable {

    private static final long serialVersionUID = 1L;

    private static int contadorId = 1;
    protected String id;
    protected String nombre;
    protected int horasSemanales;
    protected int cupoMaximo;
    protected Profesor profesor;
    protected ArrayList<Alumno> alumnos; // Lista para guardar a todos los alumnos de un curso
    protected NivelCurso nivelCurso;
    
    public Curso(String nombre, int horasSemanales, int cupoMaximo) {
        this.id = "CUR-" + contadorId++;
        this.nombre = nombre;
        this.horasSemanales = horasSemanales;
        this.cupoMaximo = cupoMaximo;
        alumnos = new ArrayList<>();
        asignarNivelCurso();
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
    
    //Método para asignarle un nivel al curso creado
    private void asignarNivelCurso(){       
     
        if(horasSemanales < 6){
            this.nivelCurso = NivelCurso.BASICO;
        }
        
        else if(horasSemanales > 6 && horasSemanales < 12){
            this.nivelCurso = NivelCurso.INTERMEDIO;
        }
        
        else{
            this.nivelCurso = NivelCurso.AVANZADO;
        }
        
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

    
    public abstract void mostrarInformacion();
}