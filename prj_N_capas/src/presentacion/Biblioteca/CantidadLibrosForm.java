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
import persistencia.BibliotecaDAO;
import java.util.List;
//import com.toedter.calendar.JDateChooser;

public class CantidadLibrosForm extends JFrame {

    private final JButton btnGenerarReporte;
    private final JButton btnImprimir;
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final BibliotecaDAO bibliotecaDao; // Instancia de BibliotecaDao
    private final JTable fechaTable;  // Tabla para mostrar las fechas
    private final JLabel lblCantidadTotal; // Etiqueta para mostrar la cantidad total de libros

    public CantidadLibrosForm() {
        // Configuración de la ventana
        setTitle("Reporte: Cantidad de Libros a Entregar por Día");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear instancia de BibliotecaDao
        bibliotecaDao = new BibliotecaDAO();

        // Panel superior: Entrada de datos
        JPanel panelEntrada = new JPanel(new FlowLayout());
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Seleccionar Fecha para Reporte"));

        // Tabla para seleccionar fecha
        fechaTable = new JTable();
        JScrollPane fechaScrollPane = new JScrollPane(fechaTable);
        panelEntrada.add(fechaScrollPane);

        btnGenerarReporte = new JButton("Generar Reporte");
        panelEntrada.add(btnGenerarReporte);

        add(panelEntrada, BorderLayout.NORTH);

        // Panel central: Tabla de reporte
        tableModel = new DefaultTableModel(new Object[]{"Fecha", "Código Libro", "Cantidad"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel para mostrar la cantidad total de libros
        JPanel panelCantidad = new JPanel(new FlowLayout());
        lblCantidadTotal = new JLabel("Cantidad Total de Libros: 0");
        panelCantidad.add(lblCantidadTotal);
        add(panelCantidad, BorderLayout.SOUTH);

        // Panel inferior: Botón de impresión
        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Coloca el botón a la derecha
        panelBoton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añade espacio al borde
        btnImprimir = new JButton("Imprimir");
        panelBoton.add(btnImprimir);
        add(panelBoton, BorderLayout.SOUTH);

        // Configurar eventos
        configureButtonActions();

        // Cargar las fechas disponibles
        cargarFechasDisponibles();

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

    private void cargarFechasDisponibles() {
        // Obtener las fechas disponibles desde el Dao
        java.util.List<Date> fechas = bibliotecaDao.obtenerFechasDisponibles();

        // Configurar el modelo de la tabla para las fechas
        DefaultTableModel fechaTableModel = new DefaultTableModel(new Object[]{"Fecha de Préstamo"}, 0);
        for (Date fecha : fechas) {
            fechaTableModel.addRow(new Object[]{fecha});
        }
        fechaTable.setModel(fechaTableModel);
    }

    private void generarReporte() {
        // Limpiar la tabla de resultados
        tableModel.setRowCount(0);

        // Obtener la fecha seleccionada de la tabla
        int selectedRow = fechaTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una fecha.");
            return;
        }

        Date fechaSeleccionada = (Date) fechaTable.getValueAt(selectedRow, 0);

        // Obtener los resultados del reporte desde BibliotecaDao
        try {
            java.util.List<String[]> datos = bibliotecaDao.generarReportePorFecha(fechaSeleccionada);

            // Llenar la tabla con los datos obtenidos
            for (String[] fila : datos) {
                tableModel.addRow(fila);
            }

            // Obtener la cantidad total de libros
            int cantidadTotalLibros = bibliotecaDao.obtenerCantidadTotalLibros(fechaSeleccionada);
            lblCantidadTotal.setText("Cantidad Total de Libros: " + cantidadTotalLibros);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al obtener los datos del reporte: " + ex.getMessage());
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
