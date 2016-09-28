/**
 * Created by William on 10/7/15.
 */
package com.culqi.pruebas;

import com.culqi.sdk.Culqi;
import com.culqi.sdk.Pago;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
public class CrearVentaTest {

    private SecureRandom random = new SecureRandom();

    public String randomOrder() {
        return new BigInteger(16, random).toString(4);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CrearVentaTest.class);

    @Test
    public void crearVentaTest() {

        Culqi.codigoComercio = "demo";
        Culqi.llaveSecreta = "JlhLlpOB5s1aS6upiioJkmdQ0OYZ6HLS2+/o4iYO2MQ=";
        Culqi.servidorBase = "https://integ-pago.culqi.com";

        String orden = randomOrder();

        String moneda = "PEN";

        Integer monto = random.nextInt(99900)+100;

        Float montoFlotante = (float)monto / 100;
        String montoFormateado = new DecimalFormat("0.00").format(montoFlotante);

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put(Pago.PARAM_NUMERO_PEDIDO, orden);
        params.put("correo_electronico", "wmuro@me.com");
        params.put("nombres", "William");
        params.put("apellidos", "Muro");
        params.put("id_usuario_comercio", "123456");
        params.put(Pago.PARAM_MONEDA, moneda);
        params.put(Pago.PARAM_MONTO, 123);
        params.put(Pago.PARAM_DESCRIPCION, "Venta de prueba.");
        params.put(Pago.PARAM_CIUDAD, "Lima");
        params.put(Pago.PARAM_COD_PAIS, "PE");
        params.put(Pago.PARAM_DIRECCION, "Avenida Lima 1234");
        params.put(Pago.PARAM_NUM_TEL, "12345");

        Map<String, Object> respuesta = com.culqi.sdk.Pago.crearDatosPago(params);

        LOGGER.info("Información recibida. {}", respuesta.toString() );

    }

}
