/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servicios.ComprobacionesServicios;
import servicios.MovimientoServicios;
import utils.Constantes;
import utils.Mensajes;

/**
 *
 * @author Eduardo DAW
 */
@WebServlet(name = "movimientos", urlPatterns = {"/secure/movimientos"})
public class Movimientos extends HttpServlet {

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
            //Servicios
            MovimientoServicios ms=new MovimientoServicios();
            ComprobacionesServicios cs=new ComprobacionesServicios();
            //GET PARAMETER
            String cuenta = request.getParameter(Constantes.CUENTAMOV);
            String fecha1=request.getParameter(Constantes.FECHA1MOV);
            String fecha2=request.getParameter(Constantes.FECHA2MOV);
            //CHECKS       
            if(cuenta!=null && fecha1!=null && fecha2!=null){    
                if(cs.checkCuenta(cuenta) && ms.mirarFechas(fecha1, fecha2)){
                    if(cs.validar(cuenta)){
                        String lista=ms.movimientosFechas(cuenta, fecha1, fecha2);
                        if(lista==null){
                            response.setStatus(500);
                            request.setAttribute(Mensajes.PASALO,Mensajes.NO_MOV);
                        } else {
                            response.getWriter().write(lista);
                        }
                    } else {
                        response.setStatus(409);
                        request.setAttribute(Mensajes.PASALO,Mensajes.NO_EXI);
                    }
                } else {
                    response.setStatus(500);
                    request.setAttribute(Mensajes.PASALO,Mensajes.FEC_MAL);
                }
            } else {
                response.setStatus(500);
                request.getRequestDispatcher(Constantes.PINTAR_MOVI).forward(request, response);
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
