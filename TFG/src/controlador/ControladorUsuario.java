/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.TratamientoBD;
import vista.JFInicio;
import vista.JFLogin;

/**
 *
 * @author Sergio
 */
public class ControladorUsuario implements ActionListener{
    
    JFInicio inicio = new JFInicio();
    TratamientoBD bd = new TratamientoBD();
    
    public ControladorUsuario(){
        
    }
    
    public ControladorUsuario(JFInicio inicio, TratamientoBD bd){
        this.bd = bd;
        this.inicio = inicio;
        
        inicio.btnCerrar.addActionListener(this);
        
    
    }
    
    public String obtenerId(String identificador){
        String id = identificador;
        return id;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == inicio.btnCerrar){
            
            System.out.println();
            System.out.println("cerrando sesión...");
            
            inicio.dispose();
            
        }
        
        
    }
    
    
    
}
