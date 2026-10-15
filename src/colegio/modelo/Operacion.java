package colegio.modelo;

import colegio.enums.TipoOperacion;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Entrada del historial de acciones realizadas en el sistema.
public class Operacion implements Serializable {
    private static final long serialVersionUID = 1L;

    private TipoOperacion tipo;
    private String fecha;
    private String descripcion;
    private LocalDateTime fechaHora;

    // Constructor original sin parámetros.
    public Operacion() {
        this.tipo = TipoOperacion.INSCRIPCION;
        this.fechaHora = LocalDateTime.now();
        this.fecha = fechaHora.format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.descripcion = "Operación creada";
    }

    // Constructor que permite indicar el tipo y la descripción de la operación.
    public Operacion(TipoOperacion tipo, String descripcion) {
        this.tipo = tipo == null ? TipoOperacion.INSCRIPCION : tipo;
        this.descripcion = descripcion == null ? "Sin descripción" : descripcion;
        this.fechaHora = LocalDateTime.now();
        this.fecha = fechaHora.format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    // Devuelve el tipo de operación.
    public TipoOperacion getTipo() {
        return tipo;
    }

    // Devuelve la fecha de la operación.
    public String getFecha() {
        return fecha;
    }

    // Devuelve la descripción de la operación.
    public String getDescripcion() {
        return descripcion;
    }

    // Devuelve la fecha y hora de la operación.
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    // Devuelve la operación en formato de texto.
    @Override
    public String toString() {
        String fechaMostrar = fechaHora == null
                ? fecha
                : fechaHora.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        return "[" + fechaMostrar + "] " + tipo + ": " + descripcion;
    }
}