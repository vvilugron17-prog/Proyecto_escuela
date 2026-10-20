package colegio.excepciones;

public class CupoLlenoException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    // [REQUISITO: Persistencia]

    public CupoLlenoException(int cupoMaximo) {
        super("Capacidad maxima superada, hay " + cupoMaximo + " estudiantes ahora mismo");
    }

}