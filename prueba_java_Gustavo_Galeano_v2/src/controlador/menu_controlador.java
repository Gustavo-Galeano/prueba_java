/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.menu_modelo;
import vista.menu_vista;

/**
 *
 * @author PC
 */
public class menu_controlador implements ActionListener{
    menu_vista vista;
    menu_modelo modelo;

    public menu_controlador(menu_vista vista, menu_modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.menu_vista_personales.addActionListener(this);
        this.vista.menu_vista_clientes.addActionListener(this);
        this.vista.menu_vista_producto.addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object p = e.getSource();
        if (p.equals(vista.menu_vista_personales)) {
            modelo.mostrar_personales();
        }
        if (p.equals(vista.menu_vista_clientes)) {
            modelo.mostrar_clientes();
        }
        if (p.equals(vista.menu_vista_producto)) {
            modelo.mostrar_productos();
        }
    }
    
}
