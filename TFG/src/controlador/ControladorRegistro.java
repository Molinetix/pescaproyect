/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import modelo.TratamientoBD;
import vista.JFRegistro;
import vista.JFRegistro2;

/**
 *
 * @author Sergio
 */
public class ControladorRegistro implements ActionListener {

    private static final char[] CONSTS_HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    TratamientoBD bd = new TratamientoBD();
    JFRegistro registro = new JFRegistro();

    public ControladorRegistro(TratamientoBD bd, JFRegistro registro) {
        this.bd = bd;
        this.registro = registro;

        registro.btnSiguiente.addActionListener(this);
        registro.btnVolver.addActionListener(this);

        registro.jXDatePicker.setFormats("dd/MM/yyyy");
    }

    public static String encriptaEnMD5(String stringAEncriptar) {
        try {
            MessageDigest msgd = MessageDigest.getInstance("MD5");
            byte[] bytes = msgd.digest(stringAEncriptar.getBytes());
            StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length);
            for (int i = 0; i < bytes.length; i++) {
                int bajo = (int) (bytes[i] & 0x0f);
                int alto = (int) ((bytes[i] & 0xf0) >> 4);
                strbCadenaMD5.append(CONSTS_HEX[alto]);
                strbCadenaMD5.append(CONSTS_HEX[bajo]);
            }
            return strbCadenaMD5.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public boolean validarCorreo(String mail) {
        boolean validado = false;
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(mail);

        if (mather.find() == true) {
            validado = true;
        }

        return validado;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registro.btnSiguiente) {

            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");

            String nombre = null;
            String apellido = null;
            String fecha = null;
            String nacionalidad = null;
            String localidad = null;
            
            if (registro.txtNombre.getText().isEmpty()) {
                registro.labelNombre.setText("Introduzca un nombre");
            } else {
                nombre = registro.txtNombre.getText();
            }
            
            apellido = registro.txtApellidos.getText();
            fecha = String.valueOf(formater.format(registro.jXDatePicker.getDate()));

            String cp = registro.txtCP.getText();
            nacionalidad = registro.txtNacionalidad.getText();
            localidad = registro.txtLocalidad.getText();

            boolean comprobacion = false;

            if (comprobacion) {

                registro.dispose();

                JFRegistro2 registro2 = new JFRegistro2();
                registro2.setVisible(true);
                registro2.setLocationRelativeTo(null);

                if (e.getSource() == registro2.btnRegistrarse) {

                    System.out.println("esto es una prueba");

                    String correo = registro2.txtCorreo.getText();
                    String usuario = registro2.txtUsuario.getText();
                    String password = registro2.txtPass.getText();
                    String password2 = registro2.txtPass2.getText();

                    String passwordEncriptada = encriptaEnMD5(password);

                    if (!validarCorreo(correo)) {
                        JOptionPane.showMessageDialog(null, "El correo introducido no es correcto");
                    }

                    if (!password.matches(password2)) {
                        JOptionPane.showMessageDialog(null, "Las contraseñas deben coincidir");
                    }

                    if (validarCorreo(correo) && password.matches(password2)) {
                        System.out.println(passwordEncriptada);
                        try {
                            int resultado = bd.realizarRegistro(nombre, apellido, correo, fecha, nacionalidad, localidad, usuario, passwordEncriptada);
                            if (resultado > 0) {
                                JOptionPane.showMessageDialog(null, "Registro insertado con éxito");
                                registro.dispose();
                            } else {
                                System.out.println("Error en la inserción");
                            }
                        } catch (ControladorError ex) {
                            System.out.println(ex);
                        }
                    } else {
                        System.out.println("Error en el registro de usuario");
                    }
                }
            }
        }

        if (e.getSource() == registro.btnVolver) {
            registro.dispose();
        }
    }

}
