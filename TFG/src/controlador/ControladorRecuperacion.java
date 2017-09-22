/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    
    public boolean validarCorreo(String mail){
        boolean validado = false;
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                        
        Matcher mather = pattern.matcher(mail);
        
        if(mather.find() == true){
            validado = true;
        }
        
        return validado;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == pass.btnEnviarPass){
            String mail = pass.txtCorreo.getText();
            
            System.out.println(validarCorreo(mail));
            
            if(!validarCorreo(mail)){
                JOptionPane.showMessageDialog(null, "Por favor introduzca el correo correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "Compruebe su correo, mensaje enviado!");
                pass.dispose();
            }
        }
        
        if(e.getSource() == pass.btnVolver){
            pass.dispose();
        }
    }
    
    
    
}
