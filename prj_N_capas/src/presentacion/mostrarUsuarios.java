/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collection;
import javax.swing.table.DefaultTableModel;
import negocio.Usuario;
import negocio.UsuarioNegocio;
import recursos.GlobalException;
import recursos.NoDataExeption;

public class mostrarUsuarios extends JFrame {
    private JButton btnAgregar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JTable tablaUsuarios;

    public mostrarUsuarios() {
        setTitle("Gestión de Usuarios");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo la ventana
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Panel para los botones de acción
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 4));

        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnBuscar = new JButton("Buscar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);

        // Tabla de usuarios
        tablaUsuarios = new JTable();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID_USUARIO");
        modelo.addColumn("USUARIO");
        modelo.addColumn("PASSWORD");
        tablaUsuarios.setModel(modelo);

        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        add(panelBotones, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Agregar componentes a la ventana
        add(panelBotones, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Configurar eventos para botones
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción para agregar usuario
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción para editar usuario
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción para eliminar usuario
            }
        });


        
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
                    Collection<Usuario> usuarios = usuarioNegocio.obtenerUsuarios();

                    DefaultTableModel modelo = (DefaultTableModel) tablaUsuarios.getModel();
                    modelo.setRowCount(0);  // Limpiar la tabla

                    for (Usuario usuario : usuarios) {
                        modelo.addRow(new Object[]{usuario.getIdUsuario(), usuario.getUsuario(), usuario.getPassword()});
                    }
                } catch (GlobalException | NoDataExeption ex) {
                    JOptionPane.showMessageDialog(mostrarUsuarios.this, "Error al cargar usuarios: " + ex.getMessage());
                }
            }
        });

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
        SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new mostrarUsuarios().setVisible(true);
                }
            });
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
