/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Cuenta;
import model.Movimiento;
import servicios.ClienteServicios;
import servicios.ComprobacionesServicios;
import servicios.CuentaServicios;
import servicios.MovimientoServicios;
import utils.Constantes;

/**
 *
 * @author Eduardo DAW
 */
@WebServlet(name = "OperacionesApiRest", urlPatterns = {"/api/operacionesapirest"})
public class OperacionesApiRest extends HttpServlet {

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

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MovimientoServicios ms=new MovimientoServicios();
        CuentaServicios cus=new CuentaServicios();
        ClienteServicios cls=new ClienteServicios();
        ComprobacionesServicios cs=new ComprobacionesServicios();
        
        Movimiento movi = (Movimiento) request.getAttribute(Constantes.MOVI);
        String numC = movi.getMo_ncu();       
        if(numC != null){
            Movimiento mo = new Movimiento();
            Cuenta cu = new Cuenta();
            Cliente cl = new Cliente();
            boolean ojo = true;
            int imp=movi.getMo_imp();
            String desc=movi.getMo_des();
            //Hora y formato de hora
            Date date = new Date();
            DateFormat horas = new SimpleDateFormat(Constantes.FORMAT_H);
            String hora = horas.format(date);         
            String horaFin = "";                                
            for(int i = 0; i < hora.length(); i++){
                if(hora.charAt(i) != ':'){
                    horaFin += hora.charAt(i);
                }
            }          
            hora = horaFin;           
            cu.setCu_ncu(numC);//obj cuenta con el numero de cuenta           
            if(cs.validar(numC)){              
                cu = cus.getCuenta(cu);               
                if(cu != null){//chekea en la base de datos que la cuenta existe
                    String dni= cu.getCu_dn1();
                    String dni2=cu.getCu_dn2();
                    int filas = 0;
                    boolean posible=true;
                    if(imp<0){
                        if((imp*(-1))>cu.getCu_sal()){//comprobacion saldo disponible
                            posible=false;
                        }                                                    
                    }                   
                    if(posible){
                        //Cuenta
                        cu.setCu_sal(imp + cu.getCu_sal());
                        filas = cus.updateCuenta(cu);//update saldo cuenta
                        if(filas == 0){                                  
                            ojo = false;
                            response.setStatus(500);
                        }             
                        //Cliente 1
                        cl.setCl_dni(dni);
                        cl = cls.getCliente(cl);
                        cl.setCl_sal(cl.getCl_sal() + imp);
                        filas = cls.updateCliente(cl);//update saldo cliente1
                        if(filas <= 0){                                    
                            ojo = false;
                            response.setStatus(500);
                        } 
                        //Cliente 2
                        if(dni2!=null && !dni.equals(dni2)){
                            cl.setCl_dni(dni2);
                            cl = cls.getCliente(cl);
                            cl.setCl_sal(cl.getCl_sal() + imp);
                            filas = cls.updateCliente(cl);//update saldo cliente2
                        }
                        if(filas <= 0){                                    
                            ojo = false;
                            response.setStatus(500);
                        }  
                        //Movimiento
                        mo.setMo_ncu(numC);
                        mo.setMo_imp(imp);
                        mo.setMo_des(desc);
                        mo.setMo_fec(date);
                        mo.setMo_hor(hora);                                
                        filas = ms.crearMovimiento(mo);//insert movimiento
                        if(filas == 0){
                            ojo = false;
                            response.setStatus(500);
                        }                               
                        if(ojo == true){//Si no salta ningun error llega aqui
                            request.setAttribute(Constantes.JSON, movi);
                        }          
                    } else {
                        response.setStatus(406);
                    }                                                
                }else{
                response.setStatus(400); 
                }
            }else{
                response.setStatus(409);
            }
        }else{
            response.setStatus(500);
        } //To change body of generated methods, choose Tools | Templates.
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
