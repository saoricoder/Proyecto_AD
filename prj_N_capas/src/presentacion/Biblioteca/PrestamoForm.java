package presentacion.Biblioteca;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PrestamoForm extends JFrame {

    // Componentes de la interfaz
    private JTextField txtNumero, txtFechaPrestamo, txtDescripcion, txtCodigoLibro, txtCantidad, txtFechaEntrega;
    private JButton btnGuardar, btnBuscar, btnModificar, btnEliminar, btnRegresar;
    private JTable tableCabecera, tableDetalle;
    private DefaultTableModel modelCabecera, modelDetalle;

    // Conexión a la base de datos
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "SYSTEM";
    private static final String PASSWORD = "23889";

    public PrestamoForm() {
        setTitle("Gestión de Préstamos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior: Formulario de cabecera
        JPanel panelCabecera = new JPanel(new GridLayout(4, 2, 10, 10));
        panelCabecera.setBorder(BorderFactory.createTitledBorder("Cabecera del Préstamo"));

        panelCabecera.add(new JLabel("Número:"));
        txtNumero = new JTextField();
        panelCabecera.add(txtNumero);

        panelCabecera.add(new JLabel("Fecha del Préstamo (YYYY-MM-DD):"));
        txtFechaPrestamo = new JTextField();
        panelCabecera.add(txtFechaPrestamo);

        panelCabecera.add(new JLabel("Descripción (Autor):"));
        txtDescripcion = new JTextField();
        panelCabecera.add(txtDescripcion);

        // Panel central: Formulario de detalle
        JPanel panelDetalle = new JPanel(new GridLayout(3, 2, 10, 10));
        panelDetalle.setBorder(BorderFactory.createTitledBorder("Detalle del Préstamo"));

        panelDetalle.add(new JLabel("Código de Libro (ISBN):"));
        txtCodigoLibro = new JTextField();
        panelDetalle.add(txtCodigoLibro);

        panelDetalle.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        panelDetalle.add(txtCantidad);

        panelDetalle.add(new JLabel("Fecha de Entrega (YYYY-MM-DD):"));
        txtFechaEntrega = new JTextField();
        panelDetalle.add(txtFechaEntrega);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnGuardar = new JButton("Guardar");
        btnBuscar = new JButton("Buscar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnRegresar = new JButton("Regresar");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnRegresar);

        // Panel de tablas
        modelCabecera = new DefaultTableModel(new String[]{"Número", "Fecha", "Descripción"}, 0);
        tableCabecera = new JTable(modelCabecera);

        modelDetalle = new DefaultTableModel(new String[]{"Código Libro", "Cantidad", "Fecha Entrega"}, 0);
        tableDetalle = new JTable(modelDetalle);

        JPanel panelTablas = new JPanel(new GridLayout(2, 1));
        panelTablas.setBorder(BorderFactory.createTitledBorder("Registros"));
        panelTablas.add(new JScrollPane(tableCabecera));
        panelTablas.add(new JScrollPane(tableDetalle));

        // Agregar componentes al frame
        add(panelCabecera, BorderLayout.NORTH);
        add(panelDetalle, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        add(panelTablas, BorderLayout.EAST);

        // Eventos
        btnGuardar.addActionListener(e -> guardarPrestamo());
        btnBuscar.addActionListener(e -> buscarPrestamo());
        btnModificar.addActionListener(e -> modificarPrestamo());
        btnEliminar.addActionListener(e -> eliminarPrestamo());
        btnRegresar.addActionListener(e -> dispose());
        

        setVisible(true);
    }

    private void guardarPrestamo() {
        String numero = txtNumero.getText();
        String fechaPrestamo = txtFechaPrestamo.getText();
        String descripcion = txtDescripcion.getText();
        String codigoLibro = txtCodigoLibro.getText();
        String cantidad = txtCantidad.getText();
        String fechaEntrega = txtFechaEntrega.getText();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Insertar en CABECERAPRESTAMO
            String queryCabecera = "INSERT INTO CABECERAPRESTAMO (NUMERO, FECHAPRESTAMO, DESCRIPCION) VALUES (?, TO_DATE(?, 'YYYY-MM-DD'), ?)";
            PreparedStatement psCabecera = conn.prepareStatement(queryCabecera);
            psCabecera.setString(1, numero);
            psCabecera.setString(2, fechaPrestamo);
            psCabecera.setString(3, descripcion);
            psCabecera.executeUpdate();

            // Insertar en DETALLEPRESTAMO
            String queryDetalle = "INSERT INTO DETALLEPRESTAMO (COD_LIBRO, CANTIDAD, FECHA) VALUES (?, ?, TO_DATE(?, 'YYYY-MM-DD'))";
            PreparedStatement psDetalle = conn.prepareStatement(queryDetalle);
            psDetalle.setString(1, codigoLibro);
            psDetalle.setString(2, cantidad);
            psDetalle.setString(3, fechaEntrega);
            psDetalle.executeUpdate();

            JOptionPane.showMessageDialog(this, "Préstamo guardado exitosamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el préstamo: " + e.getMessage());
        }
    }

    private void buscarPrestamo() {
    // Limpiar las tablas antes de cargar nuevos resultados
    modelCabecera.setRowCount(0);
    modelDetalle.setRowCount(0);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Consulta para obtener los registros de la tabla CABECERAPRESTAMO
            String queryCabecera = "SELECT NUMERO, TO_CHAR(FECHAPRESTAMO, 'YYYY-MM-DD') AS FECHAPRESTAMO, DESCRIPCION FROM CABECERAPRESTAMO";
            PreparedStatement psCabecera = conn.prepareStatement(queryCabecera);
            ResultSet rsCabecera = psCabecera.executeQuery();

            // Rellenar la tabla de la cabecera con los resultados de la consulta
            while (rsCabecera.next()) {
                String numero = rsCabecera.getString("NUMERO");
                String fechaPrestamo = rsCabecera.getString("FECHAPRESTAMO");
                String descripcion = rsCabecera.getString("DESCRIPCION");
                modelCabecera.addRow(new Object[]{numero, fechaPrestamo, descripcion});
            }

            // Consulta para obtener los registros de la tabla DETALLEPRESTAMO
            String queryDetalle = "SELECT COD_LIBRO, CANTIDAD, TO_CHAR(FECHA, 'YYYY-MM-DD') AS FECHA FROM DETALLEPRESTAMO";
            PreparedStatement psDetalle = conn.prepareStatement(queryDetalle);
            ResultSet rsDetalle = psDetalle.executeQuery();

            // Rellenar la tabla de detalle con los resultados de la consulta
            while (rsDetalle.next()) {
                String codigoLibro = rsDetalle.getString("COD_LIBRO");
                String cantidad = rsDetalle.getString("CANTIDAD");
                String fechaEntrega = rsDetalle.getString("FECHA");
                modelDetalle.addRow(new Object[]{codigoLibro, cantidad, fechaEntrega});
            }

            JOptionPane.showMessageDialog(this, "Registros cargados correctamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los registros: " + e.getMessage());
        }
    }


    private void modificarPrestamo() {
        String numero = txtNumero.getText();
        String fechaPrestamo = txtFechaPrestamo.getText();
        String descripcion = txtDescripcion.getText();
        String codigoLibro = txtCodigoLibro.getText();
        String cantidad = txtCantidad.getText();
        String fechaEntrega = txtFechaEntrega.getText();

        if (numero.isEmpty() || fechaPrestamo.isEmpty() || descripcion.isEmpty() || 
            codigoLibro.isEmpty() || cantidad.isEmpty() || fechaEntrega.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Actualizar en CABECERAPRESTAMO
            String queryCabecera = "UPDATE CABECERAPRESTAMO SET FECHAPRESTAMO = TO_DATE(?, 'YYYY-MM-DD'), DESCRIPCION = ? WHERE NUMERO = ?";
            PreparedStatement psCabecera = conn.prepareStatement(queryCabecera);
            psCabecera.setString(1, fechaPrestamo);
            psCabecera.setString(2, descripcion);
            psCabecera.setString(3, numero);
            psCabecera.executeUpdate();

            // Actualizar en DETALLEPRESTAMO
            String queryDetalle = "UPDATE DETALLEPRESTAMO SET CANTIDAD = ?, FECHA = TO_DATE(?, 'YYYY-MM-DD') WHERE COD_LIBRO = ? AND NUMERO = ?";
            PreparedStatement psDetalle = conn.prepareStatement(queryDetalle);
            psDetalle.setString(1, cantidad);
            psDetalle.setString(2, fechaEntrega);
            psDetalle.setString(3, codigoLibro);
            psDetalle.setString(4, numero);
            psDetalle.executeUpdate();

            JOptionPane.showMessageDialog(this, "Préstamo modificado exitosamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al modificar el préstamo: " + e.getMessage());
        }
    }


    private void eliminarPrestamo() {
        String numero = txtNumero.getText();

        if (numero.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el número de préstamo para eliminar.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Eliminar de DETALLEPRESTAMO
            String queryDetalle = "DELETE FROM DETALLEPRESTAMO WHERE NUMERO = ?";
            PreparedStatement psDetalle = conn.prepareStatement(queryDetalle);
            psDetalle.setString(1, numero);
            psDetalle.executeUpdate();

            // Eliminar de CABECERAPRESTAMO
            String queryCabecera = "DELETE FROM CABECERAPRESTAMO WHERE NUMERO = ?";
            PreparedStatement psCabecera = conn.prepareStatement(queryCabecera);
            psCabecera.setString(1, numero);
            psCabecera.executeUpdate();

            JOptionPane.showMessageDialog(this, "Préstamo eliminado exitosamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el préstamo: " + e.getMessage());
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
        new PrestamoForm();
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

