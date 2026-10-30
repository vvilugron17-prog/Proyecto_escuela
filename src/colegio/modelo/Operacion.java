package colegio.modelo;

import colegio.enums.TipoOperacion;
import java.io.Serializable;

// [REQUISITO: Package] [REQUISITO: Persistencia]
public class Operacion implements Serializable {

    private static final long serialVersionUID = 1L;

    private final TipoOperacion tipo;
    private final String descripcion;
    private final String idAlumno;

    // Para operaciones generales que no pertenecen a un alumno.
    public Operacion(TipoOperacion tipo, String descripcion) {
        this(tipo, descripcion, null);
    }

    // Para operaciones que deben aparecer en el historial de un alumno.
    public Operacion(TipoOperacion tipo, String descripcion, String idAlumno) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de operacion es obligatorio.");
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripcion de la operacion es obligatoria.");
        }

        this.tipo = tipo;
        this.descripcion = descripcion;
        this.idAlumno = idAlumno;
    }

    public TipoOperacion getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getIdAlumno() {
        return idAlumno;
    }

    @Override
    public String toString() {
        return tipo + " | " + descripcion;
    }
}
