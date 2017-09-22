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
import vista.JFRecuperaPass;

/**
 *
 * @author Sergio
 */
public class ControladorRecuperacion implements ActionListener{
    
    TratamientoBD bd = new TratamientoBD();
    JFRecuperaPass pass = new JFRecuperaPass();
    
    public ControladorRecuperacion(TratamientoBD bd, JFRecuperaPass pass){
        this.bd = bd;
        this.pass = pass;
        
        pass.btnVolver.addActionListener(this);
        pass.btnEnviarPass.addActionListener(this);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == pass.btnEnviarPass){
            String mail = pass.txtCorreo.getText();
            if(mail.isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor introduce el correo...");
            }else{
                JOptionPane.showMessageDialog(null, "Compruebe su correo, mensaje enviado!");
            }
        }
        
        if(e.getSource() == pass.btnVolver){
            pass.dispose();
        }
    }
    
    
    
}
