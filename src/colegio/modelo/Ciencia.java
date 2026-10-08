package colegio.modelo;

public class Ciencia extends Curso {

    public Ciencia() {
        super();
    }

    @Override
    public double calcularPonderacion() {
        return 6.7;
    }
}