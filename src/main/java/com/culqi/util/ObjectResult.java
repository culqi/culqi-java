package com.culqi.util;

import com.culqi.model.Security;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by culqi on 1/16/17.
 */
public class ObjectResult {

    public ObjectResult(){
    }

    public Map<String, Object> run(Security security, Object object, String url) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        HttpResponse response;
        String jsonData = mapper.writeValueAsString(object);
        response = new Response().call(security, url, jsonData);
        HttpEntity entity = response.getEntity();
        String statusCode = response.getStatusLine().toString();
        // get json result to string
        String jsonResult = EntityUtils.toString(entity,"UTF-8");
        return mapper.readValue(jsonResult, new TypeReference<HashMap<String, Object>>(){});
    }

}
