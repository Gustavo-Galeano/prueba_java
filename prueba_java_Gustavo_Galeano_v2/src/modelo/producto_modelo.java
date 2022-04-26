/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.producto_vista;

/**
 *
 * @author PC
 */
public class producto_modelo {

    private String id;
    private String nombre;
    private String cantidad;
    private String iva;
    private String categoria;
    private String precio;

    Statement st = null;
    ResultSet rs = null;

    DefaultTableModel modelo_productos;

    producto_vista vista;

    public producto_modelo(producto_vista vistam) {
        this.vista = vistam;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    //funcionalidades del formulario personales
    //guardar => cargar base de datos
    public void guardar() {
        String sql = " insert into producto(idpersonal, pro_nombre, pro_cantidad, pro_iva, pro_categoria, pro_precio)"
                + "value ('" + id + "', '" + nombre + "', '" + cantidad + "', '" + iva + "', '" + categoria + "', '" + precio + "')";
        try {
            st = utilidades.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null, "Datos guardado");
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(producto_modelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    //modificar => modificamos los registros
    public void modificar() {
        String sql = " update producto set pro_nombre = '" + nombre + "', pro_cantidad = '" + cantidad + "', pro_iva= '" + iva + "', pro_categoria = '" + categoria + "', pro_precio = '" + precio + "' where idproducto= '" + id + "' ";
        try {
            st = utilidades.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(vista, "Registro modificado");
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(producto_modelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    //instruccion para eliminar
    public void eliminar() {
        String sql = " delete from producto where idproducto = '" + id + "' ";
        try {
            st = utilidades.conexion.sta(st);
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(vista, "Registro eliminado");
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(producto_modelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void mostrar_tabla_productos() {
        vista.buscador_productos.setSize(810, 420);
        vista.buscador_productos.setLocationRelativeTo(vista);
        vista.buscador_productos.setVisible(true);
        vista.txt_filtro.requestFocus(); //Aparecer el cursor en el filtro

    }

    public void inicio_productos() {
        modelo_productos = (DefaultTableModel) vista.tabla_productos.getModel();
        limpiar();
        bloquear(false);
        desbloquear(true, false, false, false, true);
    }

    public void cargar_tabla_producto(String filtro) {
        String sql = " select * from producto where pro_nombre like '" + filtro + "%' ";
        try {
            st = utilidades.conexion.sta(st);
            rs = st.executeQuery(sql);
            modelo_productos.setRowCount(0);
            while (rs.next()) {
                modelo_productos.addRow(new Object[]{
                    rs.getString("idproducto"),
                    rs.getString("pro_nombre"),
                    rs.getString("pro_cantidad"),
                    rs.getString("pro_iva"),
                    rs.getString("pro_categoria"),
                    rs.getString("pro_precio")
                });
            }
            //enviamos los registros a la tabla
            vista.tabla_productos.setModel(modelo_productos);
            st.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(producto_modelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void enviar_datos_de_la_tabla() {
        int fila = vista.tabla_productos.getSelectedRow();
        vista.txt_id.setText(vista.tabla_productos.getValueAt(fila, 0).toString());
        vista.txt_nombre.setText(vista.tabla_productos.getValueAt(fila, 1).toString());
        vista.txt_cantidad.setText(vista.tabla_productos.getValueAt(fila, 2).toString());
        String iva = vista.tabla_productos.getValueAt(fila, 3).toString();
        if (iva.equals("5")) {
            vista.rbn_5.setSelected(true);
        } else {
            vista.rbn_10.setSelected(true);
        }
        vista.txt_categoria.setText(vista.tabla_productos.getValueAt(fila, 4).toString());
        vista.txt_precio.setText(vista.tabla_productos.getValueAt(fila, 5).toString());

        vista.buscador_productos.setVisible(false);
    }

    public void limpiar() {
        vista.txt_id.setText("");
        vista.txt_nombre.setText("");
        vista.txt_cantidad.setText("");
        vista.rbn_5.setSelected(true);
        vista.txt_categoria.setText("");
        vista.txt_precio.setText("");
    }

    public void bloquear(boolean aux) {
        vista.txt_id.setEnabled(aux);
        vista.txt_nombre.setEnabled(aux);
        vista.txt_cantidad.setEnabled(aux);
        vista.rbn_5.setEnabled(aux);
        vista.rbn_10.setEnabled(aux);
        vista.txt_categoria.setEnabled(aux);
        vista.txt_precio.setEnabled(aux);
    }

    public void desbloquear(boolean n, boolean g, boolean m, boolean e, boolean b) {
        vista.btn_nuevo.setEnabled(n);
        vista.btn_guardar.setEnabled(g);
        vista.btn_modificar.setEnabled(m);
        vista.btn_eliminar.setEnabled(e);
        vista.btn_buscar.setEnabled(b);
    }
}
