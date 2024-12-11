package negocio;

import persistencia.BibliotecaDAO;
import java.util.List;
import java.util.ArrayList;


public class BibliotecaNegocio {
    private BibliotecaDAO dao;

    public BibliotecaNegocio() {
        dao = new BibliotecaDAO();
    }

    public void agregarAutor(String codigo, String nombre, String apellido) {
        dao.insertarAutor(codigo, nombre, apellido);
    }

    public void modificarAutor(String codigo, String nombre, String apellido) {
        dao.modificarAutor(codigo, nombre, apellido);
    }

    public void eliminarAutor(String codigo) {
        dao.eliminarAutor(codigo);
    }

    public List<String> listarAutores() {
        return dao.listarAutores();
    }
}

