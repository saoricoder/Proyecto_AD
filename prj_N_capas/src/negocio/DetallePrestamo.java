/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author lizet
 */
public class DetallePrestamo {

    private String codigoLibro;    // Código del libro prestado
    private int cantidad;          // Cantidad de libros prestados
    private String fechaEntrega;   // Fecha de entrega del libro

    // Constructor
    public DetallePrestamo(String codigoLibro, int cantidad, String fechaEntrega) {
        this.codigoLibro = codigoLibro;
        this.cantidad = cantidad;
        this.fechaEntrega = fechaEntrega;
    }

    // Getters y Setters
    public String getCodigoLibro() {
        return codigoLibro;
    }

    public void setCodigoLibro(String codigoLibro) {
        this.codigoLibro = codigoLibro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    @Override
    public String toString() {
        return "DetallePrestamo{" +
                "codigoLibro='" + codigoLibro + '\'' +
                ", cantidad=" + cantidad +
                ", fechaEntrega='" + fechaEntrega + '\'' +
                '}';
    }
}
