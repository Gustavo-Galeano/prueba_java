/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.personal_vista;

/**
 *
 * @author PC
 */
public class personal_modelo {

    private String id;
    private String nombre;
    private String apellido;
    private String clave;
    private String tipo;
    private String estado;
    private String telefono;
    private String direccion;

    Statement st = null;
    ResultSet rs = null;

    DefaultTableModel modelo_personal;

    personal_vista vista;

    public personal_modelo(personal_vista vista) {
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    //funcionalidades del formulario personales
    //guardar => cargar base de datos
    public void guardar() {
        String sql = " insert into personales(idpersonal, per_nombre, per_apellido, per_clave, per_tipo, per_estado, per_telefono, per_direccion) value ('" + id + "', '" + nombre + "', '" + apellido + "', '" + clave + "', '" + tipo + "', '" + estado + "', '" + telefono + "', '" + direccion + "')";
        try {
            st = utilidades.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(null, "Datos Guardados");
        } catch (SQLException ex) {
            Logger.getLogger(personal_modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //modificar => modificamos los registros
    public void modificar() {
        String sql = " update personales set per_nombre = '" + nombre + "', per_apellido = '" + apellido + "', per_clave = '" + clave + "', per_estado = '" + estado + "', per_telefono = '" + telefono + "', per_direccion = '" + direccion + "' where idpersonal= '" + id + "' ";
        try {
            st = utilidades.conexion.sta(st);
            st.executeUpdate(sql);
            st.close();
            JOptionPane.showMessageDialog(vista, "Datos Modificados");
        } catch (SQLException ex) {
            Logger.getLogger(personal_modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //instruccion para eliminar
    public void eliminar() {
        String sql = " delete from personales where idpersonal = '" + id + "' ";
        try {
            st = utilidades.conexion.sta(st);
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(vista, "Datos Eliminados");
        } catch (SQLException ex) {
            Logger.getLogger(personal_modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrar_tabla_personal() {
        vista.buscador_de_personal.setSize(800, 400);
        vista.buscador_de_personal.setLocationRelativeTo(vista);
        vista.buscador_de_personal.setVisible(true);
        vista.txt_filtro.requestFocus(); //Aparecer el cursor en el filtro

    }

    public void inicio() {
        modelo_personal = (DefaultTableModel) vista.tabla_buscador_personal.getModel();
        limpiar();
        bloquear(false);
        desbloquear(true, false, false, false, true);
    }

    public void cargar_tabla_personal(String filtro) {
        String sql = " select * from personales where per_nombre like '" + filtro + "%' ";
        try {
            st = utilidades.conexion.sta(st);
            rs = st.executeQuery(sql);
            modelo_personal.setRowCount(0);
            while (rs.next()) {
                modelo_personal.addRow(new Object[]{
                    rs.getString("idpersonal"),
                    rs.getString("per_nombre"),
                    rs.getString("per_apellido"),
                    rs.getString("per_clave"),
                    rs.getString("per_tipo"),
                    rs.getString("per_estado"),
                    rs.getString("per_telefono"),
                    rs.getString("per_direccion")
                });
            }
            //enviamos los registros a la tabla
            vista.tabla_buscador_personal.setModel(modelo_personal);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(personal_modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviar_datos_de_la_tabla() {
        int fila = vista.tabla_buscador_personal.getSelectedRow();
        vista.txt_id.setText(vista.tabla_buscador_personal.getValueAt(fila, 0).toString());
        vista.txt_nombre.setText(vista.tabla_buscador_personal.getValueAt(fila, 1).toString());
        vista.txt_apellido.setText(vista.tabla_buscador_personal.getValueAt(fila, 2).toString());
        vista.txt_clave.setText(vista.tabla_buscador_personal.getValueAt(fila, 3).toString());
        vista.cmb_tipo.setSelectedItem(vista.tabla_buscador_personal.getValueAt(fila, 4).toString());

        String estado = vista.tabla_buscador_personal.getValueAt(fila, 5).toString();
        if (estado.equals("A")) {
            vista.rbn_activo.setSelected(true);
        } else {
            vista.rbn_inactivo.setSelected(true);
        }

        vista.txt_telefono.setText(vista.tabla_buscador_personal.getValueAt(fila, 6).toString());
        vista.txt_direccion.setText(vista.tabla_buscador_personal.getValueAt(fila, 7).toString());

        vista.buscador_de_personal.setVisible(false);
    }

    public void limpiar() {
        vista.txt_id.setText("");
        vista.txt_nombre.setText("");
        vista.txt_apellido.setText("");
        vista.txt_clave.setText("");
        vista.cmb_tipo.setSelectedIndex(0);
        vista.rbn_activo.setSelected(true);
        vista.txt_telefono.setText("");
        vista.txt_direccion.setText("");
    }

    public void bloquear(boolean aux) {
        vista.txt_id.setEnabled(aux);
        vista.txt_nombre.setEnabled(aux);
        vista.txt_apellido.setEnabled(aux);
        vista.txt_clave.setEnabled(aux);
        vista.cmb_tipo.setEnabled(aux);
        vista.rbn_activo.setEnabled(aux);
        vista.rbn_inactivo.setEnabled(aux);
        vista.txt_telefono.setEnabled(aux);
        vista.txt_direccion.setEnabled(aux);
    }
    
    public void desbloquear(boolean n, boolean g, boolean m, boolean e, boolean b) {
        vista.btn_nuevo.setEnabled(n);
        vista.btn_guardar.setEnabled(g);
        vista.btn_modificar.setEnabled(m);
        vista.btn_buscar.setEnabled(b);
        vista.btn_eliminar.setEnabled(e);
    }
}
