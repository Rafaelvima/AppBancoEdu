<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../css/estilos.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="../js/abrircuenta.js"></script>
        <title>Abrir Cuenta</title>
    </head>
    <body>
        <div class="container">
            <h1>ABRIR CUENTA</h1><br><c:out value="${pasalo}"></c:out><hr> 
            <h4><a href="../inicio.jsp">Volver</a><span> - </span><a href="../login">Logout</a></h4><hr><br>
            <div class="row">
                <form id="formadd" methos="get" class="col-12"><br><br>
                    <table>
                        <tr>
                            <td colspan="2"><label><b>DATOS CUENTA</b></label></td>
                            <td colspan="2"><label><b>PRIMER TITULAR</b></label></td>
                            <td colspan="2"><label><b>SEGUNDO TITULAR</b></label></td>
                        </tr>
                        <tr>
                            <td><label><b>nº Cuenta:</b></label></td>
                            <td><input type="text" id="numcuenta" name="numcuenta"/></td>
                            <td><label><b>DNI-1:</b></label></td>
                            <td><input type="text" id="dni1" name="dni1"/></td>
                            <td><label><b>DNI-2:</b></label></td>
                            <td><input type="text" id="dni2" name="dni2"/></td>
                        </tr>
                        <tr>
                            <td><label><b>Importe inicial:</b></label></td>
                            <td><input type="text" id="importe" name="importe"/></td>
                            <td><label><b>Nombre:</b></label></td>
                            <td><input type="text" id="nombre1" name="nombre1"/></td>
                            <td><label><b>Nombre:</b></label></td>
                            <td><input type="text" id="nombre2" name="nombre2"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="button" class="btn btn-success" value="Ejecutar orden" onclick="funcionAdd();"/></td>
                            <td><label><b>Direccion:</b></label></td>
                            <td><input type="text" id="direccion1" name="direccion1"/></td>
                            <td><label><b>Direccion:</b></label></td>
                            <td><input type="text" id="direccion2" name="direccion2"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td><label><b>Telefono:</b></label></td>
                            <td><input type="text" id="telefono1" name="telefono1"/></td>
                            <td><label><b>Telefono:</b></label></td>
                            <td><input type="text" id="telefono2" name="telefono2"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td><label><b>Email:</b></label></td>
                            <td><input type="text" id="email1" name="email1"/></td>
                            <td><label><b>Email:</b></label></td>
                            <td><input type="text" id="email2" name="email2"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td><label><b>Nacimiento:</b></label></td>
                            <td><input type="date" id="fecha1" name="fecha1"/></td>
                            <td><label><b>Nacimiento:</b></label></td>
                            <td><input type="date" id="fecha2" name="fecha2"/></td>
                        </tr> 
                    </table>   
                </form>
                <h1 id="aviso"></h1>
                <table id="tabla"></table>             
            </div>
         </div>
    </body>
</html>
