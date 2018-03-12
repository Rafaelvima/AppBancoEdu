/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.time.LocalDateTime;
import java.util.List;
import model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.Consultas;

/**
 *
 * @author Eduardo DAW
 */
public class UsersDAO {

    //COMPROBAR NOMBRE//
    public int getUsersByNameJDBCTemplate(String nombre){
        int filas=0;
        JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());
        List<User> users = jtm.query(Consultas.SEL_NOM_Q,new BeanPropertyRowMapper(User.class),nombre);
        filas=users.size();
        return filas;
    }
    //BUSCAR PASS//
    public String getUsersPassJDBCTemplate(String dni){
        JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());
        List<User> users = jtm.query(Consultas.SEL_NOM_Q,new BeanPropertyRowMapper(User.class),dni);
        String pass=users.get(0).getPassword();
        return pass;
    }
    //BUSCAR FECHA//
    public LocalDateTime getUsersDateJDBCTemplate(String codigo_activacion){
        JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());
        List<User> users = jtm.query(Consultas.SEL_CA_Q,new BeanPropertyRowMapper(User.class),codigo_activacion);
        LocalDateTime fecha=users.get(0).getFecha_activacion();
        return fecha;
    }
    //MIRAR ACTIVADO//
    public int getUsersActivadoJDBCTemplate(String dni){
        JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());
        List<User> users = jtm.query(Consultas.SEL_NOM_Q,new BeanPropertyRowMapper(User.class),dni);
        int act=users.get(0).getActivo();
        return act;
    }
    //AÑADIR//
    public User addJDBCTemplate(User u) {
        JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());
        jtm.update(Consultas.REG_Q,u.getId(),u.getNombre(),u.getPassword(),u.getActivo(),u.getCodigo_activacion(),u.getFecha_activacion(),u.getEmail());
        return u;
    }
    //ACTIVAR//
    public int activarUser(String codigo_activacion){
        JdbcTemplate jtm = new JdbcTemplate(DBConnection.getInstance().getDataSource());
        int filas = jtm.update(Consultas.UPD_Q,codigo_activacion);
        if(filas==1){
            return 1;
        }else{
            return 0;
        }
    }
    
}
