package com.culqi.apioperation;

import com.culqi.model.ResponseCulqi;
import com.culqi.util.EncryptAESRSA;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by culqi on 1/16/17.
 */
public class ObjectResult {

    public ObjectResult(){}

    ObjectMapper mapper = new ObjectMapper();

    public ResponseCulqi list(String url, Object params) throws Exception {
        String query = (params != null) ? mapper.writeValueAsString(params) : null;
        ResponseCulqi response = new ResponseHelper().list(url, query);
        return response;
    }
    
    public ResponseCulqi create(Map<String, Object> body, String url) throws Exception {
        String jsonData = mapper.writeValueAsString(body);
        ResponseCulqi response = new ResponseHelper().create(url, jsonData);
        return response;
    }

    public ResponseCulqi create(Map<String, Object> body, String url, String rsaPublicKey, String rsaId ) throws Exception {
        String jsonData = mapper.writeValueAsString(body);

        EncryptAESRSA encryptAESRSA = new EncryptAESRSA();        
        jsonData = encryptAESRSA.getJsonEncryptAESRSA(jsonData, rsaPublicKey);

        ResponseCulqi response = new ResponseHelper().create(url, jsonData, rsaId);System.out.println(jsonData);
        System.out.println(response.getStatusCode());
        return response;
    }

    public ResponseCulqi update(Map<String, Object> body, String url, String id) throws Exception {
        String jsonData = mapper.writeValueAsString(body);
        ResponseCulqi response = new ResponseHelper().update(url, jsonData, id);
        return response;
    }
    
    public ResponseCulqi update(Map<String, Object> body, String url, String id, String rsaPublicKey, String rsaId) throws Exception {
        String jsonData = mapper.writeValueAsString(body);
        
        EncryptAESRSA encryptAESRSA = new EncryptAESRSA();        
        jsonData = encryptAESRSA.getJsonEncryptAESRSA(jsonData, rsaPublicKey);
        
        ResponseCulqi response = new ResponseHelper().update(url, jsonData, id, rsaId);
        return response;
    }

    public ResponseCulqi get_or_delete(String url, String id, boolean delete) throws Exception {
    	ResponseCulqi response = new ResponseHelper().get_or_delete(url, id, delete);
    	return response;
    }

    public ResponseCulqi capture(String url, String id) throws Exception {
    	ResponseCulqi response = new ResponseHelper().capture(url, id);
    	return response;
    }

    public ResponseCulqi capture(String url, String id, String rsaPublicKey, String rsaId) throws Exception {
        Map<String, Object> body = new HashMap<String, Object>();
        String jsonData = mapper.writeValueAsString(body);

        EncryptAESRSA encryptAESRSA = new EncryptAESRSA();
        jsonData = encryptAESRSA.getJsonEncryptAESRSA(jsonData, rsaPublicKey);

        ResponseCulqi response = new ResponseHelper().capture(url, id, jsonData, rsaId);
        return response;
    }
    
    public ResponseCulqi confirm(String url, String id) throws Exception {
    	ResponseCulqi response = new ResponseHelper().confirm(url, id);
    	return response;
    }
}
