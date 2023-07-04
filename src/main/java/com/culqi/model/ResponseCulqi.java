package com.culqi.model;

import java.util.HashMap;
import java.util.Map;

public class ResponseCulqi {

	private int statusCode;
	private String body;
	
	
	public ResponseCulqi() {
		
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
	@Override
	public String toString() {
		return "ResponseCulqi [statusCode=" + statusCode + ", body=" + body + "]";
	}
	
	
	
	
}
