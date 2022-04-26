/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import modelo.personal_modelo;
import vista.personal_vista;

/**
 *
 * @author PC
 */
public class personal_controlador implements ActionListener, KeyListener {

    personal_vista vista;
    personal_modelo modelo;

    public personal_controlador(personal_vista vista, personal_modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.btn_guardar.addActionListener(this);
        this.vista.btn_modificar.addActionListener(this);
        this.vista.btn_eliminar.addActionListener(this);
        this.vista.btn_buscar.addActionListener(this);
        this.vista.txt_filtro.addKeyListener(this);
        this.vista.btnaceptar.addActionListener(this);        
        this.vista.btn_cancelar.addActionListener(this);        
        this.vista.btn_nuevo.addActionListener(this);        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //creamos un objeto para panipular los eventos
        Object p = e.getSource();

        //instruccion para guardar
        if (p.equals(vista.btn_guardar)) {
            modelo.setId(vista.txt_id.getText());
            modelo.setNombre(vista.txt_nombre.getText());
            modelo.setApellido(vista.txt_apellido.getText());
            modelo.setClave(vista.txt_clave.getText());
            modelo.setTipo(vista.cmb_tipo.getSelectedItem().toString());

            if (vista.rbn_activo.isSelected() == true) {
                modelo.setEstado("A");
            } else {
                modelo.setEstado("I");
            }

            modelo.setTelefono(vista.txt_telefono.getText());
            modelo.setDireccion(vista.txt_direccion.getText());
            modelo.guardar();
            modelo.limpiar();
            modelo.bloquear(false);
            modelo.desbloquear(true, false, false, false, true);
        }

        //instruccion para modificar
        if (p.equals(vista.btn_modificar)) {
            modelo.setId(vista.txt_id.getText());
            modelo.setNombre(vista.txt_nombre.getText());
            modelo.setApellido(vista.txt_apellido.getText());
            modelo.setClave(vista.txt_clave.getText());
            modelo.setTipo(vista.cmb_tipo.getSelectedItem().toString());

            if (vista.rbn_activo.isSelected() == true) {
                modelo.setEstado("A");
            } else {
                modelo.setEstado("I");
            }

            modelo.setTelefono(vista.txt_telefono.getText());
            modelo.setDireccion(vista.txt_direccion.getText());

            modelo.modificar();
            modelo.limpiar();
            modelo.bloquear(false);
            modelo.desbloquear(true, false, false, false, true);
        }

        //instruccion para eliminar
        if (p.equals(vista.btn_eliminar)) {
            modelo.setId(vista.txt_id.getText());
            modelo.eliminar();
            modelo.guardar();
            modelo.limpiar();
            modelo.bloquear(false);
            modelo.desbloquear(true, false, false, false, true);
        }

        //instruccion para mostrar tabla
        if (vista.btn_buscar.equals(p)) {
            modelo.mostrar_tabla_personal();
            modelo.cargar_tabla_personal("");
        }

        if (p.equals(vista.btnaceptar)) {
            modelo.enviar_datos_de_la_tabla();
            modelo.bloquear(true);
            modelo.desbloquear(false, false, true, true, false);
        }

        if (p.equals(vista.btn_nuevo)) {
            modelo.limpiar();
            modelo.bloquear(true);
            modelo.desbloquear(true, true, false, false, true);
        }

        if (p.equals(vista.btn_cancelar)) {
            modelo.limpiar();
            modelo.bloquear(false);
            modelo.desbloquear(true, false, false, false, true);
            JOptionPane.showMessageDialog(vista, "Cancelado");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Object p = e.getSource();
        if (p.equals(vista.txt_filtro)) {
            modelo.cargar_tabla_personal(vista.txt_filtro.getText());
        }
    }

}
