package com.culqi.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.security.spec.ECField;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by culqi on 1/16/17.
 */
public class ObjectResult {

    public ObjectResult(){}

    ObjectMapper mapper = new ObjectMapper();

    public Map<String, Object> list(String url, Object params) throws Exception {
        String query = (params != null) ? mapper.writeValueAsString(params) : null;
        String response = new ResponseHelper().list(url, query);
        return mapper.readValue(response.toString(), new TypeReference<HashMap<String, Object>>(){});
    }

    public Map<String, Object> create(Map<String, Object> body, String url) throws Exception {
        String jsonData = mapper.writeValueAsString(body);
        String response = new ResponseHelper().create(url, jsonData);
        return mapper.readValue(response, new TypeReference<HashMap<String, Object>>(){});
    }

    public Map<String, Object> createEncrypt(Map<String, Object> body, String url) throws Exception {
        String jsonData = mapper.writeValueAsString(body);

        String publicKey = "-----BEGIN RSA PUBLIC KEY-----\n" +
                "MIGJAoGBALKb1HSgZO0EPSZrZ2NqaI3oDG1I7S7xUk5h9Ey7WvjM3WtfqwmOeMfF\n" +
                "xImHe75GO9lA5K+qj+EU6vrdNe5fQ0ZBvLbagccVSM9yqQ7Hq4vpCw6wQeR/Lf7w\n" +
                "XZqZWLsZ7f4/C3T+Sw9CF/1Z+i1rE+hyLTUIg2ZI4nFISVz5UGsDAgMBAAE=\n" +
                "-----END RSA PUBLIC KEY-----";

        Encrypt encrypt = new Encrypt();
        JSONObject jsonObj = new JSONObject(jsonData);


        jsonData = String.valueOf(encrypt.getJsonEncryptAESRSA(jsonData, publicKey, true));
        String response = new ResponseHelper().createEncrypt(url, jsonData);
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
        return mapper.readValue(response, new TypeReference<HashMap<String, Object>>() {
        });
    }
    
    public Map<String, Object> confirm(String url, String id) throws Exception {
        String response = new ResponseHelper().confirm(url, id);
        return mapper.readValue(response, new TypeReference<HashMap<String, Object>>(){});
    }

}
