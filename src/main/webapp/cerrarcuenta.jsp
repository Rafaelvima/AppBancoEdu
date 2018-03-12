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
        <script src="../js/cerrarcuenta.js"></script>
        <title>Cerrar Cuenta</title>
    </head>
    <body>
         <div class="container">
            <h1>CERRAR CUENTA</h1><br><c:out value="${pasalo}"></c:out><hr> 
            <h4><a href="../inicio.jsp">Volver</a><span> - </span><a href="../login">Logout</a></h4><hr><br>
            <div class="row">
                <h2>En proceso, disculpe las molestias</h2><br>
                <h4 style="color:red">Consulte con el administrador de la base de datos para borrar cuentas</h4>
            </div>
         </div>
    </body>
</html>
