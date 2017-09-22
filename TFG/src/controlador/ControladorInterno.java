/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.TratamientoBD;
import modelo.Usuario;
import vista.JFInicio;
import vista.JFLogin;
import vista.JFRegistro;
import vista.JFRecuperaPass;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Sergio
 */
public class ControladorInterno implements ActionListener {

    private static final char[] CONSTS_HEX = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };

    
    JFLogin panelLogin = new JFLogin();
    TratamientoBD trataBD = new TratamientoBD();

    public ControladorInterno(JFLogin panelLogin, TratamientoBD trataBD) {

        this.panelLogin = panelLogin;
        this.trataBD = trataBD;

        panelLogin.btnEntrar.addActionListener(this);
        panelLogin.btnRegistrar.addActionListener(this);
        panelLogin.btnRecuperaPass.addActionListener(this);
        
        panelLogin.btnRecuperaPass.setFocusPainted(false);
        //panelLogin.btnRecuperaPass.setMargin(new Insets(0, 0, 0, 0));
        panelLogin.btnRecuperaPass.setContentAreaFilled(false);
        panelLogin.btnRecuperaPass.setBorderPainted(false);
        panelLogin.btnRecuperaPass.setOpaque(false);

    }

    public boolean compruebaCampos() {
        boolean comprobacion = false;

        String usuario = panelLogin.txtUsuario.getText();
        String password = panelLogin.txtPassword.getText();

        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, rellena los campos correctamente");
        } else {
            comprobacion = true;
        }

        return comprobacion;
    }

     public static String encriptaEnMD5(String stringAEncriptar)
    {
        try
        {
           MessageDigest msgd = MessageDigest.getInstance("MD5");
           byte[] bytes = msgd.digest(stringAEncriptar.getBytes());
           StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length);
           for (int i = 0; i < bytes.length; i++)
           {
               int bajo = (int)(bytes[i] & 0x0f);
               int alto = (int)((bytes[i] & 0xf0) >> 4);
               strbCadenaMD5.append(CONSTS_HEX[alto]);
               strbCadenaMD5.append(CONSTS_HEX[bajo]);
           }
           return strbCadenaMD5.toString();
        } catch (NoSuchAlgorithmException e) {
           return null;
        }
    }
    
    
    public void volverLogin() {

    }

    public void accederInicio(String usuario) {

        JFInicio inicio = new JFInicio();

        inicio.setVisible(true);
        inicio.setLocationRelativeTo(null);
        ControladorUsuario contUser = new ControladorUsuario(inicio, trataBD);
        contUser.inicio.txtUsuario.setText(usuario);
    }

    public void accederRegistro() {
        JFRegistro registro = new JFRegistro();
        registro.setVisible(true);
        registro.setLocationRelativeTo(null);

        ControladorRegistro contRegistro = null;
        contRegistro = new ControladorRegistro(trataBD, registro);
    }
    
    public void accederRecuperacion(){
        JFRecuperaPass newPass = new JFRecuperaPass();
        newPass.setVisible(true);
        newPass.setLocationRelativeTo(null);
        
        ControladorRecuperacion recuperacion = new ControladorRecuperacion(trataBD, newPass);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == panelLogin.btnEntrar) {
            if (compruebaCampos() == true) {
                
                System.out.println("comprobando....");
                String usuario = panelLogin.txtUsuario.getText();
                String password = panelLogin.txtPassword.getText();
                String passwordCodificada = encriptaEnMD5(password);

                ArrayList<Usuario> datosUsuario = new ArrayList();
                
                try {
                    datosUsuario = trataBD.comprobarLogin(usuario, passwordCodificada);
                } catch (ControladorError ex) {
                    System.out.println(ex);
                }

                if (datosUsuario.size() < 1) {
                    JOptionPane.showMessageDialog(null, "El usuario o la contraseña son incorrectos.");
                    System.out.println("Error en la consulta");
                } else {

                    String user = datosUsuario.get(0).getUsuario();

                    System.out.println();
                    System.out.println("accediendo al panel de usuario...");

                    accederInicio(usuario);

                }

            }
        }
        if (e.getSource() == panelLogin.btnRegistrar) {

            accederRegistro();

        }

        if (e.getSource() == panelLogin.btnRecuperaPass) {
            accederRecuperacion();
            System.out.println("recuperando pass...");

        }
    }

}
