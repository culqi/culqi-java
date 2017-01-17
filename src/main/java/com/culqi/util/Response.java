package com.culqi.util;

import com.culqi.model.Config;
import com.culqi.model.Security;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by culqi on 1/16/17.
 */
public class Response {

    public Response(){}

    Config config = new Config();

    ObjectMapper mapper = new ObjectMapper();

    public HttpResponse call(Security security, String url, String jsonData) throws Exception {

        HttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(config.API_BASE+url);

        String api_key = url.contains("tokens")? security.getCOD_ECOMMERCE() : security.getAPI_KEY();

        post.setHeader("Authorization","Bearer " + api_key);

        post.setHeader("Content-Type","application/json");

        StringEntity entity = new StringEntity(jsonData);
        post.setEntity(entity);

        HttpClient httpClient = new DefaultHttpClient();

        return httpClient.execute(post);

    }


}
