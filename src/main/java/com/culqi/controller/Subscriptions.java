package com.culqi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.culqi.model.Secure;
import com.culqi.model.Subscription;
import com.culqi.modelreponse.SubscriptionResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import com.culqi.util.Result;
import com.culqi.util.Util;

/**
 * Created by culqi on 12/23/16.
 */
public class Subscriptions {

    private static final String URL = "/subscriptions/";

    Util util = new Util();

    ObjectMapper mapper = new ObjectMapper();

    public Result createSubscription(Secure secure, Subscription subscription) throws Exception {
        Result result = new Result();
        result.setMessage("CODE STATUS NOT SUPPORTED");
        HttpResponse response;
        String jsonData = mapper.writeValueAsString(subscription);
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
            SubscriptionResponse subscriptionResponse = mapper.readValue(jsonResult, SubscriptionResponse.class);
            result.setId(subscriptionResponse.getId());
            result.setMessage(subscriptionResponse.getObject());
        }
        return result;
    }

}
