/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.CuentaDAO;
import model.Cuenta;

/**
 *
 * @author Eduardo DAW
 */
public class CuentaServicios {
    
    public Cuenta getCuenta(Cuenta cu) {
        CuentaDAO dao = new CuentaDAO();
        return dao.getCuentaDAO(cu);
    } 

    public int updateCuenta(Cuenta cu) {
        CuentaDAO dao = new CuentaDAO();
        return dao.updateCuentaDAO(cu);
    }
    
    public Cuenta getSaldo(Cuenta cu){
        CuentaDAO dao = new CuentaDAO();
        return dao.getSaldoDAO(cu);
    }
    
    public int addCuenta(Cuenta cu){
        CuentaDAO dao=new CuentaDAO();
        return dao.addCuenta(cu);
    }
}
