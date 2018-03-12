function validar() {
    var ojo = true;   
    var fecha1 = new Date($("#fecha1mov").val());
    var fecha2 = new Date($("#fecha2mov").val());
    var cuenta = $("#cuentamov").val();
    var suma = 0;    
    for (var i = 0; i < cuenta.length - 1; i++) {
        suma += parseInt(cuenta.charAt(i));
    }
    //En cuanto entre en un if no sera valido
    if (!/^[0-9]{10}$/.test(cuenta)) {ojo = false;}//formato cuenta
    if (!((suma % 9) == (cuenta.charAt(cuenta.length - 1)))) {ojo = false;}//cuenta valida
    if ((isNaN(fecha1.getTime())) || (isNaN(fecha2.getTime()))) {ojo = false;}//fechas correctas
    if (fecha1 > fecha2) {ojo = false;}//fechas en orden
    return ojo;
}

function mostrarDatos(){
            if(validar){
                $.ajax({
                    type:"POST",
                    url:"http://localhost:8282/AppBancoEdu/secure/movimientos",
                    data:$("#formmov").serialize(),
                        success : function(data) {                   
                            var datos = JSON.parse(data);
                            $("#tabla").empty();
                            $("#aviso").fadeOut(100,function (){
                                $("#tabla").append("<tr><th>Fecha</th><th>Hora</th><th>Descripción</th><th>Importe</th></tr>");
                                for (var i = 0; i < datos.length; i++) {
                                    //formateo                          
                                    var fecha = fechaFormat(datos[i].mo_fec);
                                    var hora = horaFormat(datos[i].mo_hor);
                                    var importe = datos[i].mo_imp;
                                    //añado a tabla los datos anteriores
                                    $("#tabla").append('<tr><td>' + fecha + '</td><td>' + hora + '</td><td>' + datos[i].mo_des + '</td><td>' + importe + '</td></tr>');
                                }                      
                            });
                        }
                    
                });
            }              
        }
        
var meses = ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Novimbre","Diciembre"];

function fechaFormat(aux){
    var fecha = new Date(aux);
    var fechaFinal = "";
    fechaFinal = fecha.getDate()+" de "+(meses[fecha.getMonth()])+" de "+fecha.getFullYear();
    return fechaFinal;
}
function horaFormat(aux){
    var hora = aux.charAt(0)+aux.charAt(1)+":"+aux.charAt(2)+aux.charAt(3)+":("+aux.charAt(4)+aux.charAt(5)+")";
    return hora;
}