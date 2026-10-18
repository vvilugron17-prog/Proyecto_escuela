package colegio.excepciones;

public class NotaInvalidaException extends Exception {
    
    private static final long serialVersionOID = 1L;
    // [REQUISITO: Persistencia]
    

    public NotaInvalidaException(String mensaje) {
        super(mensaje);
    }
}