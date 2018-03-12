/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.Consultas;

/**
 *
 * @author Eduardo DAW
 */
public class ClienteDAO {
    
    public List<Cliente> getAllClientes() {
        List<Cliente> lista = null;
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Cliente>> h = new BeanListHandler<>(Cliente.class);
            lista = qr.query(con, Consultas.CLI_ALL, h);

        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return lista;
    }
    
    public Cliente getClienteDAO(Cliente cl) {
        Cliente cliente = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Cliente> h = new BeanHandler<>(Cliente.class);
            cliente = qr.query(con, Consultas.CLI_GET_ST, h,cl.getCl_dni());

        } catch (Exception ex){
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return cliente;
    }
    
    public int updateClienteDAO(Cliente cl){
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas = 0;
        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(Consultas.CLI_UPDATE);
 
            stmt.setInt(1, cl.getCl_sal());
            stmt.setString(2, cl.getCl_dni());
            
            filas = stmt.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }
    
    public int updateClienteSalNum(int importe, String dni){
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas = 0;
        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(Consultas.CLI_NCU_SAL);
 
            stmt.setInt(1, importe);
            stmt.setString(2, dni);
            
            filas = stmt.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }
    
    public int addCliente(Cliente c){
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas = 0;
        try {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(Consultas.CLI_ADD);
 
            stmt.setString(1, c.getCl_dni());
            stmt.setString(2, c.getCl_nom());
            stmt.setString(3, c.getCl_dir());
            stmt.setInt(4, c.getCl_tel());
            stmt.setString(5, c.getCl_ema());
            stmt.setDate(6, new java.sql.Date(c.getCl_fna().getTime()));
            stmt.setDate(7, new java.sql.Date(c.getCl_fcl().getTime()));
            stmt.setInt(8, c.getCl_sal());
            
            filas = stmt.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }
}
