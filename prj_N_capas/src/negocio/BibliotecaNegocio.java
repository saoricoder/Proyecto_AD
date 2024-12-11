package negocio;

import persistencia.BibliotecaDAO;

public class BibliotecaNegocio {
    private BibliotecaDAO dao;

    public BibliotecaNegocio() {
        dao = new BibliotecaDAO();
    }

    public void agregarAutor(String nombre, String apellido) {
        if (nombre != null && !nombre.isEmpty() && apellido != null && !apellido.isEmpty()) {
            dao.insertarAutor(nombre, apellido);
        } else {
            throw new IllegalArgumentException("Nombre y apellido no pueden estar vacíos.");
        }
    }

    // Métodos adicionales (Modificar, Eliminar, Buscar) pueden añadirse aquí
}


