package presentacion.Biblioteca;

import javax.swing.*;
import negocio.BibliotecaNegocio;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;



public class LibroForm extends JFrame {
    private final BibliotecaNegocio bibliotecaNegocio;

    private JTextField txtISBN, txtTitulo, txtAutor, txtValorPrestamo, txtBuscarISBN, txtBuscarTitulo;
    private JTextArea txtResultados;

    public LibroForm() {
        bibliotecaNegocio = new BibliotecaNegocio();

        setTitle("Gestión de Libros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(700, 600); // Ajustamos el tamaño de la ventana

        // Panel de Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Libro"));

        panelFormulario.add(new JLabel("ISBN:"));
        txtISBN = new JTextField();
        panelFormulario.add(txtISBN);

        panelFormulario.add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        panelFormulario.add(txtTitulo);

        panelFormulario.add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        panelFormulario.add(txtAutor);

        panelFormulario.add(new JLabel("Valor del Préstamo:"));
        txtValorPrestamo = new JTextField();
        panelFormulario.add(txtValorPrestamo);

        // Panel de Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnAgregar = new JButton("Agregar");
        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);

        // Botón "Regresar"
        JButton btnRegresar = new JButton("Regresar");
        panelBotones.add(btnRegresar);

        // Panel de Búsqueda
        JPanel panelBusqueda = new JPanel(new GridLayout(2, 2, 5, 5));
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Buscar Libro"));

        panelBusqueda.add(new JLabel("Buscar por ISBN:"));
        txtBuscarISBN = new JTextField();
        panelBusqueda.add(txtBuscarISBN);

        panelBusqueda.add(new JLabel("Buscar por Título:"));
        txtBuscarTitulo = new JTextField();
        panelBusqueda.add(txtBuscarTitulo);

        JButton btnBuscar = new JButton("Buscar");
        panelBusqueda.add(btnBuscar);

        // Área de Resultados
        txtResultados = new JTextArea(10, 50);
        txtResultados.setEditable(false);
        JScrollPane scrollResultados = new JScrollPane(txtResultados);
        scrollResultados.setBorder(BorderFactory.createTitledBorder("Resultados"));

        // Agregar componentes al JFrame
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.add(panelFormulario, BorderLayout.NORTH);
        panelCentro.add(panelBotones, BorderLayout.CENTER);

        add(panelCentro, BorderLayout.NORTH);
        add(scrollResultados, BorderLayout.CENTER);
        add(panelBusqueda, BorderLayout.SOUTH);

        // Acciones de botones
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bibliotecaNegocio.agregarLibro(
                        txtISBN.getText(),
                        txtTitulo.getText(),
                        txtAutor.getText(),
                        Double.parseDouble(txtValorPrestamo.getText())
                    );
                    JOptionPane.showMessageDialog(null, "Libro agregado con éxito.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar el libro: " + ex.getMessage());
                }
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bibliotecaNegocio.modificarLibro(
                        txtISBN.getText(),
                        txtTitulo.getText(),
                        txtAutor.getText(),
                        Double.parseDouble(txtValorPrestamo.getText())
                    );
                    JOptionPane.showMessageDialog(null, "Libro modificado con éxito.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al modificar el libro: " + ex.getMessage());
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bibliotecaNegocio.eliminarLibro(txtISBN.getText());
                    JOptionPane.showMessageDialog(null, "Libro eliminado con éxito.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el libro: " + ex.getMessage());
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet rs = bibliotecaNegocio.buscarLibro(
                        txtBuscarISBN.getText(),
                        txtBuscarTitulo.getText()
                    );
                    txtResultados.setText(""); // Limpiar resultados anteriores
                    while (rs.next()) {
                        txtResultados.append(
                            "ISBN: " + rs.getString("isbn") + "\n" +
                            "Título: " + rs.getString("titulo") + "\n" +
                            "Autor: " + rs.getString("autor") + "\n" +
                            "Valor del Préstamo: " + rs.getDouble("valor_prestamo") + "\n\n"
                        );
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar libros: " + ex.getMessage());
                }
            }
        });

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra esta ventana
                // Aquí puedes abrir un menú principal u otra ventana si lo necesitas
            }
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         SwingUtilities.invokeLater(() -> {
            LibroForm form = new LibroForm();
            form.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
/**        private void limpiarCampos() {
        txtISBN.setText("");
        txtTitulo.setText("");
        txtAutor.setText("");
        txtValorPrestamo.setText("");
    }**/