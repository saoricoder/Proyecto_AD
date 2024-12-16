package negocio;

import persistencia.BibliotecaDAO;
import java.sql.SQLException;
import java.sql.ResultSet;

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

    public ResultSet buscarAutor(String nombre, String apellido) {
        return dao.buscarAutor(nombre, apellido); // Llamada al DAO
    }
    
    public void modificarAutor(String nombre, String apellido) {
        if (nombre != null && !nombre.isEmpty() && apellido != null && !apellido.isEmpty()) {
            dao.modificarAutor(nombre, apellido);
        } else {
            throw new IllegalArgumentException("Nombre y apellido no pueden estar vacíos.");
        }
    }
    
    public void eliminarAutor(String nombre, String apellido) {
        if (nombre != null && !nombre.isEmpty() && apellido != null && !apellido.isEmpty()) {
            dao.eliminarAutor(nombre, apellido);
        } else {
            throw new IllegalArgumentException("Nombre y apellido no pueden estar vacíos.");
        }
    }


    public void agregarLibro(String isbn, String titulo, String autor, double valorPrestamo) throws SQLException {
        if (!isbn.isEmpty() && !titulo.isEmpty() && !autor.isEmpty() && valorPrestamo > 0) {
            dao.insertarLibro(isbn, titulo, autor, valorPrestamo);
        } else {
            throw new IllegalArgumentException("Todos los campos deben ser válidos.");
        }
    }

    public ResultSet buscarLibro(String isbn) throws SQLException {
        if (!isbn.isEmpty()) {
            return dao.buscarLibro(isbn);
        } else {
            throw new IllegalArgumentException("El ISBN no puede estar vacío.");
        }
    }

    public boolean modificarLibro(String isbn, String titulo, String autor, double valorPrestamo) throws SQLException {
        if (!isbn.isEmpty() && !titulo.isEmpty() && !autor.isEmpty() && valorPrestamo > 0) {
            dao.modificarLibro(isbn, titulo, autor, valorPrestamo);
        } else {
            throw new IllegalArgumentException("Todos los campos deben ser válidos.");
        }
        return false;
    }

    public boolean eliminarLibro(String isbn) throws SQLException {
        if (!isbn.isEmpty()) {
            dao.eliminarLibro(isbn);
        } else {
            throw new IllegalArgumentException("El ISBN no puede estar vacío.");
        }
        return false;
    }
    public boolean guardarLibro(String isbn, String titulo, String autor, double valorPrestamo) {
        if (isbn == null || isbn.isEmpty() || titulo == null || titulo.isEmpty() || 
            autor == null || autor.isEmpty() || valorPrestamo <= 0) {
            return false;
        }
        return dao.insertarLibro(isbn, titulo, autor, valorPrestamo);
    }

}


