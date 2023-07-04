package com.culqi.apioperation.service;

import java.util.Map;

import com.culqi.apioperation.All;
import com.culqi.apioperation.Create;
import com.culqi.apioperation.Find;
import com.culqi.apioperation.ObjectResult;
import com.culqi.model.ResponseCulqi;

public class Generic implements All, Create, Find {

    private String URL = "";
    
    public Generic(String url) {
    	this.URL = url;
    }

    public ResponseCulqi list(Map<String, Object> params) throws Exception {
        params = (params == null || params.size() == 0) ? null : params;
        return new ObjectResult().list(this.URL.replace("/",""), params);
    }

    public ResponseCulqi list() throws Exception {
        return new ObjectResult().list(this.URL.replace("/",""), null);
    }

    public ResponseCulqi create(Map<String, Object> body) throws Exception {
        return new ObjectResult().create(body, this.URL);
    }

    public ResponseCulqi create(Map<String, Object> body, String rsaPublicKey, String rsaId) throws Exception {
        return new ObjectResult().create(body, this.URL, rsaPublicKey, rsaId);
    }

    public ResponseCulqi get(String id) throws Exception {
        return new ObjectResult().get_or_delete(this.URL, id, false);
    }
    
    public ResponseCulqi update(Map<String, Object> body, String id) throws Exception {
        return new ObjectResult().update(body, this.URL, id);
    }
    
    public ResponseCulqi update(Map<String, Object> body, String id, String rsaPublicKey, String rsaId) throws Exception {
        return new ObjectResult().update(body, this.URL, id, rsaPublicKey, rsaId);
    }
    
    public ResponseCulqi delete(String id) throws Exception {
        return new ObjectResult().get_or_delete(this.URL, id, true);
    }

}
