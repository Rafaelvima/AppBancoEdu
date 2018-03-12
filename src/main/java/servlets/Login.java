/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import config.Configuration;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import servicios.UsersServicios;
import utils.Constantes;
import utils.Mensajes;
import utils.PasswordHash;

/**
 *
 * @author Eduardo DAW
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType(Constantes.CTYPE);        
        User u=new User();
        UsersServicios us = new UsersServicios();
        String op = request.getParameter(Constantes.OP);
        if(op!=null){
  
            switch (op) {
                case Constantes.REGISTRAR:
                    if(us.getUsersByName(request.getParameter(Constantes.NOMBRE))==0){
                        if(us.validarCorreo(request.getParameter(Constantes.CORREO))){
                            u.setId(request.getParameter(Constantes.DNI));
                            u.setNombre(request.getParameter(Constantes.NOMBRE));
                            u.setEmail(request.getParameter(Constantes.CORREO));                           
                            String password = request.getParameter(Constantes.PASSWORD);                            
                            String hash=us.hacerHash(password);
                            u.setPassword(hash);                           
                            String hash2 = us.hacerCodigo();                          
                            u.setCodigo_activacion(hash2);
                            us.sendCorreo(Constantes.DESTINO, Constantes.MENSAJE+hash2,Constantes.TITULO );
                            request.getSession().setAttribute(Constantes.HASH, hash2);
                            u.setActivo(0);
                            LocalDateTime ahora = LocalDateTime.now();
                            u.setFecha_activacion(ahora); 
                            us.addUser(u);
                            request.setAttribute(Mensajes.PASALO,Mensajes.CREADO);
                        } else{
                            request.setAttribute(Mensajes.PASALO,Mensajes.MALCORREO);
                        }
                    } else {
                        request.setAttribute(Mensajes.PASALO,Mensajes.ENUSO);
                    }
                    request.getRequestDispatcher(Constantes.PINTAR_LOGIN).forward(request, response);
                    break;
                    
                case Constantes.ACTIVAR:
                    String cod_act = request.getParameter(Constantes.CODIGO_ACTIVACION);
                    LocalDateTime ahora = LocalDateTime.now().minusMinutes(Configuration.getInstance().getMaxMinutes());
                    LocalDateTime antes = us.getUsersDate(cod_act);
                    if(ahora.isBefore(antes)){
                        int ck=us.activarUser(cod_act);
                        if(ck==1){
                            request.setAttribute(Mensajes.PASALO,Mensajes.BIEN);
                        }else{
                            request.setAttribute(Mensajes.PASALO,Mensajes.MAL);
                        }
                    }else{
                        request.setAttribute(Mensajes.PASALO,Mensajes.AGOTADO);
                    }
                    request.getRequestDispatcher(Constantes.PINTAR_LOGIN).forward(request, response);
                    break;
                case Constantes.ENTRAR:                    
                    try{
                        String encrip = us.getUsersPass(request.getParameter(Constantes.DNILOG));
                        String password = request.getParameter(Constantes.PASSWORDLOG);
                        int a=us.getUsersActivo(request.getParameter(Constantes.DNILOG));
                        if(a==1){
                            if(PasswordHash.getInstance().validatePassword(password, encrip)){
                                request.getSession().setAttribute(Constantes.LOGIN, Constantes.OK);
                                request.getSession().setAttribute(Constantes.USUARIO,request.getParameter(Constantes.DNILOG));
                                request.getSession().setAttribute(Constantes.SESION,request.getSession().getAttribute(Constantes.USUARIO));
                                request.getRequestDispatcher(Constantes.PINTAR_ELEGIR).forward(request, response);
                                break;
                            } else {
                                request.getSession().setAttribute(Constantes.LOGIN, null);
                                request.setAttribute(Mensajes.PASALO,Mensajes.MAL);
                                request.getRequestDispatcher(Constantes.PINTAR_LOGIN).forward(request, response);
                            }
                        }else{
                            request.setAttribute(Mensajes.PASALO,Mensajes.NOACTIVO);
                            request.getRequestDispatcher(Constantes.PINTAR_LOGIN).forward(request, response);   
                        }
                        
                    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
            }
        } else {
            request.getSession().setAttribute(Constantes.LOGIN, null);
            request.getRequestDispatcher(Constantes.PINTAR_LOGIN).forward(request, response);                    
        }
        
    }

   // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    

}
