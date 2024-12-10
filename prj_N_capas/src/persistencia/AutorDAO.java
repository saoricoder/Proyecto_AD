/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import recursos.conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    Conexion con = new Conexion();

    public void agregarAutor(int codigo, String nombre, String apellido) {
        String sql = "INSERT INTO AUTOR (codigo, nombre, apellido) VALUES (?, ?, ?)";
        try (Connection conn = con.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.executeUpdate();
            System.out.println("Autor agregado.");
        } catch (SQLException e) {
            System.err.println("Error al agregar autor: " + e.getMessage());
        }
    }

    public List<String> listarAutores() {
        List<String> autores = new ArrayList<>();
        String sql = "SELECT * FROM AUTOR";
        try (Connection conn = con.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                autores.add(rs.getInt("codigo") + " - " +
                            rs.getString("nombre") + " " +
                            rs.getString("apellido"));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar autores: " + e.getMessage());
        }
        return autores;
    }
}
