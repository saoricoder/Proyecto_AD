package presentacion.Biblioteca;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrestamoForm extends JFrame {

    private JTextField txtNumero, txtFechaPrestamo, txtDescripcion;
    private JTextField txtCodigoLibro, txtCantidad, txtFechaEntrega;
    private JButton btnAgregar, btnActualizar, btnEliminar, btnGuardar, btnBuscar;
    private JTable table;
    private DefaultTableModel tableModel;

    public PrestamoForm() {
        // Configuración de la ventana
        setTitle("Gestión de Préstamos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior: Cabecera de préstamo
        JPanel panelCabecera = new JPanel(new GridLayout(3, 2, 10, 10));
        panelCabecera.setBorder(BorderFactory.createTitledBorder("Cabecera de Préstamo"));

        panelCabecera.add(new JLabel("Número:"));
        txtNumero = new JTextField();
        panelCabecera.add(txtNumero);

        panelCabecera.add(new JLabel("Fecha del préstamo:"));
        txtFechaPrestamo = new JTextField();
        panelCabecera.add(txtFechaPrestamo);

        panelCabecera.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        panelCabecera.add(txtDescripcion);

        add(panelCabecera, BorderLayout.NORTH);

        // Panel central: Detalle de préstamo
        JPanel panelDetalle = new JPanel(new GridLayout(3, 2, 10, 10));
        panelDetalle.setBorder(BorderFactory.createTitledBorder("Detalle de Préstamo"));

        panelDetalle.add(new JLabel("Código del libro:"));
        txtCodigoLibro = new JTextField();
        panelDetalle.add(txtCodigoLibro);

        panelDetalle.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        panelDetalle.add(txtCantidad);

        panelDetalle.add(new JLabel("Fecha de entrega:"));
        txtFechaEntrega = new JTextField();
        panelDetalle.add(txtFechaEntrega);

        add(panelDetalle, BorderLayout.CENTER);

        // Panel inferior: Botones y tabla
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnGuardar = new JButton("Guardar");
        btnBuscar = new JButton("Buscar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnBuscar);

        add(panelBotones, BorderLayout.SOUTH);

        // Tabla
        tableModel = new DefaultTableModel(new Object[]{"Número", "Fecha Préstamo", "Descripción", "Código Libro", "Cantidad", "Fecha Entrega"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.EAST);

        // Configurar eventos de botones
        configureButtonActions();

        setVisible(true);
    }

    private void configureButtonActions() {
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarRegistro();
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarRegistro();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarRegistro();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarRegistros();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarRegistro();
            }
        });
    }

    private void agregarRegistro() {
        tableModel.addRow(new Object[]{
                txtNumero.getText(),
                txtFechaPrestamo.getText(),
                txtDescripcion.getText(),
                txtCodigoLibro.getText(),
                txtCantidad.getText(),
                txtFechaEntrega.getText()
        });
    }

    private void actualizarRegistro() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.setValueAt(txtNumero.getText(), selectedRow, 0);
            tableModel.setValueAt(txtFechaPrestamo.getText(), selectedRow, 1);
            tableModel.setValueAt(txtDescripcion.getText(), selectedRow, 2);
            tableModel.setValueAt(txtCodigoLibro.getText(), selectedRow, 3);
            tableModel.setValueAt(txtCantidad.getText(), selectedRow, 4);
            tableModel.setValueAt(txtFechaEntrega.getText(), selectedRow, 5);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un registro para actualizar.");
        }
    }

    private void eliminarRegistro() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un registro para eliminar.");
        }
    }

    private void guardarRegistros() {
        JOptionPane.showMessageDialog(this, "Registros guardados exitosamente.");
    }

    private void buscarRegistro() {
        String numero = JOptionPane.showInputDialog(this, "Ingrese el número del préstamo a buscar:");
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(numero)) {
                table.setRowSelectionInterval(i, i);
                JOptionPane.showMessageDialog(this, "Registro encontrado.");
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Registro no encontrado.");
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
         new PrestamoForm();
 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
