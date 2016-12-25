package com.culqi.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.culqi.model.Config;
import com.culqi.model.Secure;
import com.culqi.modelreponse.ErrorResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by culqi on 12/21/16.
 */
public class Util {

    public Util(){}

    Config config = new Config();

    ObjectMapper mapper = new ObjectMapper();

    public HttpResponse response(Secure secure, String url, String jsonData) throws Exception {

        HttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(config.API_BASE+url);

        if (url.contains("tokens")) {
            post.setHeader("Authorization","Code " + secure.getCOD_ECOMERCE());
        } else {
            post.setHeader("Authorization","Bearer " + secure.getAPI_KEY());
        }

        post.setHeader("Content-Type","application/json");

        StringEntity entity = new StringEntity(jsonData);
        post.setEntity(entity);

        HttpClient httpClient = new DefaultHttpClient();

        return httpClient.execute(post);

    }

    public String getErrorMessage(String statusCode, String jsonResult) throws Exception {
        String message = "";
        if(statusCode.contains("400")) {
            ErrorResponse errorResponse = mapper.readValue(jsonResult, ErrorResponse.class);
            message = "STATUS CODE: 400 "+errorResponse.getMessage();
        }
        if(statusCode.contains("401")) {
            ErrorResponse errorResponse = mapper.readValue(jsonResult, ErrorResponse.class);
            message = "STATUS CODE: 401 "+errorResponse.getMessage();
        }
        if(statusCode.contains("500")) {
            message = "STATUS CODE: 500 "+jsonResult;
        }
        return message;
    }

}
