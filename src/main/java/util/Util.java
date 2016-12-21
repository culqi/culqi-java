package util;

import model.Config;
import model.Secure;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by culqi on 12/21/16.
 */
public class Util {

    public Util(){}

    Config config = new Config();

    public HttpResponse response(Secure secure, String url, String jsonData) throws Exception {

        HttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(config.API_BASE+url);

        post.setHeader("Authorization","Code " + secure.getCOD_ECOMERCE());
        post.setHeader("Content-Type","application/json");

        StringEntity entity = new StringEntity(jsonData);
        post.setEntity(entity);

        HttpClient httpClient = new DefaultHttpClient();

        return httpClient.execute(post);

    }

}
