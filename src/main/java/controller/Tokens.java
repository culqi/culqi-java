package controller;

/**
 * Created by culqi on 12/21/16.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Secure;
import model.Token;
import modelreponse.Error;
import modelreponse.TokenResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import util.Util;

public class Tokens {

    private static final String URL = "/tokens/";

    Token token = new Token();

    Util u = new Util();

    ObjectMapper mapper = new ObjectMapper();

    public String create(Secure secure, String jsonData) throws Exception {
        String message = "CODE STATUS NOT SUPPORTED";
        HttpResponse response;
        response = u.response(secure, URL, jsonData);
        HttpEntity entity = response.getEntity();
        String statusCode = response.getStatusLine().toString();
        // get json result to string
        String jsonResult = EntityUtils.toString(entity,"UTF-8");
        // convert json string to object
        if(statusCode.contains("400")){
            Error error = mapper.readValue(jsonResult, Error.class);
            message = error.getMessage();
        }
        if(statusCode.contains("401")){
            Error error = mapper.readValue(jsonResult, Error.class);
            message = error.getMessage();
        }
        if(statusCode.contains("201")) {
            TokenResponse tokenResponse = mapper.readValue(jsonResult, TokenResponse.class);
            message = tokenResponse.getValue();
        }
        return message;
    }

}
