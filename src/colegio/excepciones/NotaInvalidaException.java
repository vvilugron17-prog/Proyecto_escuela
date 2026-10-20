package colegio.excepciones;

// [REQUISITO: Excepciones]
public class NotaInvalidaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotaInvalidaException(String mensaje) {
        super(mensaje);
    }
}
