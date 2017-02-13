package com.culqi.apioperation;

import java.util.Map;

/**
 * Created by culqi on 12/02/17.
 */
public interface Update {

    Map<String, Object> update(Map<String, Object> body, String id) throws Exception;

}
