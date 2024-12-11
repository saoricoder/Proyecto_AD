package persistencia;

import recursos.conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaDAO {
    Conexion con = new Conexion();

    public void insertarAutor(String codigo, String nombre, String apellido) {
        String sql = "INSERT INTO AUTOR (codigo, nombre, apellido) VALUES (?, ?, ?)";
        try (Connection conn = con.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar autor: " + e.getMessage());
        }
    }

    public void modificarAutor(String codigo, String nombre, String apellido) {
        String sql = "UPDATE AUTOR SET nombre = ?, apellido = ? WHERE codigo = ?";
        try (Connection conn = con.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, codigo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al modificar autor: " + e.getMessage());
        }
    }

    public void eliminarAutor(String codigo) {
        String sql = "DELETE FROM AUTOR WHERE codigo = ?";
        try (Connection conn = con.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar autor: " + e.getMessage());
        }
    }

    public List<String> listarAutores() {
        List<String> autores = new ArrayList<>();
        String sql = "SELECT * FROM AUTOR";
        try (Connection conn = con.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                autores.add(rs.getString("codigo") + " - " +
                            rs.getString("nombre") + " " +
                            rs.getString("apellido"));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar autores: " + e.getMessage());
        }
        return autores;
    }
}

