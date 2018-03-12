/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "AbrirCuenta", urlPatterns ={"/secure/abrircuenta"})
public class AbrirCuenta extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType(Constantes.CTYPE); 
        MovimientoServicios ms=new MovimientoServicios();
        CuentaServicios cus=new CuentaServicios();
        ClienteServicios cls=new ClienteServicios();
        ComprobacionesServicios cs=new ComprobacionesServicios();
        
        String numC = request.getParameter(Constantes.NUM_CU);
        
        if(numC!=null){
            int importe= parseInt(request.getParameter(Constantes.IMPOR));
            if(importe>0){
                if(cs.checkCuenta(numC)){
                    Cliente aux1=new Cliente();
                    Cliente aux2=new Cliente();
                    String dni1= request.getParameter(Constantes.DNI_1);
                    String dni2= request.getParameter(Constantes.DNI_2);
                    aux1.setCl_dni(dni1);
                    if(cls.getCliente(aux1)!=null){
                        cls.updateClienteSalNum(importe, dni1);//Añade +1 cuenta e importe
                    } else {
                        Cliente c1=new Cliente();
                        String nombre1= request.getParameter(Constantes.NOMBRE_1);              
                        String direccion1= request.getParameter(Constantes.DIR_1);               
                        String telefono1= request.getParameter(Constantes.TEL_1);               
                        String email1= request.getParameter(Constantes.EMAIL_1);              
                        String fecha1= request.getParameter(Constantes.FEC_1);
                        c1.setCl_dni(dni1);
                        c1.setCl_nom(nombre1);
                        c1.setCl_dir(direccion1);
                        c1.setCl_tel(parseInt(telefono1));
                        c1.setCl_ema(email1);
                        SimpleDateFormat format= new SimpleDateFormat(Constantes.FORM_DATE);
                        Date fechaBien=format.parse(fecha1);//formato Date
                        c1.setCl_fna(fechaBien);
                        c1.setCl_fcl(new Date());
                        c1.setCl_sal(importe);
                        cls.addCliente(c1);//Añadir cliente 1
                    }
                    aux2.setCl_dni(dni2);
                    if(cls.getCliente(aux2)!=null){
                        cls.updateClienteSalNum(importe, dni2);//Añade +1 cuenta e importe
                    } else {
                        Cliente c2=new Cliente();
                        String nombre2= request.getParameter(Constantes.NOMBRE_2);
                        String direccion2= request.getParameter(Constantes.DIR_2);
                        String telefono2= request.getParameter(Constantes.TEL_2);
                        String email2= request.getParameter(Constantes.EMAIL_2);
                        String fecha2= request.getParameter(Constantes.FEC_2);
                        c2.setCl_dni(dni2);
                        c2.setCl_nom(nombre2);
                        c2.setCl_dir(direccion2);
                        c2.setCl_tel(parseInt(telefono2));
                        c2.setCl_ema(email2);
                        SimpleDateFormat format= new SimpleDateFormat(Constantes.FORM_DATE);
                        Date fechaBien2=format.parse(fecha2);//formato Date
                        c2.setCl_fna(fechaBien2);
                        c2.setCl_fcl(new Date());
                        c2.setCl_sal(importe);
                        cls.addCliente(c2);//Añadir cliente 1
                    }             
                    if(dni1!=null && dni2!=null){
                        Cuenta cuenta=new Cuenta();
                        cuenta.setCu_ncu(numC);
                        cuenta.setCu_dn1(dni1);
                        cuenta.setCu_dn2(dni2);
                        cuenta.setCu_sal(importe);
                        cus.addCuenta(cuenta);//añadir cuenta
                    }                    
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
                    Movimiento mo=new Movimiento();
                    //Movimiento
                    mo.setMo_ncu(numC);
                    mo.setMo_imp(importe);
                    mo.setMo_des(Constantes.MOV_INICIO);
                    mo.setMo_fec(date);
                    mo.setMo_hor(hora);                                
                    ms.crearMovimiento(mo);//insert movimiento
                } else {
                    response.setStatus(409);
                    response.getWriter().println(Mensajes.NO_EXI);
                }              
            } else {
                response.setStatus(500);
                response.getWriter().println(Mensajes.IMP_MAL);
            }        
        } else {
            response.setStatus(500);
            request.getRequestDispatcher(Constantes.PINTAR_ABRIR_1).forward(request, response);
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
            throws ServletException, IOException
    {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AbrirCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            throws ServletException, IOException
    {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AbrirCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
