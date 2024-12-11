
package presentacion;

import javax.swing.*;
import negocio.BibliotecaNegocio;
import java.awt.GridLayout;

public class BibliotecaForm extends javax.swing.JFrame {
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JButton btnGuardar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnBuscar;

    public BibliotecaForm() {
        setTitle("Modulo de Biblioteca");
        setSize(500, 400); // Asegúrate de que la ventana sea de un tamaño adecuado
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        // Entrada de Autor
        panel.add(new JLabel("Código Autor:"));
        txtCodigo = new JTextField();
        panel.add(txtCodigo);

        panel.add(new JLabel("Nombre Autor:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Apellido Autor:"));
        txtApellido = new JTextField();
        panel.add(txtApellido);

        // Botones de Acciones
        btnGuardar = new JButton("Guardar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnBuscar = new JButton("Buscar");

        panel.add(btnGuardar);
        panel.add(btnModificar);
        panel.add(btnEliminar);
        panel.add(btnBuscar);

        add(panel);

        // Acción al presionar guardar
        btnGuardar.addActionListener(e -> {
        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        BibliotecaNegocio negocio = new BibliotecaNegocio(); // Crear una instancia de BibliotecaNegocio
        negocio.agregarAutor(codigo, nombre, apellido); // Llamar al método agregarAutor
        JOptionPane.showMessageDialog(this, "Autor guardado.");
    });
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
            BibliotecaForm form = new BibliotecaForm();
            form.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
