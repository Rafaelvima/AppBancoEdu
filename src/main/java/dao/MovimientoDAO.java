/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Movimiento;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.Consultas;

/**
 *
 * @author Eduardo DAW
 */


public class MovimientoDAO {
    public List movimientosFechas(String cuenta,LocalDate fecha1,LocalDate fecha2){
        List<Movimiento> lista = null;
        try{
            JdbcTemplate temp= new JdbcTemplate(DBConnection.getInstance().getDataSource());
            lista=temp.query(Consultas.MOV_FECHAS,new BeanPropertyRowMapper(Movimiento.class),cuenta,fecha1,fecha2);
            if(lista.size()<1){
                lista=null;
            }
        } catch(DataAccessException e) {
            Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return lista;
    }
    
     public int crearMovimientoDAO(Movimiento mo){      
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas = 0;
        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(Consultas.MOV_CREAR);                                       
            stmt.setString(1, mo.getMo_ncu());
            stmt.setDate(2, new java.sql.Date(mo.getMo_fec().getTime()));
            stmt.setString(3, mo.getMo_hor());
            stmt.setString(4, mo.getMo_des());
            stmt.setInt(5,mo.getMo_imp());           
            filas = stmt.executeUpdate();           
        } catch (Exception ex) {
            Logger.getLogger(MovimientoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;  
    }
}
