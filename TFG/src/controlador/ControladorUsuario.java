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
        
    
    }
    
    public String obtenerId(String identificador){
        String id = identificador;
        return id;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
    }
    
    
    
}
