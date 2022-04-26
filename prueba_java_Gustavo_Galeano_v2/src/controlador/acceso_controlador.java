/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.acceso_modelo;
import vista.acceso_vista;

/**
 *
 * @author PC
 */
public class acceso_controlador implements ActionListener{
    acceso_vista vista;
    acceso_modelo modelo;

    public acceso_controlador(acceso_vista vista, acceso_modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        vista.btningresar.addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object p = e.getSource();
        if (p.equals(vista.btningresar)) {
            modelo.setNombre(vista.txtusuario.getText());
            modelo.setClave(vista.clave.getText());
            modelo.acceder();
        }
    }    
    
}
