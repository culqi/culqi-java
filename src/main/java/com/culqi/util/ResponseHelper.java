package com.culqi.util;

import com.culqi.model.Config;
import okhttp3.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by culqi on 1/16/17.
 */
public class ResponseHelper {

    public ResponseHelper(){}

    Config config = new Config();

    OkHttpClient client = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).build();

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public String list(String url, String params) throws Exception {

        HashMap<String, Object> map = new HashMap<String, Object>();

        String[] pairs = params.replace("{","").replace("}","").split(",");
        for (int i=0;i<pairs.length;i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split(":");
            map.put(keyValue[0], keyValue[1]);
        }

        HttpUrl.Builder builder = new HttpUrl.Builder();

        builder.scheme("https").host(Config.DOMAIN).addPathSegment(Config.PATH+url);

        Iterator it = map.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            builder.addQueryParameter(pair.getKey().toString(), pair.getValue().toString());
            it.remove();
        }

        HttpUrl urlquery =  builder.build();

        Request request = new Request.Builder()
                .url(urlquery)
                .header("Authorization","Bearer " +  Config.API_KEY)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String create(String url, String jsonData) throws Exception {
        String api_key = url.contains("tokens")?  Config.COD_ECOMMERCE: Config.API_KEY;
        RequestBody body = RequestBody.create(JSON, jsonData);
        Request request = new Request.Builder()
                .url(config.API_BASE+url)
                .header("Authorization","Bearer " + api_key)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String update(String url, String jsonData, String id) throws Exception {
        RequestBody body = RequestBody.create(JSON, jsonData);
        Request request = new Request.Builder()
                .url(config.API_BASE+url+id)
                .header("Authorization","Bearer " + Config.API_KEY)
                .patch(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String get_or_delete(String url, String id, boolean delete) throws Exception {
        Request.Builder builder = new Request.Builder();
        builder.url(config.API_BASE+url+id);
        builder.header("Authorization","Bearer " + Config.API_KEY);

        if(delete){
            builder.delete();
        }

        Request request = builder.build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String capture(String url, String id) throws Exception {
        RequestBody body = RequestBody.create(JSON, "");
        Request.Builder builder = new Request.Builder();
        builder.url(config.API_BASE+url+id+"/capture/");
        builder.header("Authorization","Bearer " + Config.API_KEY);
        builder.post(body);
        Request request = builder.build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
