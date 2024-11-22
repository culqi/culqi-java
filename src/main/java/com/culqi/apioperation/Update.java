package com.culqi.apioperation;

import java.util.Map;

import com.culqi.model.ResponseCulqi;

/**
 * Created by culqi on 12/02/17.
 */
public interface Update {

	ResponseCulqi update(Map<String, Object> body, String id) throws Exception;

}
