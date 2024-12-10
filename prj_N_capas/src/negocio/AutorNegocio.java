/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;


import persistencia.AutorDAO;

public class AutorNegocio {
    AutorDAO dao = new AutorDAO();

    public void registrarAutor(int codigo, String nombre, String apellido) {
        if (nombre != null && !nombre.isEmpty() && apellido != null && !apellido.isEmpty()) {
            dao.agregarAutor(codigo, nombre, apellido);
        } else {
            System.out.println("Datos inválidos.");
        }
    }
}
