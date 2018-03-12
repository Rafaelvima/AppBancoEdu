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
import utils.Mensajes;

/**
 *
 * @author Eduardo DAW
 */
@WebServlet(name = "IngresoReintegro", urlPatterns = {"/secure/ingresoreintegro"})
public class IngresoReintegro extends HttpServlet {

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
        MovimientoServicios ms=new MovimientoServicios();
        CuentaServicios cus=new CuentaServicios();
        ClienteServicios cls=new ClienteServicios();
        ComprobacionesServicios cs=new ComprobacionesServicios();
        String numC = request.getParameter(Constantes.NUM_CU);
        String op = request.getParameter(Constantes.OP);
        if(numC != null){
            Movimiento mo = new Movimiento();
            Cuenta cu = new Cuenta();
            Cliente cl = new Cliente();
            boolean ojo = true;
            int imp = Integer.parseInt(request.getParameter(Constantes.IMPOR));
            String desc = request.getParameter(Constantes.DESCR);
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
                    if (op != null){                          
                        if(Constantes.RETIRAR.equals(op)){//si es retirar
                            if(imp>cu.getCu_sal()){//comprobacion saldo disponible
                                posible=false;
                            }
                            imp=imp*(-1);//importe negativo si es retirar
                        }
                        if(posible){
                            //Cuenta
                            cu.setCu_sal(imp + cu.getCu_sal());
                            filas = cus.updateCuenta(cu);//update saldo cuenta
                            if(filas == 0){                                  
                                ojo = false;
                                response.setStatus(500);
                                response.getWriter().println(Mensajes.ERROR_CUE);
                            }             
                            //Cliente 1
                            cl.setCl_dni(dni);
                            cl = cls.getCliente(cl);
                            cl.setCl_sal(cl.getCl_sal() + imp);
                            filas = cls.updateCliente(cl);//update saldo cliente1
                            if(filas <= 0){                                    
                                ojo = false;
                                response.setStatus(500);
                                response.getWriter().println(Mensajes.ERROR_PRI_TIT);
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
                                response.getWriter().println(Mensajes.ERROR_SEG_TIT);
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
                                response.getWriter().println(Mensajes.ERROR_MOV);
                            }                               
                            if(ojo == true){//Si no salta ningun error llega aqui
                                response.getWriter().println(Mensajes.BIEN);
                            }          
                        } else {
                            response.setStatus(500);
                            response.getWriter().println(Mensajes.NO_CASH);
                        }                            
                    }
                }else{
                response.setStatus(500);
                response.getWriter().println(Mensajes.ING_ERR); 
                }
            }else{
                response.setStatus(500);
                response.getWriter().println(Mensajes.NO_EXI);
            }
        }else{
            response.setStatus(500);
            response.getWriter().println(Mensajes.MAL_FORM);
        }
        if(numC == null || op==null){
            request.getRequestDispatcher(Constantes.PINTAR_ING_REI).forward(request, response);
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
