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
public class CabeceraPrestamo {

    private String numero;         // Número del préstamo
    private String fechaPrestamo;  // Fecha del préstamo
    private String descripcion;    // Descripción del préstamo

    // Constructor
    public CabeceraPrestamo(String numero, String fechaPrestamo, String descripcion) {
        this.numero = numero;
        this.fechaPrestamo = fechaPrestamo;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
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
                ", fechaPrestamo='" + fechaPrestamo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}