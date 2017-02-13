package com.culqi.apioperation;

import java.util.Map;

/**
 * Created by culqi on 12/02/17.
 */
public interface Find {

    Map<String, Object> get(String id) throws Exception;

}
