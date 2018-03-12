/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.UsersDAO;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.User;
import servlets.Login;
import utils.Constantes;
import utils.PasswordHash;
import utils.Utils;

/**
 *
 * @author Eduardo DAW
 */
public class UsersServicios {

    public User addUser(User u){
        UsersDAO dao = new UsersDAO();      
        return dao.addJDBCTemplate(u);       
    }
    public int activarUser(String codigo_activacion){
        UsersDAO dao = new UsersDAO();       
        return dao.activarUser(codigo_activacion);
    }
    public int getUsersByName(String nombre){
        UsersDAO dao=new UsersDAO();
        return dao.getUsersByNameJDBCTemplate(nombre);
    }
    public String getUsersPass(String nombre){
        UsersDAO dao=new UsersDAO();
        return dao.getUsersPassJDBCTemplate(nombre);
    }
    public LocalDateTime getUsersDate(String codigo_activacion){
        UsersDAO dao=new UsersDAO();
        return dao.getUsersDateJDBCTemplate(codigo_activacion);
    }
    public int getUsersActivo(String nombre){
        UsersDAO dao=new UsersDAO();
        return dao.getUsersActivadoJDBCTemplate(nombre);
    }
    
    //COMPROBAR CORREO//
    public boolean validarCorreo(String email) {       
        boolean valido = false;       
        Pattern patronEmail = Pattern.compile(Constantes.REQUISITOS);    
        Matcher mEmail = patronEmail.matcher(email.toLowerCase());
        if (mEmail.matches()){
           valido = true;  
        }
        return valido;
    }
    //HACER EL HASH//
    public String hacerHash(String password){
        String hash="";
        try {
            hash = PasswordHash.getInstance().createHash(password);
            return hash;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
    }
    //HACER CODIGO//
    public String hacerCodigo(){
        return Utils.randomAlphaNumeric(20);
    }
    //MANDAR EL CORREO//
    public MandarMail sendCorreo(String dest,String sms,String tit){
        MandarMail mail = new MandarMail();
        mail.mandarMail(dest, sms,tit );
        return mail;
    }
}
