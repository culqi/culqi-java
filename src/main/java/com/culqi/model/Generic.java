package com.culqi.model;

import java.util.Map;

import com.culqi.apioperation.All;
import com.culqi.apioperation.Create;
import com.culqi.apioperation.Find;
import com.culqi.util.ObjectResult;

public class Generic implements All, Create, Find {

    private String URL = "";
    
    public Generic(String url) {
    	this.URL = url;
    }

    public Map<String, Object> list(Map<String, Object> params) throws Exception {
        params = (params == null || params.size() == 0) ? null : params;
        return new ObjectResult().list(this.URL.replace("/",""), params);
    }

    public Map<String, Object> list() throws Exception {
        return new ObjectResult().list(this.URL.replace("/",""), null);
    }

    public Map<String, Object> create(Map<String, Object> body) throws Exception {
        return new ObjectResult().create(body, this.URL);
    }

    public Map<String, Object> create(Map<String, Object> body, String rsaPublicKey, String rsaId) throws Exception {
        return new ObjectResult().create(body, this.URL, rsaPublicKey, rsaId);
    }

    public Map<String, Object> get(String id) throws Exception {
        return new ObjectResult().get_or_delete(this.URL, id, false);
    }
    
    public Map<String, Object> update(Map<String, Object> body, String id) throws Exception {
        return new ObjectResult().update(body, this.URL, id);
    }
    
    public Map<String, Object> update(Map<String, Object> body, String id, String rsaPublicKey, String rsaId) throws Exception {
        return new ObjectResult().update(body, this.URL, id, rsaPublicKey, rsaId);
    }
    
    public Map<String, Object> delete(String id) throws Exception {
        return new ObjectResult().get_or_delete(this.URL, id, true);
    }

}
