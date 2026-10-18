package colegio.modelo;

import colegio.enums.TipoOperacion;
import java.io.Serializable;

public class Operacion implements Serializable {
    private static final long serialVersionUID = 1L;
    private TipoOperacion tipo;
    private String descripcion;

    public Operacion(TipoOperacion tipo, String descripcion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public TipoOperacion getTipo() { return tipo; }
    public String getDescripcion() { return descripcion; }

    @Override
    public String toString() { return tipo + " | " + descripcion; }
}
