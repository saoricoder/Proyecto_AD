/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.Biblioteca;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ReporteLibroForm extends JFrame {

    private JTextField txtFecha;
    private JButton btnGenerarReporte, btnImprimir;
    private JTable table;
    private DefaultTableModel tableModel;

    public ReporteLibroForm() {
        // Configuración de la ventana
        setTitle("Reporte Cruzado: Libros por Autores");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior: Entrada de datos
        JPanel panelEntrada = new JPanel(new FlowLayout());
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Generar Reporte"));

        panelEntrada.add(new JLabel("Fecha (YYYY-MM-DD):"));
        txtFecha = new JTextField(10);
        panelEntrada.add(txtFecha);

        btnGenerarReporte = new JButton("Generar Reporte");
        panelEntrada.add(btnGenerarReporte);

        add(panelEntrada, BorderLayout.NORTH);

        // Panel central: Tabla de reporte
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel inferior: Botón de impresión
        JPanel panelBoton = new JPanel(new FlowLayout());
        btnImprimir = new JButton("Imprimir");
        panelBoton.add(btnImprimir);
        add(panelBoton, BorderLayout.SOUTH);

        // Configurar eventos
        configureButtonActions();

        setVisible(true);
    }

    private void configureButtonActions() {
        btnGenerarReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporte();
            }
        });

        btnImprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirReporte();
            }
        });
    }

    private void generarReporte() {
        // Limpiar tabla existente
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);

        String fecha = txtFecha.getText();
        if (fecha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una fecha.");
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "usuario", "contraseña")) {
            Statement statement = connection.createStatement();

            // Consulta para obtener autores y libros cruzados con la cantidad
            String query = "SELECT a.nombre AS autor, l.titulo AS libro, COUNT(*) AS cantidad " +
                           "FROM Autor a " +
                           "JOIN Libro l ON a.id_autor = l.id_autor " +
                           "WHERE l.fecha_publicacion <= TO_DATE('" + fecha + "', 'YYYY-MM-DD') " +
                           "GROUP BY a.nombre, l.titulo " +
                           "ORDER BY a.nombre, l.titulo";

            ResultSet resultSet = statement.executeQuery(query);

            // Obtener columnas dinámicas
            tableModel.addColumn("Libro / Autor");
            while (resultSet.next()) {
                String autor = resultSet.getString("autor");
                if (!tableModel.getColumnIdentifiers().contains(autor)) {
                    tableModel.addColumn(autor);
                }
            }

            // Reiniciar el ResultSet para llenar filas
            resultSet.beforeFirst();
            while (resultSet.next()) {
                String libro = resultSet.getString("libro");
                String autor = resultSet.getString("autor");
                int cantidad = resultSet.getInt("cantidad");

                // Encontrar la fila correspondiente al libro
                int rowIndex = -1;
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    if (tableModel.getValueAt(i, 0).equals(libro)) {
                        rowIndex = i;
                        break;
                    }
                }

                // Si no existe la fila, crearla
                if (rowIndex == -1) {
                    Object[] row = new Object[tableModel.getColumnCount()];
                    row[0] = libro;
                    tableModel.addRow(row);
                    rowIndex = tableModel.getRowCount() - 1;
                }

                // Agregar cantidad al cruce correcto
                int colIndex = tableModel.findColumn(autor);
                tableModel.setValueAt(cantidad, rowIndex, colIndex);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al generar el reporte: " + e.getMessage());
        }
    }

    private void imprimirReporte() {
        try {
            if (!table.print()) {
                JOptionPane.showMessageDialog(this, "Error al intentar imprimir el reporte.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al imprimir: " + e.getMessage());
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
    public static void main(String args[]) {
        new ReporteLibroForm();
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
