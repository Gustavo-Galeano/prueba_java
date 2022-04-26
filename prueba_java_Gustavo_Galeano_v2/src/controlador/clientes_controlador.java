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
import modelo.clientes_modelo;
import vista.clientes_vista;

/**
 *
 * @author PC
 */
public class clientes_controlador implements ActionListener, KeyListener {

    clientes_vista vista;
    clientes_modelo modelo;

    public clientes_controlador(clientes_vista vista, clientes_modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.btn_guardar.addActionListener(this);
        this.vista.btn_modificar.addActionListener(this);
        this.vista.btn_eliminar.addActionListener(this);
        this.vista.btn_buscar.addActionListener(this);
        this.vista.txt_filtro.addKeyListener(this);
        this.vista.btn_aceptar.addActionListener(this);
        this.vista.btn_nuevo.addActionListener(this);
        this.vista.btn_cancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //cremos un objeto para manipular los eventos
        Object p = e.getSource();
        if (p.equals(vista.btn_guardar)) {
            modelo.setId(vista.txt_id.getText());
            modelo.setNombre(vista.txt_nombre.getText());
            modelo.setCi(vista.txt_cedula.getText());
            modelo.setTelefono(vista.txt_telefono.getText());
            modelo.setDireccion(vista.txt_direccion.getText());
            if (vista.rbn_frecuente.isSelected() == true) {
                modelo.setTipo("F");
            } else {
                modelo.setTipo("C");
            }
            modelo.guardar();
            modelo.limpiar();
            modelo.bloquear(true);
            modelo.desbloquear(true, false, false, false, true);
        }

        if (p.equals(vista.btn_modificar)) {
            modelo.setId(vista.txt_id.getText());
            modelo.setNombre(vista.txt_nombre.getText());
            modelo.setCi(vista.txt_cedula.getText());
            modelo.setTelefono(vista.txt_telefono.getText());
            modelo.setDireccion(vista.txt_direccion.getText());

            if (vista.rbn_frecuente.isSelected() == true) {
                modelo.setTipo("F");
            } else {
                modelo.setTipo("C");
            }

            modelo.modificar();
            modelo.limpiar();
            modelo.bloquear(false);
            modelo.desbloquear(true, false, false, false, true);
        }

        if (p.equals(vista.btn_eliminar)) {
            modelo.setId(vista.txt_id.getText());
            modelo.eliminar();

            modelo.guardar();
            modelo.limpiar();
            modelo.bloquear(false);
            modelo.desbloquear(true, false, false, false, true);
        }

        if (vista.btn_buscar.equals(p)) {
            modelo.mostrar_tabla_cliente();
            modelo.cargar_tabla_clientes("");
        }

        if (p.equals(vista.btn_aceptar)) {
            modelo.enviar_datos_de_la_tabla();

            modelo.bloquear(true);
            modelo.desbloquear(false, false, true, true, false);
        }
        if (p.equals(vista.btn_cancelar)) {
            modelo.limpiar();
            modelo.bloquear(false);
            modelo.desbloquear(true, false, false, false, true);
            JOptionPane.showMessageDialog(vista, "Cancelado");
        }

        if (p.equals(vista.btn_nuevo)) {
            modelo.limpiar();
            modelo.bloquear(true);
            modelo.desbloquear(true, true, false, false, true);
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
            modelo.cargar_tabla_clientes(vista.txt_filtro.getText());
        }
    }

}
