package controller;

/**
 * Created by culqi on 12/21/16.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Secure;
import model.Token;
import modelreponse.ErrorResponse;
import modelreponse.TokenResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import util.Result;
import util.Util;

public class Tokens {

    private static final String URL = "/tokens/";

    Util util = new Util();

    ObjectMapper mapper = new ObjectMapper();

    public Result create(Secure secure, Token token) throws Exception {
        Result result = new Result();
        result.setMessage("CODE STATUS NOT SUPPORTED");
        HttpResponse response;
        String jsonData = mapper.writeValueAsString(token);
        response = util.response(secure, URL, jsonData);
        HttpEntity entity = response.getEntity();
        String statusCode = response.getStatusLine().toString();
        // get json result to string
        String jsonResult = EntityUtils.toString(entity,"UTF-8");
        // convert json string to object
        String errorMessage = util.getErrorMessage(statusCode,jsonResult);
        if(!errorMessage.equals("")){
            result.setMessage(errorMessage);
        }
        if(statusCode.contains("201")) {
            TokenResponse tokenResponse = mapper.readValue(jsonResult, TokenResponse.class);
            result.setId(tokenResponse.getId());
            result.setMessage(tokenResponse.getCard_number());
        }
        return result;
    }

}
