<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="utils.Constantes" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="css/estilos.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <title>Elige una opcion</title>
    </head>
    <body>  
        <div class="container"><br>
            <h1>Bienvenido: <c:out value="${sesion}"></c:out></h1><br><hr>
            <div class="row">
                <div class="col">
                    <h3 style="color:brown">Que operacion desea realizar</h3><br>     
                    <h2><a href="secure/ingresoreintegro">Ingreso o Reintegro</a></h2>
                    <h2><a href="secure/movimientos">Consulta de movimientos</a></h2>
                    <h2><a href="secure/abrircuenta">Apertura de cuenta</a></h2>
                    <h2><a class="nova" href="secure/cerrarcuenta">Cierre de cuenta</a></h2>
                    <br><hr>
                    <h2><a href="login">Logout</a></h2>
                </div>
            </div>
        </div>               
    </body>
</html>
