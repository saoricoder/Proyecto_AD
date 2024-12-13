package persistencia;

import recursos.conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean validarUsuario(String usuario, String contrasena) {
        conexion db = new conexion();
        try (Connection conn = db.conectar()) {
            String query = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, usuario);
                stmt.setString(2, contrasena);
                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next(); // Si se encuentra el usuario, retorna true
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void crearUsuario(String usuario, String contrasena) {
        conexion db = new conexion();
        try (Connection conn = db.conectar()) {
            String query = "INSERT INTO usuarios (usuario, contrasena) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, usuario);
                stmt.setString(2, contrasena);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

