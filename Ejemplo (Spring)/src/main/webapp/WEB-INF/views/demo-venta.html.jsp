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
<h1>Nueva Venta</h1>
<input id="codigo_comercio" value="${codigo_comercio}" readonly="readonly">
<button id="botonPagar">Pagar</button>

<!-- Abre el checkout Culqi -->
<script type="text/javascript">
    $('#botonPagar').on('click', function(e){
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
        descripcion: 'Venta',
        monto: 100,
        guardar: false
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
        url: "/nueva-venta",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({

            'token'               : Culqi.token.id,
            'codigo_comercio'     : Culqi.codigoComercio,
            'moneda'              : 'PEN',
            'monto'               : '100',
            'descripcion'         : 'Venta de prueba',
            'pedido'              : 'x123123',
            'codigo_pais'         : 'PE',
            'ciudad'              : 'Lima',
            'usuario'             : 'Usuario de Prueba',
            'direccion'           : 'Av. Arequipa 123',
            'telefono'            : '987654321',
            'nombres'             : 'Luis',
            'apellidos'           : 'Gonzales',
            'correo_electronico'  : 'lgonzales@me.com'

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