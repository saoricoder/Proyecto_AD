package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import recursos.conexion.conexion;

public class BibliotecaDAO {
    private conexion conexion;

    public BibliotecaDAO() {
        this.conexion = new conexion();
    }

    public void insertarAutor(String nombre, String apellido) {
        String sql = "INSERT INTO AUTOR (CODIGO, NOMBRE, APELLIDO) VALUES (autor_seq.NEXTVAL, ?, ?)";
        try (PreparedStatement stmt = conexion.conectar().prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Autor insertado con éxito.");
            } else {
                System.out.println("No se pudo insertar el autor.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al insertar autor: " + e.getMessage());
        }
    }


    public ResultSet buscarAutor(String nombre, String apellido) {
        String sql = "SELECT * FROM autor WHERE nombre = ? OR apellido = ?";
        try (Connection conn = conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            return ps.executeQuery(); // Devuelve el ResultSet para manejarlo en la capa de negocio
        } catch (SQLException e) {
            System.err.println("Error al buscar autor: " + e.getMessage());
            return null;
        }
    }

    
    public boolean insertarLibro(String isbn, String titulo, String autor, double valorPrestamo) {
        String sql = "INSERT INTO libro (isbn, titulo, autor, valor_prestamo) VALUES (?, ?, ?, ?)";
        try (Connection con = new conexion().conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, isbn);
            ps.setString(2, titulo);
            ps.setString(3, autor);
            ps.setDouble(4, valorPrestamo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public void modificarAutor(String nombre, String apellido) {
    String sql = "UPDATE autor SET nombre = ?, apellido = ? WHERE nombre = ? AND apellido = ?";
        try (Connection conn = conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, nombre);
            ps.setString(4, apellido);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al modificar autor: " + e.getMessage());
        }
    }
    
    public void eliminarAutor(String nombre, String apellido) {
    String sql = "DELETE FROM autor WHERE nombre = ? AND apellido = ?";
        try (Connection conn = conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar autor: " + e.getMessage());
        }
    }


/**Metodos para Libro**/
    public ResultSet buscarLibro(String isbn) throws SQLException {
        String sql = "SELECT * FROM libro WHERE isbn = ?";
        Connection conn = conexion.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, isbn);
        return ps.executeQuery(); // Devolver ResultSet para manejarlo en la capa de negocio
    }

    public void modificarLibro(String isbn, String titulo, String autor, double valorPrestamo) throws SQLException {
        String sql = "UPDATE libro SET titulo = ?, autor = ?, valor_prestamo = ? WHERE isbn = ?";

        try (Connection conn = conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, titulo);
            ps.setString(2, autor);
            ps.setDouble(3, valorPrestamo);
            ps.setString(4, isbn);
            ps.executeUpdate();
        }
    }

    public void eliminarLibro(String isbn) throws SQLException {
        String sql = "DELETE FROM libro WHERE isbn = ?";

        try (Connection conn = conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, isbn);
            ps.executeUpdate();
        }
    }
}

