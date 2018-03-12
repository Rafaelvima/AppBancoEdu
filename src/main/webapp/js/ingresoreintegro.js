function validar() {
    var ojo = true;   
    var cuenta = $("#numcuenta").val();
    var descrip= $("#descripcion").val();
    var suma = 0;    
    for (var i = 0; i < cuenta.length - 1; i++) {
        suma += parseInt(cuenta.charAt(i));
    }
    //En cuanto entre en un if no sera valido
    if (!/^[0-9]{10}$/.test(cuenta)) {ojo = false;}//formato cuenta
    if (!((suma % 9) == (cuenta.charAt(cuenta.length - 1)))) {ojo = false;}//cuenta valida
    if (descrip == ''){ojo=false}
    if(document.getElementById("ingresar").checked == false && document.getElementById("retirar").checked == false){
        error = false;}   
    return ojo;
}
   
  
function funcionIngRei(){
        if(validar()){          
            var continuar = confirm ("¿Estas seguro?, ¿Acepta mandar los datos?");           
            if(continuar){           
            $.ajax({
                type: 'POST',
                url: "http://localhost:8282/AppBancoEdu/secure/ingresoreintegro",
                data: $("#formingrein").serialize(),
                success: function(data){      
                    document.getElementById("tabla").innerHTML = data;           
                    }
                ,error: function(xhr) {
                    document.getElementById("tabla").innerHTML = xhr.responseText;
                }
            });
        } 
    }
}
