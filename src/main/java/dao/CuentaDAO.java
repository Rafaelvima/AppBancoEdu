/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cuenta;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.Consultas;

/**
 *
 * @author Eduardo DAW
 */

public class CuentaDAO {
    
    public boolean validar(String cuenta){
        boolean ojo=false;
        try{
            JdbcTemplate temp= new JdbcTemplate(DBConnection.getInstance().getDataSource());
            String valido=temp.queryForObject(Consultas.CUE_VALIDAR, String.class,cuenta);
            if(valido!=null){
                ojo=true;
            }
        } catch(DataAccessException e) {
            Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return ojo;
    }     
    
    public Cuenta getCuentaDAO(Cuenta cu) {
        Cuenta cuenta = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Cuenta> h = new BeanHandler<>(Cuenta.class);
            cuenta = qr.query(con, Consultas.CUE_GET, h,cu.getCu_ncu());

        } catch (Exception ex){
            Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return cuenta;
    }
    
     public int updateCuentaDAO(Cuenta cu){
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas = 0;
        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(Consultas.CUE_UPDATE);
 
            stmt.setInt(1, cu.getCu_sal());
            stmt.setString(2, cu.getCu_ncu());
            
            filas = stmt.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }
     
     public Cuenta getSaldoDAO(Cuenta cu){
        Cuenta cuenta = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Cuenta> h = new BeanHandler<>(Cuenta.class);
            cuenta = qr.query(con, Consultas.CUE_GETSALDO, h,cu.getCu_sal());

        } catch (Exception ex){
            Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return cuenta;
    }
     
    public int addCuenta(Cuenta cu){
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas = 0;
        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(Consultas.ADD_CUENTA);                                       
            stmt.setString(1, cu.getCu_ncu());
            stmt.setString(2, cu.getCu_dn1());
            stmt.setString(3, cu.getCu_dn2());
            stmt.setInt(4, cu.getCu_sal());           
            filas = stmt.executeUpdate();           
        } catch (Exception ex) {
            Logger.getLogger(MovimientoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;  
    }
}
