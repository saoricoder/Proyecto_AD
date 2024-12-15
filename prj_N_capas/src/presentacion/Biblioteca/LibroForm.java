package presentacion.Biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LibroForm extends JFrame {
    
    private JLabel lblISBN, lblTitulo, lblAutor, lblValorPrestamo;
    private JTextField txtISBN, txtTitulo, txtAutor, txtValorPrestamo;
    private JButton btnGuardar;
    
    public LibroForm() {
        setTitle("Formulario de Libro");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));
        
        // Inicializar componentes
        lblISBN = new JLabel("ISBN:");
        lblTitulo = new JLabel("Título:");
        lblAutor = new JLabel("Autor:");
        lblValorPrestamo = new JLabel("Valor del préstamo:");
        
        txtISBN = new JTextField();
        txtTitulo = new JTextField();
        txtAutor = new JTextField();
        txtValorPrestamo = new JTextField();
        
        btnGuardar = new JButton("Guardar");
        
        // Agregar los componentes al formulario
        add(lblISBN);
        add(txtISBN);
        add(lblTitulo);
        add(txtTitulo);
        add(lblAutor);
        add(txtAutor);
        add(lblValorPrestamo);
        add(txtValorPrestamo);
        add(new JLabel());  // Espacio vacío
        add(btnGuardar);
        
        // Acción del botón Guardar
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarLibro();
            }
        });
    }
    
    private void guardarLibro() {
        String isbn = txtISBN.getText();
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String valorPrestamo = txtValorPrestamo.getText();
        
        if (isbn.isEmpty() || titulo.isEmpty() || autor.isEmpty() || valorPrestamo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.");
            return;
        }
        
        // Guardar en la base de datos
        try {
            // Establecer la conexión con la base de datos
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "usuario", "contraseña");
            
            // Crear la sentencia SQL
            String sql = "INSERT INTO libro (isbn, titulo, autor, valor_prestamo) VALUES (?, ?, ?, ?)";
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, isbn);
            pst.setString(2, titulo);
            pst.setString(3, autor);
            pst.setString(4, valorPrestamo);
            
            // Ejecutar la inserción
            pst.executeUpdate();
            
            // Confirmación
            JOptionPane.showMessageDialog(this, "Libro guardado correctamente.");
            
            // Limpiar campos después de guardar
            txtISBN.setText("");
            txtTitulo.setText("");
            txtAutor.setText("");
            txtValorPrestamo.setText("");
            
            // Cerrar la conexión
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el libro: " + ex.getMessage());
        }
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
        SwingUtilities.invokeLater(() -> {
            new LibroForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
