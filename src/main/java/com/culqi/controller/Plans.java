package com.culqi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.culqi.model.Plan;
import com.culqi.model.Secure;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import com.culqi.util.Result;
import com.culqi.util.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by culqi on 12/22/16.
 */
public class Plans {

    private static final String URL = "/plans/";

    Util util = new Util();

    ObjectMapper mapper = new ObjectMapper();

    public Result createPlan(Secure secure, Plan plan) throws Exception {
        Result result = new Result();
        HttpResponse response;
        String jsonData = mapper.writeValueAsString(plan);
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
            result.setMessage(jsonObject.get("alias").toString());
            result.setObject(jsonObject.get("object").toString());
            result.setStatus("201");
        }
        return result;
    }

}
