/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import vista.clientes_vista;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class clientes_modelo {

    private String id;
    private String nombre;
    private String ci;
    private String telefono;
    private String direccion;
    private String tipo;

    Statement st = null;
    ResultSet rs = null;

    clientes_vista vista;
    DefaultTableModel modelo_cliente;

    public clientes_modelo(clientes_vista vista) {
        this.vista = vista;
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

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //funcionalidades
    //guardar
    public void guardar() {
        String sql = " insert into clientes(idcliente, cli_nombre, cli_ci, cli_telefono, cli_direccion, cli_tipo) "
                + "values ('" + id + "', '" + nombre + "', '" + ci + "', '" + telefono + "', '" + direccion + "', '" + tipo + "')";
        try {
            st = utilidades.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null, "Registro guardado");
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(producto_modelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void modificar() {
        String sql = " update clientes set cli_nombre ='" + nombre + "', cli_ci='" + ci + "', cli_telefono ='" + telefono + "', cli_direccion='" + direccion + "', cli_tipo ='" + tipo + "' where idcliente= '" + id + "' ";
        try {
            st = utilidades.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null, "Registro modificado");
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(producto_modelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void eliminar() {
        String sql = " delete from clientes where idcliente= '" + id + "' ";
        try {
            st = utilidades.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null, "Registro eliminado");
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(producto_modelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void mostrar_tabla_cliente() {
        vista.buscador_clientes.setSize(700, 400);
        vista.buscador_clientes.setLocationRelativeTo(vista);
        vista.buscador_clientes.setVisible(true);
        vista.txt_filtro.requestFocus();
    }

    //
    public void inicio_cliente() {
        modelo_cliente = (DefaultTableModel) vista.tabla_cliente.getModel();
        bloquear(false);
        desbloquear(true, false, false, false, true);
    }

    public void cargar_tabla_clientes(String filtro) {
        String sql = " select * from clientes where cli_nombre like '" + filtro + "%' ";
        try {
            st = utilidades.conexion.sta(st);
            rs = st.executeQuery(sql);
            modelo_cliente.setRowCount(0);
            while (rs.next()) {
                modelo_cliente.addRow(new Object[]{
                    rs.getString("idcliente"),
                    rs.getString("cli_nombre"),
                    rs.getString("cli_ci"),
                    rs.getString("cli_telefono"),
                    rs.getString("cli_direccion"),
                    rs.getString("cli_tipo")
                });
            }
            //enviamos los registro a la tabla
            vista.tabla_cliente.setModel(modelo_cliente);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(clientes_modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //enviar los registros cargados
    public void enviar_datos_de_la_tabla() {
        int fila = vista.tabla_cliente.getSelectedRow();

        vista.txt_id.setText(vista.tabla_cliente.getValueAt(fila, 0).toString());
        vista.txt_nombre.setText(vista.tabla_cliente.getValueAt(fila, 1).toString());
        vista.txt_cedula.setText(vista.tabla_cliente.getValueAt(fila, 2).toString());
        vista.txt_telefono.setText(vista.tabla_cliente.getValueAt(fila, 3).toString());
        vista.txt_direccion.setText(vista.tabla_cliente.getValueAt(fila, 4).toString());
        String tipo_cliente = vista.tabla_cliente.getValueAt(fila, 5).toString();
        if (tipo_cliente.equals("F")) {
            vista.rbn_frecuente.setSelected(true);
        } else {
            vista.rbn_casual.setSelected(true);
        }
        //ocultamos el jdialog
        vista.buscador_clientes.setVisible(false);
    }

    public void limpiar() {
        vista.txt_id.setText("");
        vista.txt_nombre.setText("");
        vista.txt_cedula.setText("");
        vista.txt_telefono.setText("");
        vista.txt_direccion.setText("");
        vista.rbn_frecuente.setSelected(true);
    }

    public void bloquear(boolean aux) {
        vista.txt_id.setEnabled(aux);
        vista.txt_nombre.setEnabled(aux);
        vista.txt_cedula.setEnabled(aux);
        vista.txt_telefono.setEnabled(aux);
        vista.txt_direccion.setEnabled(aux);
        vista.rbn_frecuente.setEnabled(aux);
        vista.rbn_casual.setEnabled(aux);
    }

    public void desbloquear(boolean n, boolean g, boolean m, boolean e, boolean b) {
        vista.btn_nuevo.setEnabled(n);
        vista.btn_guardar.setEnabled(g);
        vista.btn_modificar.setEnabled(m);
        vista.btn_buscar.setEnabled(b);
        vista.btn_eliminar.setEnabled(e);
    }

}
