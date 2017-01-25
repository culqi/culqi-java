package com.culqi.util;

import com.culqi.model.Security;
import com.culqi.query.ChargeQuery;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by culqi on 1/16/17.
 */
public class ObjectResult {

    public ObjectResult(){}

    ObjectMapper mapper = new ObjectMapper();

    public List<Map<String, Object>> list(Security security, String url, Object params) throws Exception {
        String query = mapper.writeValueAsString(params);
        String response = new ResponseHelper().list(security, url, query);
        JsonNode node = mapper.readTree(response);
        node = node.get("data");
        return mapper.readValue(node.toString(), new TypeReference<List<HashMap<String, Object>>>(){});
    }

    public Map<String, Object> create(Security security, Object object, String url) throws Exception {
        String jsonData = mapper.writeValueAsString(object);
        String response = new ResponseHelper().create(security, url, jsonData);
        return mapper.readValue(response, new TypeReference<HashMap<String, Object>>(){});
    }

    public Map<String, Object> get_or_delete(Security security, String url, String id, boolean delete) throws Exception {
        String response = new ResponseHelper().get_or_delete(security, url, id, delete);
        return mapper.readValue(response, new TypeReference<HashMap<String, Object>>(){});
    }

}
