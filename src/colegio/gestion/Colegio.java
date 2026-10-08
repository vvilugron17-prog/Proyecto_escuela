package colegio.gestion;

import colegio.modelo.Alumno;
import colegio.modelo.Profesor;
import colegio.modelo.Curso;
import colegio.modelo.Operacion;

import java.util.ArrayList;
import java.util.HashMap;

public class Colegio {

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
}
