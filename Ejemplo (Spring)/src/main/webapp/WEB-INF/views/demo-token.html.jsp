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
<h1>Nuevo Token</h1>

<button id="botonNuevoToken">Nuevo Token</button>

<!-- Abre el checkout Culqi -->
<script type="text/javascript">
    $('#botonNuevoToken').on('click', function(e){
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
        descripcion: 'Nuevo Token',
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
      alert("Token generado!\n"+Culqi.token.id);
    }
    else{
      // Hubo un problema...
      // Mostramos JSON de objeto error en consola
      console.log(Culqi.error);
      alert("Culqi.error.mensaje");
    }
};
</script>
<!---->
</body>
</html>