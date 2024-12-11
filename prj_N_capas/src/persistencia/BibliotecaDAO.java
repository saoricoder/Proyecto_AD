package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import recursos.conexion.Conexion;

public class BibliotecaDAO {
    private Conexion conexion;

    public BibliotecaDAO() {
        this.conexion = new Conexion();
    }

    public void insertarAutor(String nombre, String apellido) {
        String sql = "INSERT INTO AUTOR (codigo, nombre, apellido) VALUES (AUTOR_SEQ.NEXTVAL, ?, ?)";

        try (Connection conn = conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.executeUpdate();

            System.out.println("Autor registrado con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al insertar autor: " + e.getMessage());
        }
    }

    // Métodos adicionales (Modificar, Eliminar, Buscar) pueden añadirse aquí
}

