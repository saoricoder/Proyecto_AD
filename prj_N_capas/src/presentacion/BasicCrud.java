package presentacion;

import recursos.GlobalException;
import recursos.NoDataExeption;
import negocio.Usuario;
import persistencia.UsuarioDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicCrud {

    public static void main(String[] args) {
        try {
            // Instanciamos UsuarioDAO correctamente
            UsuarioDAO usuarioDao = new UsuarioDAO();

            // Instanciamos un objeto Usuario con los parámetros adecuados
            Usuario objUsuario = new Usuario(0, "admin", "root");  // ID asignado como 0 para la creación

            // Llamamos al método create de usuarioDao para insertar el usuario en la base de datos
            usuarioDao.create(objUsuario);
            System.out.println("Usuario creado exitosamente.");

            // Recuperamos y mostramos todos los usuarios
            for (Usuario usuarioObj : usuarioDao.findAll()) {
                System.out.println(usuarioObj.toString());
            }

            // Ejemplo de cómo obtener un usuario específico (por su ID, por ejemplo)
            Usuario usuarioBuscado = new Usuario();
            usuarioBuscado.setIdUsuario(1);  // Establecer el ID del usuario a buscar
            Usuario encontrado = usuarioDao.findOne(usuarioBuscado);
            System.out.println("Usuario encontrado: " + encontrado);

            // Ejemplo de cómo actualizar un usuario
            Usuario usuarioActualizar = new Usuario(1, "admin_updated", "root_updated");
            usuarioDao.update(usuarioActualizar);
            System.out.println("Usuario actualizado correctamente.");

            // Ejemplo de cómo eliminar un usuario
            Usuario usuarioEliminar = new Usuario();
            usuarioEliminar.setIdUsuario(1);  // Establecer el ID del usuario a eliminar
            usuarioDao.delete(usuarioEliminar);
            System.out.println("Usuario eliminado correctamente.");

        } catch (GlobalException ex) {
            Logger.getLogger(BasicCrud.class.getName()).log(Level.SEVERE, "Error global: ", ex);
        } catch (NoDataExeption ex) {
            Logger.getLogger(BasicCrud.class.getName()).log(Level.SEVERE, "No se encontraron datos: ", ex);
        }
    }
}

