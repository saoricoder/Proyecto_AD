package presentacion.Biblioteca;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import negocio.BibliotecaNegocio;
import java.util.List;
//import com.toedter.calendar.JDateChooser;

public class CantidadLibrosForm extends JFrame {

    //private final JDateChooser dateChooser;  // Declarar el JDateChooser
    private final JButton btnGenerarReporte;
    private final JButton btnImprimir;
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final BibliotecaNegocio bibliotecaNegocio;

    public CantidadLibrosForm() {
        // Configuración de la ventana
        setTitle("Reporte: Cantidad de Libros a Entregar por Día");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear instancia de BibliotecaNegocio
        bibliotecaNegocio = new BibliotecaNegocio();

        // Panel superior: Entrada de datos
        JPanel panelEntrada = new JPanel(new FlowLayout());
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Generar Reporte"));
        /**  
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        panelEntrada.add(dateChooser);**/

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
        /**
        // Obtener fecha ingresada
        Date fecha = dateChooser.getDate();  // Obtener la fecha seleccionada
        if (fecha == null) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una fecha.");
            return;
        }

        // Obtener los libros de la base de datos a través de BibliotecaNegocio
        try {
            List<String[]> libros = bibliotecaNegocio.listarLibros();

            // Llenar la tabla con los datos obtenidos de la base de datos
            libros.forEach((libro) -> {
                tableModel.addRow(new Object[]{fecha.toString(), libro[0], libro[1]});
            });

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al obtener los datos de los libros: " + ex.getMessage());
        }**/
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
