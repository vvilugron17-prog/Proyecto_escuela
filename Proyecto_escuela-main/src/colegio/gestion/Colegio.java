package colegio.gestion;

import colegio.interfaces.seriable;
import colegio.modelo.Alumno;
import colegio.modelo.Profesor;
import colegio.modelo.Curso;
import colegio.modelo.Operacion;

import java.util.ArrayList;
import java.util.HashMap;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class Colegio implements seriable {
  
    private static final long serialVersionOID = 1L;
    // [REQUISITO: Persistencia]
    
    private final static String NOMBRE_COLEGIO = "Zapallar";
    // [REQUISITO: Static y Final].
            
    private ArrayList<Alumno> alumnos;
    private ArrayList<Profesor> profesores;
    private ArrayList<Curso> cursos;
    private ArrayList<Operacion> historial;

    private HashMap<String, Alumno> alumnosPorId;
    private HashMap<String, Profesor> profesoresPorId;
    private HashMap<String, Curso> cursosPorId;

    public Colegio() {
 
        alumnos = new ArrayList<>();
        profesores = new ArrayList<>();
        cursos = new ArrayList<>();
        historial = new ArrayList<>();

        alumnosPorId = new HashMap<>();
        profesoresPorId = new HashMap<>();
        cursosPorId = new HashMap<>();
    }
    
    // [REQUISITO: Persistencia].
    public void guardar() throws IOException {
         try (ObjectOutputStream oos = new ObjectOutputStream(
         new FileOutputStream("datos.dat"))) {
             Object lista = null;
         oos.writeObject(lista); 
         System.out.println("Datos guardados correctamente.");
        }
    }
}


