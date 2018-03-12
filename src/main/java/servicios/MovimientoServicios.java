/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.gson.Gson;
import dao.MovimientoDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Movimiento;

/**
 *
 * @author Eduardo DAW
 */
public class MovimientoServicios {

    public boolean mirarFechas(String fecha1,String fecha2){
        boolean ojo=false;
        DateTimeFormatter format= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate primera=LocalDate.parse(fecha1, format);
        LocalDate segunda=LocalDate.parse(fecha2,format);
        if(primera.isEqual(primera)){
            ojo=true;
        } else {
            ojo= !primera.isAfter(segunda);
        }
        return ojo;
    }
    
    public String movimientosFechas(String cuenta,String fecha1,String fecha2){
        MovimientoDAO dao=new MovimientoDAO();
        Gson gson=new Gson();
        String resultado;
        DateTimeFormatter format= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate primera=LocalDate.parse(fecha1, format);
        LocalDate segunda=LocalDate.parse(fecha2,format);
        List lista= dao.movimientosFechas(cuenta,primera,segunda);
        if(lista!=null){
            resultado=gson.toJson(lista);
        } else {
            resultado=null;
        }
        return resultado;
    }
    
    public int crearMovimiento(Movimiento mo) {
        MovimientoDAO dao = new MovimientoDAO();
        return dao.crearMovimientoDAO(mo);
    }
}
