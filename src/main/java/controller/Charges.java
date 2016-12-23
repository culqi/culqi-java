package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Charge;
import model.Secure;
import modelreponse.ChargeResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import util.Util;

/**
 * Created by culqi on 12/22/16.
 */
public class Charges {

    private static final String URL = "/charges/";

    Charge charge = new Charge();

    Util util = new Util();

    ObjectMapper mapper = new ObjectMapper();

    public String createCharge(Secure secure, Charge charge) throws Exception {
        String message = "CODE STATUS NOT SUPPORTED";
        HttpResponse response;
        String jsonData = mapper.writeValueAsString(charge);
        response = util.response(secure, URL, jsonData);
        HttpEntity entity = response.getEntity();
        String statusCode = response.getStatusLine().toString();
        // get json result to string
        String jsonResult = EntityUtils.toString(entity,"UTF-8");
        // convert json string to object
        String errorMessage = util.getErrorMessage(statusCode,jsonResult);
        if(!errorMessage.equals("")){
            message = errorMessage;
        }
        if(statusCode.contains("201")) {
            ChargeResponse chargeResponse = mapper.readValue(jsonResult, ChargeResponse.class);
            message = chargeResponse.getMerchant_message();
        }
        return message;
    }

}
