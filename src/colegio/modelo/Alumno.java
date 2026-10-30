package colegio.modelo;

import colegio.excepciones.NotaInvalidaException;
import colegio.interfaces.Identificable;
import colegio.interfaces.Rankeable;
import java.util.ArrayList;

// [REQUISITO: Package] [REQUISITO: Herencia] [REQUISITO: Interface]
public class Alumno extends Persona implements Identificable, Rankeable, Comparable<Alumno> {

    // [REQUISITO: Persistencia]
    private static final long serialVersionUID = 1L;

    // [REQUISITO: Static y Final]
    private static int contadorId = 1;

    private final String id;
    private final ArrayList<Double> notas;
    private double promedioNotas;

    public Alumno(String nombre, String apellido) {
        super(nombre, apellido);
        this.id = "A" + contadorId++;
        this.notas = new ArrayList<>();
    }

    // [REQUISITO: Persistencia] Recupera la secuencia de identificadores despues de cargar.
    public static void sincronizarContadorId(ArrayList<Alumno> alumnos) {
        contadorId = alumnos.size() + 1;
    }

    // [REQUISITO: Excepciones]
    public void agregarNotas(double nota) {
        if (!Double.isFinite(nota) || nota < 1.0 || nota > 7.0) {
            throw new NotaInvalidaException("La nota debe ser un numero entre 1,0 y 7,0.");
        }

        notas.add(nota);
        calcularPromedioNotas();
    }

    private void calcularPromedioNotas() {
        if (notas.isEmpty()) {
            promedioNotas = 0;
            return;
        }

        double sumaNotas = 0;
        for (Double nota : notas) {
            sumaNotas += nota;
        }

        promedioNotas = sumaNotas / notas.size();
    }

    public double getPromedioNotas() {
        return promedioNotas;
    }

    public ArrayList<Double> getNotas() {
        return new ArrayList<>(notas);
    }

    // [REQUISITO: Interface] El promedio es el puntaje utilizado en el ranking.
    @Override
    public double obtenerPuntajeRanking() {
        return promedioNotas;
    }

    // [REQUISITO: Polimorfismo] Promedio mas alto primero, luego apellido y nombre.
    @Override
    public int compareTo(Alumno otro) {
        int resultadoPromedio = Double.compare(otro.getPromedioNotas(), this.getPromedioNotas());
        if (resultadoPromedio != 0) {
            return resultadoPromedio;
        }

        int resultadoApellido = this.getApellido().compareToIgnoreCase(otro.getApellido());
        if (resultadoApellido != 0) {
            return resultadoApellido;
        }

        return this.getNombre().compareToIgnoreCase(otro.getNombre());
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(id + " / " + nombre + "  " + apellido + " / " + promedioNotas);
    }

    @Override
    public String obtenerId() {
        return id;
    }
}
