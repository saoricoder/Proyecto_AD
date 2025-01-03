package negocio;

import java.sql.*;
import persistencia.BibliotecaDAO;
import java.util.List;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import recursos.GlobalException;
import recursos.NoDataExeption;

public class BibliotecaNegocio {
    private final BibliotecaDAO bibliotecaDAO;

    public BibliotecaNegocio() {
        this.bibliotecaDAO = new BibliotecaDAO(); // Instanciamos el DAO
    }

    public boolean agregarAutor(String nombre, String apellido) throws SQLException {
        return bibliotecaDAO.agregarAutor(nombre, apellido);
    }

    public ResultSet buscarAutor(String nombre, String apellido) throws SQLException {
        return bibliotecaDAO.buscarAutores(nombre, apellido);
    }

    public boolean modificarAutor(int codigo, String nombre, String apellido) {
        return bibliotecaDAO.modificarAutor(codigo, nombre, apellido);
    }

    public boolean eliminarAutor(int codigo) {
        return bibliotecaDAO.eliminarAutor(codigo);
    }

    public List<String[]> obtenerTodosAutores() throws SQLException {
        return bibliotecaDAO.obtenerTodosAutores();
    }
    
    /**
     *
     * @return
     * @throws SQLException
     */
    /**
    public List<String> cargarAutoresParaCombo() throws SQLException {
           List<String[]> autores = bibliotecaDAO.obtenerTodosAutores();  // Obtén los autores del DAO
           List<String> listaAutores = new ArrayList<>();
           autores.forEach((autor) -> {
               listaAutores.add(autor[1] + " " + autor[2]); // Concatenamos nombre y apellido para agregar al combo
        });
           return listaAutores;
       }
       **/



    public void agregarLibro(String isbn, String titulo, String autor, double valorPrestamo) throws SQLException {
        bibliotecaDAO.insertarLibro(isbn, titulo, autor, valorPrestamo);
    }

    public void modificarLibro(String isbn, String titulo, String autor, double valorPrestamo) throws SQLException {
        bibliotecaDAO.actualizarLibro(isbn, titulo, autor, valorPrestamo);
    }

    public void eliminarLibro(String isbn) throws SQLException {
        bibliotecaDAO.eliminarLibro(isbn);
    }

    public ResultSet buscarLibro(String isbn, String titulo) throws SQLException {
        return bibliotecaDAO.buscarLibro(isbn, titulo);
    }
    
    public List<String[]> obtenerTodosLibros() throws SQLException {
        return bibliotecaDAO.obtenerTodosLibros();
    }

    public List<CabeceraPrestamo> obtenerCabeceraPrestamo(Date fechaInicio, Date fechaFin) {
    return bibliotecaDAO.obtenerCabeceraPrestamo(fechaInicio, fechaFin);
}


    public List<DetallePrestamo> obtenerDetallePrestamo() {
            return bibliotecaDAO.obtenerDetallePrestamo();
        }

    public void agregarCabecera(CabeceraPrestamo cabecera) {
            bibliotecaDAO.agregarCabecera(cabecera);
        }

    public void eliminarCabecera(String numero) {
            bibliotecaDAO.eliminarCabecera(numero);
        }

    public ResultSet obtenerAutores() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Collection<Usuario> obtenerUsuarios() throws GlobalException, NoDataExeption {
            try {
                return bibliotecaDAO.obtenerUsuarios();
            } catch (SQLException e) {
                throw new GlobalException("Error al obtener usuarios de la base de datos", e);
            }
        }
}

