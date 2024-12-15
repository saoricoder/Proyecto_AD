package presentacion.Biblioteca;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CantidadLibrosForm extends JFrame {

    private JTextField txtFecha;
    private JButton btnGenerarReporte, btnImprimir;
    private JTable table;
    private DefaultTableModel tableModel;

    public CantidadLibrosForm() {
        // Configuración de la ventana
        setTitle("Reporte: Cantidad de Libros a Entregar por Día");
        setSize(600, 400);
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
        tableModel = new DefaultTableModel(new Object[]{"Fecha", "Código Libro", "Cantidad"}, 0);
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

        // Simulación de datos generados
        String fecha = txtFecha.getText();
        if (fecha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una fecha.");
            return;
        }

        // Datos simulados para el reporte
        Object[][] datos = {
            {fecha, "L001", 3},
            {fecha, "L002", 5},
            {fecha, "L003", 2}
        };

        for (Object[] fila : datos) {
            tableModel.addRow(fila);
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
    new CantidadLibrosForm();
        }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
