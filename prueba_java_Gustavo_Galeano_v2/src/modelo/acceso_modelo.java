/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.menu_controlador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vista.acceso_vista;
import vista.menu_vista;

/**
 *
 * @author PC
 */
public class acceso_modelo {
    private String nombre;
    private String clave;
    acceso_vista vista;
    
    Statement st;
    ResultSet rs;

    //constructor
    public acceso_modelo(acceso_vista vista) {
        this.vista = vista;
    }

    //geters and seters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void acceder(){
        String sql = " select * from personales where per_nombre = '"+nombre+"' and per_clave = '"+clave+"' ";
       // String estado, tipo;
        try {
            st = utilidades.conexion.sta(st);
            rs = st.executeQuery(sql);
            rs.next();
            if (rs.getRow() == 0) {
                JOptionPane.showMessageDialog(null, "[Error] Los datos no existen");               
            }
            else{
               menu_vista menu = new menu_vista();
               menu_modelo modelo = new menu_modelo(menu);
               menu_controlador controlador = new menu_controlador(menu, modelo);
               
               modelo.inicio();
               
               menu.setLocationRelativeTo(null);
               menu.setVisible(true);
               vista.setVisible(false);
//                estado = rs.getString("estado");
//                tipo = rs.getString("tipo");
//                if (estado.equals("A")) {
//                    
//                }
                //JOptionPane.showMessageDialog(null, "Personal incativo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(acceso_modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
