package com.culqi.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by culqi on 1/16/17.
 */
public class ObjectResult {

    public ObjectResult(){}

    ObjectMapper mapper = new ObjectMapper();

    public Map<String, Object> list(String url, Map<String, Object> parameters) throws Exception {
        String response = new ResponseHelper().list(url, parameters);
        return mapper.readValue(response.toString(), new TypeReference<HashMap<String, Object>>(){});
    }

    public Map<String, Object> create(Map<String, Object> body, String url) throws Exception {
        String jsonData = mapper.writeValueAsString(body);
        String response = new ResponseHelper().create(url, jsonData);
        return mapper.readValue(response, new TypeReference<HashMap<String, Object>>(){});
    }

    public Map<String, Object> update(Map<String, Object> body, String url, String id) throws Exception {
        String jsonData = mapper.writeValueAsString(body);
        String response = new ResponseHelper().update(url, jsonData, id);
        return mapper.readValue(response, new TypeReference<HashMap<String, Object>>(){});
    }

    public Map<String, Object> get_or_delete(String url, String id, boolean delete) throws Exception {
        String response = new ResponseHelper().get_or_delete(url, id, delete);
        return mapper.readValue(response, new TypeReference<HashMap<String, Object>>(){});
    }

    public Map<String, Object> capture(String url, String id) throws Exception {
        String response = new ResponseHelper().capture(url, id);
        return mapper.readValue(response, new TypeReference<HashMap<String, Object>>(){});
    }

}
