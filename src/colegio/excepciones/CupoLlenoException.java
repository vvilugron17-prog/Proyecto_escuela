package colegio.excepciones;

// [REQUISITO: Package] [REQUISITO: Excepciones]
public class CupoLlenoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CupoLlenoException(int cupoMaximo) {
        super("No hay cupos disponibles. La capacidad maxima es " + cupoMaximo + " alumnos.");
    }
}
