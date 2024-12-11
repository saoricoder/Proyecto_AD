package escucha;

import presentacion.BibliotecaForm;
import presentacion.ModuloSeleccionForm;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModuloSeleccionListener implements ActionListener {
    private ModuloSeleccionForm form;

    public ModuloSeleccionListener(ModuloSeleccionForm form) {
        this.form = form;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == form.getBtnContabilidad()) {
            JOptionPane.showMessageDialog(null, "Abrir módulo Contabilidad");
        } else if (source == form.getBtnNomina()) {
            JOptionPane.showMessageDialog(null, "Abrir módulo Nómina");
        } else if (source == form.getBtnSeleccion()) {
            JOptionPane.showMessageDialog(null, "Abrir módulo Selección");
        } else if (source == form.getBtnBiblioteca()) {
            new BibliotecaForm().setVisible(true);
            form.dispose();
        }
    }
}
