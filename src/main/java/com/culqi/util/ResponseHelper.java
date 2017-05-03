package com.culqi.util;

import com.culqi.Culqi;
import com.culqi.model.Config;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import java.util.Map;

/**
 * Created by culqi on 1/16/17.
 */
public class ResponseHelper {

    public ResponseHelper(){
    }

    Config config = new Config();

    public String list(String url, Map<String, Object> parameters) throws Exception {
        Unirest.setTimeouts(600000, 600000);
        HttpResponse<JsonNode> request = Unirest.get(config.API_BASE+"/"+url)
                .header("Authorization","Bearer " + Culqi.secret_key)
                .header("Content-Type", "application/json")
                .queryString(parameters)
                .asJson();
        return request.getBody().toString();
    }

    public String create(String url, String jsonData) throws Exception {
        String apiKey = url.contains("tokens")? Culqi.public_key: Culqi.secret_key;
        return sendPost(config.API_BASE+url, apiKey,jsonData);
    }

    public String update(String url, String jsonData, String id) throws Exception {
        HttpResponse<JsonNode> request = Unirest.patch(config.API_BASE+url+id)
                .header("Authorization","Bearer " + Culqi.secret_key)
                .header("Content-Type", "application/json")
                .body(jsonData)
                .asJson();
        return request.getBody().toString();
    }

    public String get_or_delete(String url, String id, boolean delete) throws Exception {
        if(delete) {
            HttpResponse<JsonNode> reqget = Unirest.delete(config.API_BASE+url+id)
                    .header("Authorization","Bearer " + Culqi.secret_key)
                    .header("Content-Type", "application/json").asJson();
            return reqget.getBody().toString();
        }
        HttpResponse<JsonNode> reqdelete = Unirest.get(config.API_BASE+url+id)
                .header("Authorization","Bearer " + Culqi.secret_key)
                .header("Content-Type", "application/json").asJson();
        return reqdelete.getBody().toString();
    }

    public String capture(String url, String id) throws Exception {
        return sendPost(config.API_BASE+url+id+"/capture/", "","");
    }

    public String sendPost(String url, String key, String data) throws Exception {
        String secretKey = (key.equals(""))? Culqi.secret_key : key;
        HttpResponse<JsonNode> request = Unirest.post(url)
                .header("Authorization","Bearer " + secretKey)
                .header("Content-Type", "application/json")
                .body(data)
                .asJson();
        return request.getBody().toString();
    }

}
