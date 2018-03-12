<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../css/estilos.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <title>Movimientos</title>
        <script src="../js/movimientos.js"></script>
    </head>
    <body>
        <div class="container">
            <h1>LISTADO DE MOVIMIENTOS</h1><br><c:out value="${pasalo}"></c:out><hr>
            <h4><a href="../inicio.jsp">Volver</a><span> - </span><a href="../login">Logout</a></h4><br>
            <div class="row">
                <div class="col">
                    <table class="table">
                        <form method="post" id="formmov">
                            <tr>
                                <th>Datos</th>
                                <th></th>
                            </tr>
                            <tr>
                                <td>Numero de Cuenta:</td>
                                <td><input type="text" id="cuentamov" name="cuentamov" size="12"/></td>
                            </tr>
                            <tr>
                                <td>Fecha inicial:</td>
                                <td><input type="date" id="fecha1mov" name="fecha1mov"/></td>
                            </tr>
                            <tr>
                                <td>Fecha final:</td>
                                <td><input type="date" id="fecha2mov" name="fecha2mov"/></td>
                            </tr>
                            <tr>
                                <td><input type="button" class="btn btn-success" id="comprobar" value="ver" onclick="mostrarDatos();"></td>
                            </tr>               
                        </form>
                    </table><br>
                    <table class="table" id="tabla"></table>  
                    <h1 id="aviso"></h1>
                </div>
            </div>
        </div>
    </body>
</html>