package negocio;

import persistencia.UsuarioDAO;
import recursos.GlobalException;
import recursos.NoDataExeption;
import java.sql.SQLException;
import java.util.Collection;

public class UsuarioNegocio {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    
        // Método para obtener todos los usuarios
    public Collection<Usuario> obtenerUsuarios() throws GlobalException, NoDataExeption {
        return usuarioDAO.findAll();
    }

    // Método para obtener un usuario por su ID
    public Usuario obtenerUsuarioPorId(Usuario usuario) throws GlobalException, NoDataExeption {
        return usuarioDAO.findOne(usuario);
    }

    // Valida si un usuario existe con la contraseña especificada
    public static boolean validarUsuario(String usuario, String contrasena) {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            return dao.validarUsuario(usuario, contrasena);
        } catch (SQLException ex) {
            System.err.println("Error al validar el usuario: " + ex.getMessage());
            return false;
        }
    }

    // Crea un nuevo usuario con el nombre y contraseña especificados
    public static boolean crearUsuario(String usuario, String contrasena) {
        try {
            if (usuario == null || usuario.isEmpty() || contrasena == null || contrasena.isEmpty()) {
                System.err.println("El nombre de usuario y la contraseña no pueden estar vacíos.");
                return false;
            }
            if (!usuario.matches("^[A-Za-z0-9_]+$")) {
                System.err.println("El nombre de usuario contiene caracteres no permitidos.");
                return false;
            }

            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuarioObj = new Usuario(0, usuario, contrasena);
            dao.create(usuarioObj);
            System.out.println("Usuario creado exitosamente.");
            return true;
        } catch (GlobalException ex) {
            System.err.println("Error en la base de datos: " + ex.getMessage());
            return false;
        }
    }

    // Actualiza los detalles de un usuario
    public static boolean actualizarUsuario(int idUsuario, String usuario, String contrasena) {
        try {
            if (usuario == null || usuario.isEmpty() || contrasena == null || contrasena.isEmpty()) {
                System.err.println("El nombre de usuario y la contraseña no pueden estar vacíos.");
                return false;
            }

            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuarioObj = new Usuario(idUsuario, usuario, contrasena);
            dao.update(usuarioObj);
            System.out.println("Usuario actualizado correctamente.");
            return true;
        } catch (GlobalException ex) {
            System.err.println("Error en la base de datos: " + ex.getMessage());
            return false;
        }
    }

    // Elimina un usuario de la base de datos
    public static boolean eliminarUsuario(int idUsuario) {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuarioObj = new Usuario();
            usuarioObj.setIdUsuario(idUsuario);
            dao.delete(usuarioObj);
            System.out.println("Usuario eliminado correctamente.");
            return true;
        } catch (GlobalException ex) {
            System.err.println("Error en la base de datos: " + ex.getMessage());
            return false;
        }
    }

    // Recupera todos los usuarios desde la base de datos
    public static void listarUsuarios() {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            for (Object object : dao.findAll()) {
                Usuario usuarioObj = (Usuario) object;
                System.out.println(usuarioObj.toString());
            }
        } catch (GlobalException | NoDataExeption ex) {
            System.err.println("Error en la base de datos: " + ex.getMessage());
        }
    }

    // Recupera un usuario específico por su ID
    public static Usuario obtenerUsuarioPorId(int idUsuario) {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuarioObj = new Usuario();
            usuarioObj.setIdUsuario(idUsuario);
            return (Usuario) dao.findOne(usuarioObj);
        } catch (GlobalException | NoDataExeption ex) {
            System.err.println("Error en la base de datos: " + ex.getMessage());
        }
        return null;
    }
}






