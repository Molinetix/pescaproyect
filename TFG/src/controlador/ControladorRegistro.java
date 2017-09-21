/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.TratamientoBD;
import vista.JFRegistro;

/**
 *
 * @author Sergio
 */
public class ControladorRegistro implements ActionListener{
    
    TratamientoBD bd = new TratamientoBD();
    JFRegistro registro = new JFRegistro();

    public ControladorRegistro(TratamientoBD bd, JFRegistro registro){
        this.bd = bd;
        this.registro = registro;
        
        registro.btnRegistro.addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== registro.btnRegistro){
            JOptionPane.showMessageDialog(null, "Se registrará el usuario...");
        }
    }
    
    
    
}
