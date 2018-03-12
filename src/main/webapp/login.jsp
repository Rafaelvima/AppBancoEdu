<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="utils.Constantes" %>
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
        <title>Login</title>
    </head>
    <body>
        <div class="container">    <br>      
            <h1>ACCESO</h1><br><c:out value="${pasalo}"></c:out><br>
            <form id="formPrincipal" name="formPrincipal" action="login" method="post">
                <div class="row">
                    <div class="col">
                        <table class="table table-dark">
                            <tr>
                                <td>
                                    <table class="table">
                                        <tr>
                                            <th colspan="2">REGISTRAR</th>
                                        </tr>
                                        <tr>
                                            <td>DNI:</td>
                                            <td><input type="text" id="dni" name="dni" size="12"/></td>
                                        </tr>
                                        <tr>
                                            <td>Nombre:</td>
                                            <td><input type="text" id="nombre" name="nombre" size="12"/></td>
                                        </tr>
                                        <tr>
                                            <td>Password:</td>
                                            <td><input type="password" id="password" name="password"/></td>
                                        </tr>
                                        <tr>
                                            <td>e-mail:</td>
                                            <td><input type="text" id="correo" name="correo"/></td>
                                        </tr> 
                                        <tr>
                                            <td colspan="2"><button class="btn btn-success" value="registrar" name="op">Registrar</button></td>
                                        </tr>
                                    </table>
                                </td>
                                <td>
                                    <table class="table">
                                        <tr>
                                            <th colspan="2">LOGIN</th>
                                        </tr>
                                        <tr>
                                            <td>DNI:</td>
                                            <td><input type="text" id="dnilog" name="dnilog" size="12"/></td>
                                        </tr>
                                        <tr>
                                            <td>Password:</td>
                                            <td><input type="password" id="passwordlog" name="passwordlog"/></td>
                                        </tr> 
                                        <tr>
                                            <td colspan="2"><button class="btn btn-success" value="entrar" name="op">Login</button></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table> <br>              
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>