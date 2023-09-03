
package tiendas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Jair Ramirez
 */
public class Conex {
    Connection con;
    public Conex (){
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection ("jdbc:mysql://localhost:3306/tiendas","root", "");
        } catch (Exception e) {
            System.err.println("No se establecio la conexion "+e);
        }
    }
    public Connection getConnection (){
    return con;
    }   
    
    //metodo para reutilizar codigo
    public ResultSet Consulta (String sql){
    ResultSet res = null;
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            res =pstm.executeQuery();
        } catch (SQLException e) {
            System.err.println("error consulta "+ e.getMessage());
        }
        return res;
    }
    //metodo para obtener los datos de la base de datos
    public DefaultComboBoxModel obt_cliente(){
    DefaultComboBoxModel ListaModelo = new DefaultComboBoxModel();
    ListaModelo.addElement("--Seleccionar--");
    ResultSet res =this.Consulta("select * from usuarios order by nombre");
        try {
            while (res.next()){
            ListaModelo.addElement(res.getString("nombre"));
            }
            res.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ListaModelo;
    }
    
    public DefaultComboBoxModel obt_Item(){
    DefaultComboBoxModel ListaModelo = new DefaultComboBoxModel();
    ListaModelo.addElement("--Seleccionar--");
    ResultSet res =this.Consulta("select * from productos order by nom_item");
        try {
            while (res.next()){
            ListaModelo.addElement(res.getString("nom_item"));
            }
            res.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ListaModelo;
    }
    
}
