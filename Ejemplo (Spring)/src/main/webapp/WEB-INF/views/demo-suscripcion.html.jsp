<!DOCTYPE html>

<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html xmlns="http://www.w3.org/1999/html">
<head lang="en">
    <meta charset="UTF-8">
    <title>Ejemplo Culqi - Java</title>
    <script src="https://integ-pago.culqi.com/js/v1"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</head>
<body>
<h1>Nueva Suscripción</h1>
<table id="tb_suscripcion">
        <tr>
            <td>Codigo del comercio:</td>
            <td><input id="codigo_comercio" value="${codigo_comercio}" readonly="readonly"></td>
        </tr>
        <tr>
            <td>Plan ID:</td>
            <td>
                <select id="plan_id" name="plan_id">
                  <option value="1">plan-201609121</option>
                  <option value="2">plan-201609122</option>
                  <option value="3">plan-201609123</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Cargos predefinidos:</td>
            <td><input type="checkbox" id="cargos_predefinidos"></td>
        </tr>
    </table>
<br>
<button id="botonRegistrar">Registrar</button>

<!-- Abre el checkout Culqi -->
<script type="text/javascript">
    $('#botonRegistrar').on('click', function(e){
        Culqi.abrir();
        e.preventDefault();
    });
</script>
<!---->
<!-- Asigna el código del comercio -->
<script>
   Culqi.codigoComercio = 'u3XvS7xeC8eU';
</script>
<!---->
<!-- Configuración del contenido del checkout Culqi -->
<script>
    Culqi.configurar({
        nombre: 'Comercio Prueba JAVA',
        orden: 'x123131',
        moneda: 'PEN',
        descripcion: 'Suscripción',
        monto: 100,
        guardar: true
    });
</script>
<!---->
<!-- Obtenemos el token de la tarjeta -->
<script>
// Recibimos Token del Culqi.js
function culqi() {

    if (Culqi.token) {
      // Imprimir Token
      console.log(Culqi.token.id);
    }
    else{
      // Hubo un problema...
      // Mostramos JSON de objeto error en consola
      console.log(Culqi.error);
      alert("Culqi.error.mensaje");
    }

};
</script>
<script>
// Ejemplo: Tratando respuesta con AJAX (jQuery)
function culqi() {

    $.ajax({
            url: "/nueva-suscripcion",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({

                'codigo_comercio'       : Culqi.codigoComercio,
                'codigo_pais'           : 'PE',
                'direccion'             : 'Av. Arequipa 123',
                'ciudad'                : 'Lima',
                'telefono'              : '987654321',
                'nombre'                : 'Luis',
                'correo_electronico'    : 'lgonzales@me.com',
                'apellido'              : 'Gonzales',
                'usuarioId'             : 'Usuario de Prueba',
                'plan_id'               : document.getElementById("plan_id").options[document.getElementById("plan_id").selectedIndex].text,
                'token'                 : Culqi.token.id,
                'cargos_predefinidos'   : document.getElementById("cargos_predefinidos").checked

            }),
       success: function(data){

       },
       error:function( ){

       }
    });
};
</script>
<!---->
</body>
</html>