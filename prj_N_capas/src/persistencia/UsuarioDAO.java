package persistencia;

import recursos.GlobalException;
import recursos.NoDataExeption;
import recursos.conexion.conexion;
import java.sql.PreparedStatement;
import negocio.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import oracle.jdbc.OracleTypes;  // Asegúrate de importar OracleTypes

public class UsuarioDAO {

    private static final String FINDALL_USUARIOS = "{call SP_FINDALL_USUARIOS(?)}"; // Procedimiento almacenado que obtiene todos los usuarios
    private static final String CREATE_USUARIO = "INSERT INTO usuario (usuario, password) VALUES (?, ?)";
    private static final String UPDATE_USUARIO = "UPDATE usuario SET usuario = ?, password = ? WHERE id_usuario = ?";
    private static final String DELETE_USUARIO = "DELETE FROM usuario WHERE id_usuario = ?";
    private static final String FINDONE_USUARIO = "SELECT * FROM usuario WHERE id_usuario = ?";
    
    private final conexion conexion = new conexion();

    // Método para obtener todos los usuarios
    public Collection<Usuario> findAll() throws GlobalException, NoDataExeption {
        ResultSet rs = null;
        ArrayList<Usuario> collection = new ArrayList<>();
        Usuario usuarioObj;
        CallableStatement pstmt = null;

        try {
            pstmt = conexion.conectar().prepareCall(FINDALL_USUARIOS);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                usuarioObj = new Usuario(
                        rs.getInt("ID_USUARIO"),
                        rs.getString("USUARIO"),
                        rs.getString("PASSWORD")
                );
                collection.add(usuarioObj);
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no válida", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                conexion.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Error al cerrar recursos de la base de datos", e);
            }
        }

        if (collection.isEmpty()) {
            throw new NoDataExeption("No hay datos");
        }
        return collection;
    }

    // Método para obtener un usuario específico
    public Usuario findOne(Usuario usuario) throws GlobalException, NoDataExeption {
        ResultSet rs = null;
        Usuario usuarioObj = null;
        PreparedStatement pstmt = null;

        try {
            pstmt = conexion.conectar().prepareStatement(FINDONE_USUARIO);
            pstmt.setInt(1, usuario.getIdUsuario());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                usuarioObj = new Usuario(
                        rs.getInt("ID_USUARIO"),
                        rs.getString("USUARIO"),
                        rs.getString("PASSWORD")
                );
            }
        } catch (SQLException e) {
            throw new GlobalException("Error al encontrar usuario", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                conexion.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Error al cerrar recursos de la base de datos", e);
            }
        }

        if (usuarioObj == null) {
            throw new NoDataExeption("Usuario no encontrado");
        }

        return usuarioObj;
    }
        public boolean validarUsuario(String usuario, String contrasena) throws SQLException {
            String sql = "SELECT * FROM usuario WHERE usuario = ? AND password = ?";

            // Crear una instancia de la clase conexion y obtener la conexión
            conexion conexionDB = new conexion();
            try (Connection conn = conexionDB.conectar(); 
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, usuario);
                stmt.setString(2, contrasena);

                try (ResultSet rs = stmt.executeQuery()) {
                    // Verificar si el usuario existe
                    return rs.next();
                }
            } catch (SQLException e) {
                throw new SQLException("Error al validar el usuario", e);
            } finally {
                // Desconectar la base de datos después de la operación
                conexionDB.desconectar();
            }
        }

        
    // Método para crear un nuevo usuario
    public void create(Usuario usuario) throws GlobalException {
        PreparedStatement pstmt = null;

        try {
            pstmt = conexion.conectar().prepareStatement(CREATE_USUARIO);
            pstmt.setString(1, usuario.getUsuario());
            pstmt.setString(2, usuario.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new GlobalException("Error al crear usuario", e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                conexion.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Error al cerrar recursos de la base de datos", e);
            }
        }
    }

    // Método para actualizar un usuario
    public void update(Usuario usuario) throws GlobalException {
        PreparedStatement pstmt = null;

        try {
            pstmt = conexion.conectar().prepareStatement(UPDATE_USUARIO);
            pstmt.setString(1, usuario.getUsuario());
            pstmt.setString(2, usuario.getPassword());
            pstmt.setInt(3, usuario.getIdUsuario());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new GlobalException("Error al actualizar usuario", e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                conexion.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Error al cerrar recursos de la base de datos", e);
            }
        }
    }

    // Método para eliminar un usuario
    public void delete(Usuario usuario) throws GlobalException {
        PreparedStatement pstmt = null;

        try {
            pstmt = conexion.conectar().prepareStatement(DELETE_USUARIO);
            pstmt.setInt(1, usuario.getIdUsuario());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new GlobalException("Error al eliminar usuario", e);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                conexion.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Error al cerrar recursos de la base de datos", e);
            }
        }
    }
}








