package presentacion.Biblioteca;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import negocio.BibliotecaNegocio;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutorForm extends JFrame {
    private JTextField txtNombre, txtApellido;
    private JTable tablaAutores;
    private DefaultTableModel modeloTabla;
    private JButton btnGuardar, btnModificar, btnEliminar, btnBuscar, btnRegresar;

    public AutorForm() {
        setTitle("Gestión de Autores");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior: Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 5, 5));
        panelFormulario.add(new JLabel("Nombre Autor:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Apellido Autor:"));
        txtApellido = new JTextField();
        panelFormulario.add(txtApellido);

        add(panelFormulario, BorderLayout.NORTH);

        // Panel central: Tabla
        modeloTabla = new DefaultTableModel(new String[]{"Código", "Nombre", "Apellido"}, 0);
        tablaAutores = new JTable(modeloTabla);
        tablaAutores.getSelectionModel().addListSelectionListener(e -> seleccionarRegistro());
        add(new JScrollPane(tablaAutores), BorderLayout.CENTER);

        // Panel inferior: Botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnGuardar = new JButton("Guardar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnBuscar = new JButton("Buscar");
        btnRegresar = new JButton("Regresar");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnRegresar);

        add(panelBotones, BorderLayout.SOUTH);

        // Eventos
        BibliotecaNegocio negocio = new BibliotecaNegocio();

        btnGuardar.addActionListener(e -> {
            if (validarCampos()) {
                try {
                    negocio.agregarAutor(txtNombre.getText(), txtApellido.getText());
                    JOptionPane.showMessageDialog(this, "Autor guardado.");
                    cargarTabla();
                    limpiarCampos();
                } catch (SQLException ex) {
                    mostrarError(ex);
                }
            }
        });

        btnBuscar.addActionListener(e -> {
            try {
                ResultSet rs = negocio.buscarAutor(txtNombre.getText(), txtApellido.getText());
                modeloTabla.setRowCount(0);
                while (rs.next()) {
                    modeloTabla.addRow(new Object[]{rs.getInt("CODIGO"), rs.getString("NOMBRE"), rs.getString("APELLIDO")});
                }
            } catch (SQLException ex) {
                mostrarError(ex);
            }
        });

        btnModificar.addActionListener(e -> {
            int filaSeleccionada = tablaAutores.getSelectedRow();
            if (filaSeleccionada >= 0 && validarCampos()) {
                int codigo = (int) tablaAutores.getValueAt(filaSeleccionada, 0);
                negocio.modificarAutor(codigo, txtNombre.getText(), txtApellido.getText());
                JOptionPane.showMessageDialog(this, "Autor modificado.");
                cargarTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un autor y completar los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = tablaAutores.getSelectedRow();
            if (filaSeleccionada >= 0) {
                int codigo = (int) tablaAutores.getValueAt(filaSeleccionada, 0);
                negocio.eliminarAutor(codigo);
                JOptionPane.showMessageDialog(this, "Autor eliminado.");
                cargarTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un autor.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnRegresar.addActionListener(e -> dispose());

        // Cargar datos iniciales
        cargarTabla();
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        try {
            BibliotecaNegocio negocio = new BibliotecaNegocio();
            ResultSet rs = negocio.buscarAutor("", ""); // Cargar todos los autores
            while (rs.next()) {
                modeloTabla.addRow(new Object[]{rs.getInt("CODIGO"), rs.getString("NOMBRE"), rs.getString("APELLIDO")});
            }
        } catch (SQLException e) {
            mostrarError(e);
        }
    }

    private void seleccionarRegistro() {
        int fila = tablaAutores.getSelectedRow();
        if (fila >= 0) {
            txtNombre.setText(tablaAutores.getValueAt(fila, 1).toString());
            txtApellido.setText(tablaAutores.getValueAt(fila, 2).toString());
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
    }

    private void mostrarError(Exception e) {
        Logger logger = Logger.getLogger(AutorForm.class.getName());
        logger.log(Level.SEVERE, "Ha ocurrido un error en AutorForm", e); // Log del error
        JOptionPane.showMessageDialog(
            this,
            "Ha ocurrido un error inesperado:\n" + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty() || txtApellido.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
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
       SwingUtilities.invokeLater(() -> new AutorForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
