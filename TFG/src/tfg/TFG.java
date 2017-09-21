/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfg;

import controlador.Conexion;
import controlador.ControladorInterno;
import modelo.TratamientoBD;
import vista.JFInicio;
import vista.JFLogin;

/**
 *
 * @author Sergio
 */
public class TFG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        JFLogin login = new JFLogin();
        TratamientoBD bd = new TratamientoBD();
        Conexion conexion = new Conexion();
        
        if(conexion.getConexion() != null){
            
            ControladorInterno contInt = null;
            contInt = new ControladorInterno(login,bd);
            login.setVisible(true);
            login.setLocationRelativeTo(null);
            
            
        }else{
            System.out.println("No se puede acceder a la base de datos");
        }
        
       
    }
    
}
