package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ModuloSeleccionForm extends javax.swing.JFrame {
    private final JButton btnContabilidad;
    private final JButton btnNomina;
    private final JButton btnSeleccion;
    private final JButton btnBiblioteca;
    private final JButton btnUsuarios;
    private final JButton btnRegresar;

    public ModuloSeleccionForm() {
        setTitle("Módulo de Selección");
        setSize(600, 500); // Pantalla completa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        btnContabilidad = new JButton("Contabilidad");
        panel.add(btnContabilidad);

        btnNomina = new JButton("Nómina");
        panel.add(btnNomina);

        btnSeleccion = new JButton("Selección");
        panel.add(btnSeleccion);

        btnBiblioteca = new JButton("Biblioteca");
        panel.add(btnBiblioteca);
        
        btnUsuarios = new JButton("Usuarios");
        panel.add(btnUsuarios);
        
        // Inicialización y agregado del botón Regresar
        btnRegresar = new JButton("Regresar");
        panel.add(btnRegresar); // Agregar al panel

        add(panel);

        // Asignar ActionListener a cada botón
        btnContabilidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarOpciones("Contabilidad", new String[]{"Tipo", "Cuenta", "Comprobante", "Balance General", "Estado Resultado"});
            }
        });

        btnNomina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarOpciones("Nómina", new String[]{"Ingreso", "Empleado", "Asiento Contable", "Valores a Pagar", "Reporte Ingresos/Egresos"});
            }
        });

        btnSeleccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarOpciones("Selección", new String[]{"Candidato", "Evaluación", "Contrato", "Ranking", "Reporte Candidato"});
            }
        });

        btnBiblioteca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarOpciones("Biblioteca", new String[]{"Autor", "Libro", "Préstamo", "Cantidad Libros", "Reporte Libros"});
            }
        });
        
        btnUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarUsuarios();
            }
        });
        
        btnRegresar.addActionListener(e -> dispose());
    }
    
    // Método para mostrar el formulario de Usuarios
    private void mostrarUsuarios() {
        new presentacion.mostrarUsuarios().setVisible(true); // Abre el formulario de Usuarios
    }
    // Método para mostrar las opciones de cada módulo
    private void mostrarOpciones(String modulo, String[] opciones) {
        String opcionSeleccionada = (String) JOptionPane.showInputDialog(
                this, 
                "Selecciona una opción para " + modulo, 
                modulo, 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                opciones, 
                opciones[0]);

        // Redirigir a los formularios correspondientes
        if (opcionSeleccionada != null) {
            switch (opcionSeleccionada) {
                case "Tipo":
                    // Llamar a la clase correspondiente de Contabilidad
                    new presentacion.Contabilidad.TipoForm().setVisible(true);
                    break;
                case "Cuenta":
                    // Llamar a la clase correspondiente de Contabilidad
                    new presentacion.Contabilidad.CuentaForm().setVisible(true);
                    break;
                case "Comprobante":
                    // Llamar a la clase correspondiente de Contabilidad
                    new presentacion.Contabilidad.ComprobanteForm().setVisible(true);
                    break;
                case "Balance General":
                    // Llamar a la clase correspondiente de Contabilidad
                    new presentacion.Contabilidad.BalanceGeneralForm().setVisible(true);
                    break;
                case "Estado Resultado":
                    // Llamar a la clase correspondiente de Contabilidad
                    new presentacion.Contabilidad.EstadoResultadoForm().setVisible(true);
                    break;
                case "Ingreso":
                    // Llamar a la clase correspondiente de Nómina
                    new presentacion.Nomina.IngresoForm().setVisible(true);
                    break;
                case "Empleado":
                    // Llamar a la clase correspondiente de Nómina
                    new presentacion.Nomina.EmpleadoForm().setVisible(true);
                    break;
                case "Asiento Contable":
                    // Llamar a la clase correspondiente de Nómina
                    new presentacion.Nomina.AsientoContableForm().setVisible(true);
                    break;
                case "Valores a Pagar":
                    // Llamar a la clase correspondiente de Nómina
                    new presentacion.Nomina.ValoresPagarForm().setVisible(true);
                    break;
                case "Reporte Ingresos/Egresos":
                    // Llamar a la clase correspondiente de Nómina
                    new presentacion.Nomina.ReporteIngresosEgresosForm().setVisible(true);
                    break;
                case "Candidato":
                    // Llamar a la clase correspondiente de Selección
                    new presentacion.Seleccion.CandidatoForm().setVisible(true);
                    break;
                case "Evaluación":
                    // Llamar a la clase correspondiente de Selección
                    new presentacion.Seleccion.EvaluacionForm().setVisible(true);
                    break;
                case "Contrato":
                    // Llamar a la clase correspondiente de Selección
                    new presentacion.Seleccion.ContratoForm().setVisible(true);
                    break;
                case "Ranking":
                    // Llamar a la clase correspondiente de Selección
                    new presentacion.Seleccion.RankingForm().setVisible(true);
                    break;
                case "Reporte Candidato":
                    // Llamar a la clase correspondiente de Selección
                    new presentacion.Seleccion.ReporteCandidatoForm().setVisible(true);
                    break;
                case "Autor":
                    // Llamar a la clase correspondiente de Biblioteca
                    new presentacion.Biblioteca.AutorForm().setVisible(true);
                    break;
                case "Libro":
                    // Llamar a la clase correspondiente de Biblioteca
                    new presentacion.Biblioteca.LibroForm().setVisible(true);
                    break;
                case "Préstamo":
                    // Llamar a la clase correspondiente de Biblioteca
                    new presentacion.Biblioteca.PrestamoForm().setVisible(true);
                    break;
                case "Cantidad Libros":
                    // Llamar a la clase correspondiente de Biblioteca
                    new presentacion.Biblioteca.CantidadLibrosForm().setVisible(true);
                    break;
                case "Reporte Libros":
                    // Llamar a la clase correspondiente de Biblioteca
                    new presentacion.Biblioteca.ReporteLibroForm().setVisible(true);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Opción no válida");
                    break;
            }
        }
    }
    

    // Métodos getter para los botones
    public JButton getBtnContabilidad() {
        return btnContabilidad;
    }

    public JButton getBtnNomina() {
        return btnNomina;
    }

    public JButton getBtnSeleccion() {
        return btnSeleccion;
    }

    public JButton getBtnBiblioteca() {
        return btnBiblioteca;
    }
    
    public JButton getBtnUsuarios() {
        return btnUsuarios;
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
        SwingUtilities.invokeLater(() -> {
            ModuloSeleccionForm form = new ModuloSeleccionForm();
            form.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
