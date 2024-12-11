package presentacion;
import javax.swing.*;
import negocio.UsuarioNegocio;
import java.awt.GridLayout;

public class LoginForm extends javax.swing.JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnCrearCuenta;
    private JButton btnCerrar;  // Nuevo botón de cerrar

    public LoginForm() {
        setTitle("Inicio de sesión");
        setSize(400, 300);  // Dimensiones más pequeñas
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centra la ventana en la pantalla
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));  // Cambiado a 4 filas para el nuevo botón

        panel.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        panel.add(txtUsuario);

        panel.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        btnLogin = new JButton("Iniciar sesión");
        panel.add(btnLogin);

        btnCrearCuenta = new JButton("Crear cuenta");
        panel.add(btnCrearCuenta);
        
        // Agregar el nuevo botón "Cerrar"
        btnCerrar = new JButton("Cerrar");
        panel.add(btnCerrar);

        add(panel);
   
        // Acción para login
        btnLogin.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String contrasena = new String(txtPassword.getPassword());
            if (UsuarioNegocio.validarUsuario(usuario, contrasena)) {
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
                // Redirigir a la selección de módulo
                ModuloSeleccionForm moduloSeleccionForm = new ModuloSeleccionForm();
                moduloSeleccionForm.setVisible(true);
                this.dispose();  // Cerrar el login
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
            }
        });

        // Acción para crear cuenta
        btnCrearCuenta.addActionListener(e -> {
            CrearCuentaForm crearCuentaForm = new CrearCuentaForm();
            crearCuentaForm.setVisible(true);
            this.dispose();  // Cerrar el login
        });
        
        // Acción para cerrar la ventana
        btnCerrar.addActionListener(e -> System.exit(0));  // Cerrar la aplicación
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
         SwingUtilities.invokeLater(() -> {
            LoginForm form = new LoginForm();
            form.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
