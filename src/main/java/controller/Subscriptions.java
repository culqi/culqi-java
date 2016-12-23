package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Secure;
import model.Subscription;
import modelreponse.SubscriptionResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import util.Util;

/**
 * Created by culqi on 12/23/16.
 */
public class Subscriptions {

    private static final String URL = "/subscriptions/";

    Subscription subscription = new Subscription();

    Util util = new Util();

    ObjectMapper mapper = new ObjectMapper();

    public String createSubscription(Secure secure, Subscription subscription) throws Exception {
        String message = "CODE STATUS NOT SUPPORTED";
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
            message = errorMessage;
        }
        if(statusCode.contains("201")) {
            SubscriptionResponse subscriptionResponse = mapper.readValue(jsonResult, SubscriptionResponse.class);
            message = subscriptionResponse.getId();
        }
        return message;
    }

}
