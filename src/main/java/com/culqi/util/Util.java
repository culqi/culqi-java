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
import java.util.UUID;

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

    public Result getErrorMessage(String statusCode, String jsonResult) throws Exception {
        Result result = new Result();
        if(statusCode.contains("400")) {
            ErrorResponse errorResponse = mapper.readValue(jsonResult, ErrorResponse.class);
            result.setMessage("STATUS CODE: 400 "+errorResponse.getMessage());
            result.setId("400");
            result.setStatus("400");
        } else if(statusCode.contains("401")) {
            ErrorResponse errorResponse = mapper.readValue(jsonResult, ErrorResponse.class);
            result.setMessage("STATUS CODE: 401 "+errorResponse.getMessage());
            result.setId("401");
            result.setStatus("401");
        } else if(statusCode.contains("500")) {
            result.setMessage("STATUS CODE: 500 "+jsonResult);
            result.setId("500");
            result.setStatus("500");
        } else {
            result.setId("id");
            result.setMessage(statusCode);
            result.setStatus("status");
        }
        return result;
    }

    public int ramdomNumber() {
        String uuidNumber = String.valueOf(UUID.randomUUID().getMostSignificantBits()%2000);
        return Integer.parseInt(uuidNumber.replace("-",""));
    }

    public String ramdonString() {
        return UUID.randomUUID().toString();
    }

}
