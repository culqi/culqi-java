package com.culqi.apioperation;

import java.util.Map;

import com.culqi.model.ResponseCulqi;

/**
 * Created by culqi on 12/02/17.
 */
public interface Delete {

	ResponseCulqi delete(String id) throws Exception;

}
