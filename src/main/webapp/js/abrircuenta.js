function validar() {
    var ojo = true;   
    var cuenta = $("#numcuenta").val();
    var dni1= $("#dni1").val();
    var dni2= $("#dni2").val();
    var importe=$("#importe").val();
    var suma = 0;    
    for (var i = 0; i < cuenta.length - 1; i++) {
        suma += parseInt(cuenta.charAt(i));
    }
    //En cuanto entre en un if no sera valido
    if (!/^[0-9]{10}$/.test(cuenta)) {ojo = false;}//formato cuenta
    if (!/^[0-9]{8}[A-Z]{1}$/.test(dni1)) {ojo=false;}//formato dni's
    if (!/^[0-9]{8}[A-Z]{1}$/.test(dni2)) {ojo=false;}
    if (!((suma % 9) == (cuenta.charAt(cuenta.length - 1)))) {ojo = false;}//cuenta valida
    if (dni1 == dni2){ojo=false;}//dni's distintos
    if (importe<0){ojo=false;}//importe positivo
    return ojo;
}

function funcionAdd(){
        if(validar()){          
            var continuar = confirm ("¿Estas seguro?, ¿Acepta mandar los datos?");           
            if(continuar){           
            $.ajax({
                type: 'GET',
                url: "http://localhost:8282/AppBancoEdu/secure/abrircuenta",
                data: $("#formadd").serialize(),
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