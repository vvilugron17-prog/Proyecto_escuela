package colegio.modelo;

// Profesor a cargo de uno o más cursos.
public class Profesor extends Persona {
    private static final long serialVersionUID = 1L;

    private String especialidad;
    private double salario;

    // Constructor original conservado.
    public Profesor() {
        super();
        this.especialidad = "Sin especialidad";
        this.salario = 0;
    }

    // Constructor que inicializa los datos del profesor.
    public Profesor(String id, String nombre, String apellido, int edad,
            String especialidad, double salario) {
        super(id, nombre, apellido, edad);

        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa.");
        }

        if (salario < 0 || Double.isNaN(salario) || Double.isInfinite(salario)) {
            throw new IllegalArgumentException("El salario no puede ser negativo.");
        }

        this.especialidad = especialidad == null
                ? "Sin especialidad" : especialidad.trim();
        this.salario = salario;
    }

    // Devuelve la especialidad del profesor.
    public String getEspecialidad() {
        return especialidad;
    }

    // Devuelve el salario del profesor.
    public double getSalario() {
        return salario;
    }

    // Modifica la especialidad del profesor.
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    // Modifica el salario del profesor.
    public void setSalario(double salario) {
        if (salario < 0) {
            throw new IllegalArgumentException("El salario no puede ser negativo.");
        }
        this.salario = salario;
    }

    // Muestra la información del profesor en pantalla.
    @Override
    public void mostrarInformacion() {
        System.out.println("Profesor: " + nombre + " " + apellido + " | ID: " + id
                + " | Especialidad: " + especialidad + " | Salario: "
                + String.format(java.util.Locale.US, "%.2f", salario));
    }

    // Devuelve los datos principales del profesor en formato de texto.
    @Override
    public String toString() {
        return "Profesor{" + "id='" + id + '\'' + ", nombre='" + nombre + ' ' + apellido
                + "', especialidad='" + especialidad + '\'' + '}';
    }
}