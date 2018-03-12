/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Eduardo DAW
 */
public class Consultas
{
    //USUARIOS
    public static final String REG_Q="INSERT INTO USERS (ID_USER,NOMBRE,PASSWORD,ACTIVO,CODIGO_ACTIVACION,FECHA_ACTIVACION,EMAIL) VALUES(?,?,?,?,?,?,?)";
    public static final String UPD_Q="UPDATE USERS SET ACTIVO = 1 WHERE CODIGO_ACTIVACION = ?";
    public static final String SEL_NOM_Q="SELECT * FROM USERS WHERE ID_USER=?";
    public static final String SEL_CA_Q="SELECT * FROM USERS WHERE CODIGO_ACTIVACION=?";
    
    //MOVIMIENTOS
    public static final String MOV_FECHAS="SELECT * FROM movimientos WHERE mo_ncu=? AND mo_fec between ? AND ? order by mo_fec desc";
    public static final String MOV_CREAR="INSERT INTO movimientos (mo_ncu,mo_fec,mo_hor,mo_des,mo_imp) VALUES(?,?,?,?,?)"; 
    
    //CUENTAS
    public static final String CUE_VALIDAR="SELECT cu_ncu FROM cuentas WHERE cu_ncu=?";
    public static final String CUE_GET="SELECT * FROM cuentas WHERE cu_ncu = ?";
    public static final String CUE_UPDATE="UPDATE cuentas SET cu_sal = ? WHERE cu_ncu = ?";
    public static final String CUE_GETSALDO="SELECT * FROM cuentas WHERE cu_sal = ?";
    public static final String ADD_CUENTA="INSERT INTO cuentas (cu_ncu,cu_dn1,cu_dn2,cu_sal) VALUES(?,?,?,?)";
   
    //CLIENTES
    public static final String CLI_ALL="SELECT * FROM clientes";
    public static final String CLI_GET_ST="SELECT * FROM clientes WHERE cl_dni = ?";
    public static final String CLI_UPDATE="UPDATE clientes SET cl_sal = ? WHERE cl_dni = ?";
    public static final String CLI_NCU_SAL="UPDATE clientes SET cl_ncu = cl_ncu + 1, cl_sal = cl_sal + ? WHERE cl_dni = ?";
    public static final String CLI_ADD="INSERT INTO clientes (cl_dni, cl_nom, cl_dir, cl_tel, cl_ema, cl_fna, cl_fcl, cl_ncu, cl_sal) VALUES (?,?,?,?,?,?,?,1,?)";
    
}
