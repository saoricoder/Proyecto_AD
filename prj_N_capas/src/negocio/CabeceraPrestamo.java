package negocio;

import java.sql.Date;

public class CabeceraPrestamo {

    private String numero;         // Número del préstamo
    private Date fechaPrestamo;    // Fecha del préstamo
    private String descripcion;    // Descripción del préstamo

    // Constructor
    public CabeceraPrestamo(String numero, Date fechaPrestamo, String descripcion) {
        this.numero = numero;
        this.fechaPrestamo = fechaPrestamo;
        this.descripcion = descripcion;
    }

    // Getters y setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "CabeceraPrestamo{" +
                "numero='" + numero + '\'' +
                ", fechaPrestamo=" + fechaPrestamo +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
