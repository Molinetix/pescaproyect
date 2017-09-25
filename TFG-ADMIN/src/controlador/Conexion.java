/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sergio
 */
public class Conexion {
    
    
    public Conexion(){
        
    }
    
    public Connection getConexion(){
        Connection con = null;
        
         try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tfgadminpescapp","root","");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {

        }
        return con;
    }
    
}
