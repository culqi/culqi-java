package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Plan;
import model.Secure;
import modelreponse.PlanResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import util.Result;
import util.Util;

/**
 * Created by culqi on 12/22/16.
 */
public class Plans {

    private static final String URL = "/plans/";

    Util util = new Util();

    ObjectMapper mapper = new ObjectMapper();

    public Result createPlan(Secure secure, Plan plan) throws Exception {
        Result result = new Result();
        result.setMessage("CODE STATUS NOT SUPPORTED");
        HttpResponse response;
        String jsonData = mapper.writeValueAsString(plan);
        response = util.response(secure, URL, jsonData);
        HttpEntity entity = response.getEntity();
        String statusCode = response.getStatusLine().toString();
        // get json result to string
        String jsonResult = EntityUtils.toString(entity,"UTF-8");
        // convert json string to object
        String errorMessage = util.getErrorMessage(statusCode,jsonResult);
        if(!errorMessage.equals("")){
            result.setMessage(errorMessage);
        }
        if(statusCode.contains("201")) {
            PlanResponse planResponse = mapper.readValue(jsonResult, PlanResponse.class);
            result.setId(planResponse.getId());
            result.setMessage(planResponse.getAlias());
        }
        return result;
    }

}
