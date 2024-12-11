

package presentacion;

import escucha.ModuloSeleccionListener;
import javax.swing.*;
import java.awt.*;


public class ModuloSeleccionForm extends javax.swing.JFrame {
    private JButton btnContabilidad;
    private JButton btnNomina;
    private JButton btnSeleccion;
    private JButton btnBiblioteca;
    
        public ModuloSeleccionForm() {
        setTitle("Módulo de Selección");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        btnContabilidad = new JButton("Contabilidad");
        panel.add(btnContabilidad);

        btnNomina = new JButton("Nómina");
        panel.add(btnNomina);

        btnSeleccion = new JButton("Selección");
        panel.add(btnSeleccion);

        btnBiblioteca = new JButton("Biblioteca");
        panel.add(btnBiblioteca);

        add(panel);

        // Crear el listener y asignarlo a los botones
        ModuloSeleccionListener listener = new ModuloSeleccionListener(this);
        btnContabilidad.addActionListener(listener);
        btnNomina.addActionListener(listener);
        btnSeleccion.addActionListener(listener);
        btnBiblioteca.addActionListener(listener);
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
