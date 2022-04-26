/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class conexion {
    static Connection con = null;    
    static Statement st = null;
    static String bd = "pruebaJava?autoReconnet=true&useSSL=false";
    static String usuario = "root";
    static String clave = "";
    static String url = "jdbc:mysql://localhost:3306/" + bd;
    
    public static Connection Enlace (Connection conn) throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, usuario, clave);
            System.out.print("Conexion Exitosa");
        } catch (Exception e) {
            System.out.print("[ERROR] Clase no encontrada");
        }
        return conn;
    }
    
    public static Statement sta(Statement st) throws SQLException{
        con = Enlace(con);
        st = con.createStatement();
        return st;
    }
}
