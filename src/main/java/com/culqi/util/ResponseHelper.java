package com.culqi.util;

import com.culqi.Culqi;
import com.culqi.model.Config;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import okhttp3.*;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by culqi on 1/16/17.
 */
public class ResponseHelper {

    public ResponseHelper(){}

    Config config = new Config();

    OkHttpClient client = new OkHttpClient.Builder().connectTimeout(180, TimeUnit.SECONDS)
                          .readTimeout(180, TimeUnit.SECONDS).build();

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public String list(String url, String params) {

        String result = "";

        try {

            HttpUrl.Builder builder = new HttpUrl.Builder();

            builder.scheme("https").host(Config.DOMAIN).addPathSegment("v2").addPathSegment(url);

            if (params != null) {

                HashMap<String, Object> map = new HashMap<String, Object>();

                String[] pairs = params.replace("{", "").replace("}", "").split(",");

                for (int i = 0; i < pairs.length; i++) {
                    String pair = pairs[i];
                    String[] keyValue = pair.split(":");
                    map.put(keyValue[0].replace("\"",""), keyValue[1].replace("\"",""));
                }

                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    builder.addQueryParameter(entry.getKey(), entry.getValue().toString());
                }

            }

            HttpUrl urlquery = builder.build();
            Request request = new Request.Builder()
                    .url(urlquery)
                    .header("Authorization","Bearer " + Culqi.secret_key)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();

        } catch (IOException e) {
            result = exceptionError();
        }
        return result;
    }

    public String create(String url, String jsonData) {
        String result = "";
        try {
            String api_key = url.contains("tokens") ? Culqi.public_key : Culqi.secret_key;
            String base_url = url.contains("tokens") ? config.API_SECURE : config.API_BASE;
            RequestBody body = RequestBody.create(JSON, jsonData);
            Request request = new Request.Builder()
                    .url(base_url+url)
                    .header("Authorization","Bearer " + api_key)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            result = exceptionError();
        }
        return result;
    }

    public String createEncrypt(String url, String jsonData) {
        String result = "";
        try {
            String api_key = url.contains("tokens") ? Culqi.public_key : Culqi.secret_key;
            String base_url = url.contains("tokens") ? config.API_SECURE : config.API_BASE;
            RequestBody body = RequestBody.create(JSON, jsonData);
            Request request = new Request.Builder()
                    .url(base_url+url)
                    .header("Authorization","Bearer " + api_key)
                    .header("x-culqi-rsa-id","5c8360d5-6aca-4961-9278-2ce9721d3536")
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            result = exceptionError();
        }
        return result;
    }

    public String update(String url, String jsonData, String id) {
        String result = "";
        try {
            RequestBody body = RequestBody.create(JSON, jsonData);
            Request request = new Request.Builder()
                    .url(config.API_BASE+url+id)
                    .header("Authorization","Bearer " + Culqi.secret_key)
                    .patch(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            result = exceptionError();
        }
        return result;
    }

    public String get_or_delete(String url, String id, boolean delete) {
        String result = "";
        try {
            Request.Builder builder = new Request.Builder();
            builder.url(config.API_BASE + url + id);
            System.out.println(config.API_BASE + url + id);
            builder.header("Authorization","Bearer " + Culqi.secret_key);
            if (delete) {
                builder.delete();
            }
            Request request = builder.build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            result = exceptionError();
        }
        return result;
    }

    public String capture(String url, String id) throws Exception {
        String result = "";
        try {
            RequestBody body = RequestBody.create(JSON, "");
            Request.Builder builder = new Request.Builder();
            builder.url(config.API_BASE + url + id + "/capture/");
            builder.header("Authorization", "Bearer " + Culqi.secret_key);
            builder.post(body);
            Request request = builder.build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            result = exceptionError();
        }
        return result;
    }
    
    public String confirm(String url, String id) throws Exception {
        String result = "";
        try {
            RequestBody body = RequestBody.create(JSON, "");
            Request.Builder builder = new Request.Builder();
            builder.url(config.API_BASE+url+id+"/confirm/");
            builder.header("Authorization","Bearer " + Culqi.public_key);
            builder.post(body);
            Request request = builder.build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            result = exceptionError();
        }
        return result;
    }

    private String exceptionError() {
        String result = "";
        Map<String, Object> errorResponse = new HashMap<String, Object>();
        errorResponse.put("object", "error");
        errorResponse.put("type", "internal");
        errorResponse.put("charge_id", "ninguno");
        errorResponse.put("code", "ninguno");
        errorResponse.put("decline_code", "ninguno");
        errorResponse.put("merchant_message", "El tiempo de espera ha sido excedido");
        errorResponse.put("user_message", "El tiempo de espera ha sido excedido");
        errorResponse.put("param", "ninguno");
        try {
            result = new ObjectMapper().writeValueAsString(errorResponse);
        } catch (JsonProcessingException jx) {

        }
        return result;
    }

}
