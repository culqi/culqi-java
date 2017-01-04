package com.culqi.controller;

/**
 * Created by culqi on 12/21/16.
 */

import com.culqi.model.Secure;
import com.culqi.model.Token;
import com.culqi.util.Result;
import com.culqi.util.Util;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class Tokens {

    private static final String URL = "/tokens/";

    Util util = new Util();

    ObjectMapper mapper = new ObjectMapper();

    public Result create(Secure secure, Token token) throws Exception {
        Result result = new Result();
        HttpResponse response;
        String jsonData = mapper.writeValueAsString(token);
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
            result.setObject(resultError.getObject());
            result.setStatus(resultError.getStatus());
        }
        if(statusCode.contains("201")) {
            Map<String, Object> jsonObject = mapper.readValue(jsonResult, new TypeReference<HashMap<String, Object>>(){});
            result.setId(jsonObject.get("id").toString());
            result.setMessage("A Token was created successfully");
            result.setObject(jsonObject.get("object").toString());
            result.setStatus("201");
        }
        return result;
    }

}
