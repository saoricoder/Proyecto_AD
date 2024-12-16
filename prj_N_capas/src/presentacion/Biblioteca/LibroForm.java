package presentacion.Biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import recursos.conexion.conexion;
import negocio.BibliotecaNegocio;
import persistencia.BibliotecaDAO;
import java.sql.SQLException;

public class LibroForm extends JFrame {
    private JLabel lblISBN, lblTitulo, lblAutor, lblValorPrestamo;
    private JTextField txtISBN, txtTitulo, txtValorPrestamo;
    private JComboBox<String> cbAutor;
    private JButton btnGuardar, btnBuscar, btnEliminar, btnModificar, btnRegresar;

    private BibliotecaNegocio negocio;

    public LibroForm() {
        setTitle("Formulario de Libro");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        // Inicializar componentes
        lblISBN = new JLabel("ISBN:");
        lblTitulo = new JLabel("Título:");
        lblAutor = new JLabel("Autor:");
        lblValorPrestamo = new JLabel("Valor del préstamo:");

        txtISBN = new JTextField();
        txtTitulo = new JTextField();
        txtValorPrestamo = new JTextField();
        cbAutor = new JComboBox<>();

        btnGuardar = new JButton("Guardar");
        btnBuscar = new JButton("Buscar");
        btnEliminar = new JButton("Eliminar");
        btnModificar = new JButton("Modificar");
        btnRegresar = new JButton("Regresar");

        negocio = new BibliotecaNegocio();
        cargarAutores();

        // Agregar componentes al formulario
        add(lblISBN);
        add(txtISBN);
        add(lblTitulo);
        add(txtTitulo);
        add(lblAutor);
        add(cbAutor);
        add(lblValorPrestamo);
        add(txtValorPrestamo);

        add(btnGuardar);
        add(btnBuscar);
        add(btnModificar);
        add(btnEliminar);
        add(new JLabel());
        add(btnRegresar);

        // Eventos
        btnGuardar.addActionListener(e -> guardarLibro());
        btnBuscar.addActionListener(e -> buscarLibro());
        btnEliminar.addActionListener(e -> eliminarLibro());
        btnModificar.addActionListener(e -> modificarLibro());
        btnRegresar.addActionListener(e -> regresar());
    }

    private void cargarAutores() {
        try (Connection con = new conexion().conectar()) {
            String sql = "SELECT nombre FROM autor";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                cbAutor.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los autores: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarLibro() {
        String isbn = txtISBN.getText().trim();
        String titulo = txtTitulo.getText().trim();
        String autor = (String) cbAutor.getSelectedItem();
        String valorPrestamo = txtValorPrestamo.getText().trim();

        if (isbn.isEmpty() || titulo.isEmpty() || autor.isEmpty() || valorPrestamo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean resultado = negocio.guardarLibro(isbn, titulo, autor, Double.parseDouble(valorPrestamo));
        if (resultado) {
            JOptionPane.showMessageDialog(this, "Libro guardado correctamente.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar el libro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarLibro() {
        String isbn = txtISBN.getText().trim();
        if (isbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un ISBN para buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Libro libro = negocio.buscarLibro(isbn);
        if (libro != null) {
            txtTitulo.setText(libro.getTitulo());
            cbAutor.setSelectedItem(libro.getAutor());
            txtValorPrestamo.setText(String.valueOf(libro.getValorPrestamo()));
        } else {
            JOptionPane.showMessageDialog(this, "Libro no encontrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminarLibro() {
        String isbn = txtISBN.getText().trim();
        if (isbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un ISBN para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean resultado = negocio.eliminarLibro(isbn);
        if (resultado) {
            JOptionPane.showMessageDialog(this, "Libro eliminado correctamente.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar el libro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarLibro() {
        String isbn = txtISBN.getText().trim();
        String titulo = txtTitulo.getText().trim();
        String autor = (String) cbAutor.getSelectedItem();
        String valorPrestamo = txtValorPrestamo.getText().trim();

        if (isbn.isEmpty() || titulo.isEmpty() || autor.isEmpty() || valorPrestamo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean resultado = negocio.modificarLibro(isbn, titulo, autor, Double.parseDouble(valorPrestamo));
        if (resultado) {
            JOptionPane.showMessageDialog(this, "Libro modificado correctamente.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al modificar el libro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void regresar() {
        dispose();
        // Aquí puedes regresar al menú principal
    }

    private void limpiarCampos() {
        txtISBN.setText("");
        txtTitulo.setText("");
        txtValorPrestamo.setText("");
        cbAutor.setSelectedIndex(0);
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
    public static void main(String[] args) {
         SwingUtilities.invokeLater(() -> new LibroForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
