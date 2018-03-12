/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Eduardo
 */
public class Constantes {
    
    //Opciones
    public static final String OP="op";
    public static final String INSERT="insert";
    public static final String DELETE="delete";
    public static final String UPDATE="update";
    public static final String CERRAR="cerrar";
    public static final String REGISTRAR="registrar";
    public static final String ACTIVAR="validar";
    public static final String ENTRAR="entrar";
    public static final String COMPROBAR="comprobar";
    public static final String RETIRAR="retirar";
    
    //Pintar
    public static final String PINTAR_LOGIN="/login.jsp";
    public static final String PINTAR_ELEGIR="/inicio.jsp";
    public static final String PINTAR_MOVI="/movimientos.jsp";
    public static final String PINTAR_ABRIR_1="/abrircuenta.jsp";
    public static final String PINTAR_CERRAR="/cerrarcuenta.jsp";
    public static final String PINTAR_ING_REI="/ingresoreintegro.jsp";
    
    ////MOVIMIENTOS ////
    public static final String CUENTAMOV="cuentamov";
    public static final String FECHA1MOV="fecha1mov";
    public static final String FECHA2MOV="fecha2mov";
    public static final String REGEXPCUENTA="^[0-9]{10}$";
    public static final String MOVI="movimiento";
    
    //INGRESOS REINTEGROS
    public static final String NUM_CU="numcuenta";
    public static final String IMPOR="importe";
    public static final String DESCR="descripcion";
    
    //ABRIR CUENTA
    public static final String DNI_1="dni1";
    public static final String DNI_2="dni2";
    public static final String NOMBRE_1="nombre1";
    public static final String NOMBRE_2="nombre2";
    public static final String DIR_1="direccion1";
    public static final String DIR_2="direccion2";
    public static final String TEL_1="telefono1";
    public static final String TEL_2="telefono2";
    public static final String EMAIL_1="email1";
    public static final String EMAIL_2="email2";
    public static final String FEC_1="fecha1";
    public static final String FEC_2="fecha2";
    public static final String MOV_INICIO="Ingreso inicial apertura";
    
    //FORMATOS
    public static final String FORMAT_H="HH:mm:ss";
    public static final String CTYPE="text/html;charset=UTF-8";
    public static final String FORM_DNI="^[0-9]{8}[A-Z]$";
    public static final String FORM_DATE="yyyy-MM-dd";
    
    //LOGIN 
    public static final String ID="id";
    public static final String DNI="dni";
    public static final String DNILOG="dnilog";
    public static final String PASSWORD="password";
    public static final String PASSWORDLOG="passwordlog";
    public static final String NOMBRE="nombre";
    public static final String CORREO="correo";
    public static final String CODIGO_ACTIVACION="codigo_activacion";
    
    //sesion
    public static final String SESION="sesion";
    public static final String USUARIO="usuario";
    public static final String HASH="HASH";
    
    //filtro
    public static final String LOGIN="LOGIN";
    public static final String OK="OK";    
    public static final String JSON="json";
    public static final String APIKEY="apiKey";
    
    //CORREO
    public static final String DESTINO="eduardo.gn.daw@gmail.com"; //Mando aqui los correos para poder verlos :P
    public static final String MENSAJE="Pincha aqui para activar --> http://localhost:8282/AppBancoEdu/login?op=validar&codigo_activacion=";
    public static final String TITULO="Activacion";
    public static final String REQUISITOS="^([_A-Za-z0-9-]+)@([A-Za-z0-9-]+).([A-Za-z0-9]+)$";
    
}   