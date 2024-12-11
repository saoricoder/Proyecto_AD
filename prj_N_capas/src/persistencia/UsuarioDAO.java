package persistencia;

import java.sql.*;

public class UsuarioDAO {
    private static final String URL = "jdbc:mysql://localhost:521/xe";
    private static final String USER = "SYSTEM";
    private static final String PASSWORD = "23889";

    public boolean validarUsuario(String usuario, String contrasena) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, usuario);
                stmt.setString(2, contrasena);
                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next();  // Si se encuentra el usuario, retorna true
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void crearUsuario(String usuario, String contrasena) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
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

