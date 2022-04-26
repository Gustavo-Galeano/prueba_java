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
import modelo.producto_modelo;
import vista.producto_vista;

/**
 *
 * @author PC
 */
public class productos_controlador implements ActionListener, KeyListener {

    producto_vista vista;
    producto_modelo modelo;

    public productos_controlador(producto_vista vista, producto_modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.btn_guardar.addActionListener(this);
        this.vista.btn_modificar.addActionListener(this);
        this.vista.btn_eliminar.addActionListener(this);
        this.vista.btn_buscar.addActionListener(this);

        this.vista.txt_filtro.addKeyListener(this);

        this.vista.btn_aceptar.addActionListener(this);
        this.vista.btn_cancelar.addActionListener(this);
        this.vista.btn_nuevo.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object p = e.getSource();
        if (p.equals(vista.btn_guardar)) {
            modelo.setId(vista.txt_id.getText());
            modelo.setNombre(vista.txt_nombre.getText());
            modelo.setCantidad(vista.txt_cantidad.getText());
            if (vista.rbn_5.isSelected() == true) {
                modelo.setIva("5%");
            } else {
                modelo.setIva("10%");
            }
            modelo.setCategoria(vista.txt_categoria.getText());
            modelo.setPrecio(vista.txt_precio.getText());

            modelo.guardar();
            modelo.limpiar();
            modelo.bloquear(false);
            modelo.desbloquear(true, false, false, false, true);
        }

        //instruccion para modificar
        if (p.equals(vista.btn_modificar)) {
            modelo.setId(vista.txt_id.getText());
            modelo.setNombre(vista.txt_nombre.getText());
            modelo.setCantidad(vista.txt_cantidad.getText());
            if (vista.rbn_5.isSelected() == true) {
                modelo.setIva("5%");
            } else {
                modelo.setIva("10%");
            }
            modelo.setCategoria(vista.txt_categoria.getText());
            modelo.setPrecio(vista.txt_precio.getText());

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
            modelo.mostrar_tabla_productos();
            modelo.cargar_tabla_producto("");
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
            modelo.cargar_tabla_producto(vista.txt_filtro.getText());
        }
    }

}
