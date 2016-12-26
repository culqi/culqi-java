package com.culqi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.culqi.model.Charge;
import com.culqi.model.Secure;
import com.culqi.modelreponse.ChargeResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import com.culqi.util.Result;
import com.culqi.util.Util;

/**
 * Created by culqi on 12/22/16.
 */
public class Charges {

    private static final String URL = "/charges/";

    Util util = new Util();

    ObjectMapper mapper = new ObjectMapper();

    public Result createCharge(Secure secure, Charge charge) throws Exception {
        Result result = new Result();
        result.setMessage("CODE STATUS NOT SUPPORTED");
        HttpResponse response;
        String jsonData = mapper.writeValueAsString(charge);
        response = util.response(secure, URL, jsonData);
        HttpEntity entity = response.getEntity();
        String statusCode = response.getStatusLine().toString();
        // get json result to string
        String jsonResult = EntityUtils.toString(entity,"UTF-8");
        // convert json string to object
        Result resultError = util.getErrorMessage(statusCode,jsonResult);
        if(!resultError.getMessage().equals("")){
            result.setId(resultError.getId());
            result.setMessage(resultError.getMessage());
            result.setStatus(resultError.getStatus());
        }
        if(statusCode.contains("201")) {
            ChargeResponse chargeResponse = mapper.readValue(jsonResult, ChargeResponse.class);
            result.setId(chargeResponse.getId());
            result.setMessage(chargeResponse.getMerchant_message());
            result.setStatus("201");
        }
        return result;
    }

}
