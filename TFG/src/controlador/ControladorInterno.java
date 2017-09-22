/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.TratamientoBD;
import modelo.Usuario;
import vista.JFInicio;
import vista.JFLogin;
import vista.JFRegistro;

/**
 *
 * @author Sergio
 */
public class ControladorInterno implements ActionListener {

    JFLogin panelLogin = new JFLogin();
    TratamientoBD trataBD = new TratamientoBD();

    public ControladorInterno(JFLogin panelLogin, TratamientoBD trataBD) {

        this.panelLogin = panelLogin;
        this.trataBD = trataBD;

        panelLogin.btnEntrar.addActionListener(this);
        panelLogin.btnRegistrar.addActionListener(this);

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

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == panelLogin.btnEntrar) {
            if (compruebaCampos() == true) {
                System.out.println("comprobando....");
                String usuario = panelLogin.txtUsuario.getText();
                String password = panelLogin.txtPassword.getText();

                ArrayList<Usuario> datosUsuario = new ArrayList();

                try {
                    datosUsuario = trataBD.comprobarLogin(usuario, password);
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
    }

}
