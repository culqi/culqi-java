package com.culqi.util;

import com.culqi.model.Config;
import com.culqi.model.Security;
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

    public String list(Security security, String url, String params) throws Exception {

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
                .header("Authorization","Bearer " +  security.getAPI_KEY())
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String create(Security security, String url, String jsonData) throws Exception {
        String api_key = url.contains("tokens")? security.getCOD_ECOMMERCE() : security.getAPI_KEY();
        RequestBody body = RequestBody.create(JSON, jsonData);
        Request request = new Request.Builder()
                .url(config.API_BASE+url)
                .header("Authorization","Bearer " + api_key)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String get_or_delete(Security security, String url, String id, boolean delete) throws Exception {
        Request.Builder builder = new Request.Builder();
        builder.url(config.API_BASE+url+id);
        builder.header("Authorization","Bearer " + security.getAPI_KEY());

        if(delete){
            builder.delete();
        }

        Request request = builder.build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
