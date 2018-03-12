/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.CuentaDAO;
import java.time.LocalDateTime;
import utils.Constantes;

/**
 *
 * @author Eduardo DAW
 */
public class ComprobacionesServicios {
    
    public boolean checkCuenta(String cuenta){
        boolean ojo=false;
        int suma=0;
        for(int i=0;i<cuenta.length();i++){
            suma = suma + cuenta.charAt(i);
        }
        if(!((suma%9)==(cuenta.charAt(cuenta.length()-1)))){
            ojo=true;
        }
        if(!cuenta.matches(Constantes.REGEXPCUENTA)){
            ojo=true;
        }
        return ojo;
    }
    public boolean validar(String cuenta){
        CuentaDAO dao=new CuentaDAO();
        return dao.validar(cuenta);
    }
    
    public boolean validarDni(String dni){
        return dni.matches(Constantes.FORM_DNI);
    }
    
    public String parseTiempo(LocalDateTime fechaActual){
        String tiempo = "";       
        if(fechaActual.getHour()<10){
            tiempo = "0"+fechaActual.getHour();
        }else{
            tiempo = Integer.toString(fechaActual.getHour());
        }       
        if(fechaActual.getMinute()<10){
            tiempo = tiempo + "0" + fechaActual.getMinute();
        }else{
            tiempo = tiempo + Integer.toString(fechaActual.getMinute());
        }       
        if(fechaActual.getSecond()<10){
            tiempo = tiempo + "0" + fechaActual.getSecond();
        }else{
            tiempo = tiempo + Integer.toString(fechaActual.getSecond());
        }
        return tiempo;
    }
}
