/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.clientes_controlador;
import controlador.personal_controlador;
import controlador.productos_controlador;
import vista.menu_vista;
import vista.fondo;
import java.awt.BorderLayout;
import vista.clientes_vista;
import vista.personal_vista;
import vista.producto_vista;

/**
 *
 * @author PC
 */
public class menu_modelo {

    menu_vista vista;

    public menu_modelo(menu_vista vista) {
        this.vista = vista;
    }

    public void inicio() {
        fondo F = new fondo();
        vista.add(F, BorderLayout.CENTER);
        vista.pack();
    }

    public void mostrar_personales() {
        personal_vista vistap = new personal_vista();
        personal_modelo modelo = new personal_modelo(vistap);
        personal_controlador controlador = new personal_controlador(vistap, modelo);
        vistap.setLocationRelativeTo(null);
        //cargamos la tabla personales
        modelo.inicio();
        vistap.setVisible(true);
    }

    public void mostrar_clientes() {
        clientes_vista vistac = new clientes_vista();
        clientes_modelo modelo = new clientes_modelo(vistac);
        clientes_controlador controlador = new clientes_controlador(vistac, modelo);
        vistac.setLocationRelativeTo(null);
        modelo.inicio_cliente();
        vistac.setVisible(true);
    }

    public void mostrar_productos() {
        producto_vista vistam = new producto_vista();
        producto_modelo modelo = new producto_modelo(vistam);
        productos_controlador controlador = new productos_controlador(vistam, modelo);
        vistam.setLocationRelativeTo(null);
        modelo.inicio_productos();
        vistam.setVisible(true);
    }
}
