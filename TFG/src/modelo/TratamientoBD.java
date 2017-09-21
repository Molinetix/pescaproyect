/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.Conexion;
import controlador.ControladorError;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sergio
 */
public class TratamientoBD {
    
    Conexion con = null;
    
    public TratamientoBD(){
        
        con = new Conexion();
        
    }
    
    public ArrayList<Usuario> comprobarLogin(String usuario, String password) throws ControladorError{
        ArrayList<Usuario> obtenUsuario = new ArrayList();
        Usuario user;
        Connection accesoDB = con.getConexion();
        
        try {
            CallableStatement cs = accesoDB.prepareCall("{call sp_comprobar(?,?)}");
            cs.setString(1, usuario);
            cs.setString(2, password);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                user = new Usuario();
                user.setNombre(rs.getString(2));
                user.setApellido(rs.getString(3));
                user.setFecha(rs.getString(4));
                user.setNacionalidad(rs.getString(5));
                user.setLocalidad(rs.getString(6));
                user.setUsuario(rs.getString(7));
                user.setContrasenia(rs.getString(8));
                
                obtenUsuario.add(user);
                
            }
            
                 
            
        } catch (SQLException ex) {
            throw new ControladorError("Error en la consulta");
        }
        
        
        return obtenUsuario;
    }
    
    
    public int realizarRegistro(String nombre,String apellido,String fecha, String nacionalidad,String localidad, String usuario, String password) throws ControladorError{
        int respuesta = 0;
        
        Connection accesoDB = con.getConexion();
        
        try {
            CallableStatement cs = accesoDB.prepareCall("{call sp_registrar(?,?,?,?,?,?,?)}");
            cs.setString(1, nombre);
            cs.setString(2, apellido);
            cs.setString(3, fecha);
            cs.setString(4, nacionalidad);
            cs.setString(5, localidad);
            cs.setString(6, usuario);
            cs.setString(7, password);
            
            respuesta = cs.executeUpdate();
            
        } catch (SQLException ex) {
            throw new ControladorError("Error en la consulta");
        }
        
        return respuesta;
    }
}
