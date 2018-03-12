/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.ClienteDAO;
import java.util.List;
import model.Cliente;

/**
 *
 * @author Eduardo DAW
 */
public class ClienteServicios {
    public List<Cliente> getAllClientes() {
        ClienteDAO dao = new ClienteDAO();
        return dao.getAllClientes();
    }
    
    public Cliente getCliente(Cliente c){
        ClienteDAO dao = new ClienteDAO();       
        return dao.getClienteDAO(c);
    }

    public int updateCliente(Cliente cl){
        ClienteDAO dao = new ClienteDAO();
        return dao.updateClienteDAO(cl);
    }
    
    public int updateClienteSalNum(int importe,String dni){
        ClienteDAO dao = new ClienteDAO();
        return dao.updateClienteSalNum(importe, dni);
    }
    
    public int addCliente(Cliente c){
        ClienteDAO dao = new ClienteDAO();
        return dao.addCliente(c);
    }
}
