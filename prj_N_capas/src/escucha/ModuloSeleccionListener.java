package escucha;

import presentacion.Biblioteca.AutorForm;
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
            new AutorForm().setVisible(true);
            form.dispose();
        } else if (source == form.getBtnUsuarios()) {
            // Llamar a un formulario o mostrar interfaz de usuarios (CRUD)
            JOptionPane.showMessageDialog(null, "Abrir módulo Usuarios ");
            // Aquí puedes abrir un nuevo formulario para gestionar usuarios
            // Ejemplo:
            // new UsuarioForm().setVisible(true);
        }
    }

}
