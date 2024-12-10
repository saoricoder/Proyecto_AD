package negocio;

import persistencia.UsuarioDAO;

public class UsuarioNegocio {
    public static boolean validarUsuario(String usuario, String contrasena) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.validarUsuario(usuario, contrasena);
    }

    public static void crearUsuario(String usuario, String contrasena) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.crearUsuario(usuario, contrasena);
    }
}


