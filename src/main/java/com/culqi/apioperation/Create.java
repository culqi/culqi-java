package com.culqi.apioperation;

import java.util.Map;

import com.culqi.model.ResponseCulqi;

/**
 * Created by culqi on 12/02/17.
 */
public interface Create {

	ResponseCulqi create(Map<String, Object> body) throws Exception;

}
