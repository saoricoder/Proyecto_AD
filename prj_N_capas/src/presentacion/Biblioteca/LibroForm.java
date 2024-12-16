package presentacion.Biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import recursos.conexion.conexion;
import negocio.Libro;
import negocio.BibliotecaNegocio;
import persistencia.BibliotecaDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibroForm extends JFrame {
    private JTextField txtISBN, txtTitulo, txtAutor, txtValorPrestamo;
    private JButton btnGuardar, btnBuscar, btnModificar, btnEliminar, btnRegresar;
    private BibliotecaNegocio bibliotecaNegocio;

    public LibroForm() {
        bibliotecaNegocio = new BibliotecaNegocio();
        
        // Configuración de la ventana principal
        setTitle("Gestión de Libros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 5, 5));

        // Componentes de la interfaz
        JLabel lblISBN = new JLabel("ISBN:");
        txtISBN = new JTextField();
        JLabel lblTitulo = new JLabel("Título:");
        txtTitulo = new JTextField();
        JLabel lblAutor = new JLabel("Autor:");
        txtAutor = new JTextField();
        JLabel lblValorPrestamo = new JLabel("Valor Préstamo:");
        txtValorPrestamo = new JTextField();

        btnGuardar = new JButton("Guardar");
        btnBuscar = new JButton("Buscar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnRegresar = new JButton("Regresar");

        // Añadiendo componentes al formulario
        add(lblISBN);
        add(txtISBN);
        add(lblTitulo);
        add(txtTitulo);
        add(lblAutor);
        add(txtAutor);
        add(lblValorPrestamo);
        add(txtValorPrestamo);
        add(btnGuardar);
        add(btnBuscar);
        add(btnModificar);
        add(btnEliminar);
        add(btnRegresar);

        // Configuración de los listeners
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarLibro();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buscarLibro();
                } catch (SQLException ex) {
                    Logger.getLogger(LibroForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    modificarLibro();
                } catch (SQLException ex) {
                    Logger.getLogger(LibroForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminarLibro();
                } catch (SQLException ex) {
                    Logger.getLogger(LibroForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresar();
            }
        });

        setLocationRelativeTo(null); // Centrar la ventana
    }

    private void guardarLibro() {
        String isbn = txtISBN.getText();
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String valorPrestamoStr = txtValorPrestamo.getText();

        try {
            double valorPrestamo = Double.parseDouble(valorPrestamoStr);
            boolean resultado = bibliotecaNegocio.guardarLibro(isbn, titulo, autor, valorPrestamo);
            if (resultado) {
                JOptionPane.showMessageDialog(this, "Libro guardado correctamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el libro. Verifique los datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El valor de préstamo debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarLibro() throws SQLException {
        String isbn = txtISBN.getText();
        if (isbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el ISBN para buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Libro libro = (Libro) bibliotecaNegocio.buscarLibro(isbn);
        if (libro != null) {
            txtTitulo.setText(libro.getTitulo());
            txtAutor.setText(libro.getAutor());
            txtValorPrestamo.setText(String.valueOf(libro.getValorPrestamo()));
        } else {
            JOptionPane.showMessageDialog(this, "Libro no encontrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void modificarLibro() throws SQLException {
        String isbn = txtISBN.getText();
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String valorPrestamoStr = txtValorPrestamo.getText();

        try {
            double valorPrestamo = Double.parseDouble(valorPrestamoStr);
            boolean resultado = bibliotecaNegocio.modificarLibro(isbn, titulo, autor, valorPrestamo);
            if (resultado) {
                JOptionPane.showMessageDialog(this, "Libro modificado correctamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al modificar el libro. Verifique los datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El valor de préstamo debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarLibro() throws SQLException {
        String isbn = txtISBN.getText();
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este libro?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean resultado = bibliotecaNegocio.eliminarLibro(isbn);
            if (resultado) {
                JOptionPane.showMessageDialog(this, "Libro eliminado correctamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el libro. Verifique el ISBN.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void regresar() {
        dispose(); // Cierra la ventana actual
        // Aquí puedes redirigir al menú principal si existe
    }

    private void limpiarCampos() {
        txtISBN.setText("");
        txtTitulo.setText("");
        txtAutor.setText("");
        txtValorPrestamo.setText("");
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
