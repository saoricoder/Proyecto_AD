package recursos.conexion;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class conexion {
    // Variables de conexión (mejor usar variables de entorno)
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // Cambia si usas otro puerto o SID
    private static final String USER = "SYSTEM"; // Asegúrate de que la variable de entorno esté configurada
    private static final String PASSWORD = "23889"; // Asegúrate de que la variable de entorno esté configurada

    // Objeto para la conexión y el log
    private Connection conn;
    private static final Logger LOGGER = Logger.getLogger(conexion.class.getName());

  /// Método para conectar a la base de datos
    public Connection conectar() {
        try {
            // Cargar el driver de Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Establecer la conexión
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //LOGGER.info("Conexión exitosa a la base de datos Oracle");
            System.out.println("Mensaje de prueba");
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "El driver de Oracle no fue encontrado", e);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al conectar a la base de datos", e);
        }
        return conn;
    }

    // Desconectar la base de datos
    public void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                LOGGER.info("Conexión cerrada");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al cerrar la conexión", e);
        }
    }

    // Método para verificar si un usuario existe (consultar por usuario)
    public boolean verificarUsuario(String usuario, String password) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    LOGGER.info("Usuario encontrado: " + usuario);
                    return true;
                } else {
                    LOGGER.warning("Usuario no encontrado o contraseña incorrecta.");
                    return false;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al verificar usuario", e);
            return false;
        }
    }

    // Método para agregar un nuevo usuario
    public boolean agregarUsuario(String usuario, String password) {
        String sql = "INSERT INTO usuarios (usuario, password) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, password);
            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                LOGGER.info("Usuario " + usuario + " agregado con éxito.");
                return true;
            } else {
                LOGGER.warning("No se pudo agregar el usuario.");
                return false;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al agregar usuario", e);
            return false;
        }
    }
}



