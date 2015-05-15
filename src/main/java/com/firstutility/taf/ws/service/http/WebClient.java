package com.firstutility.taf.ws.service.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class WebClient {

	private static final String ENCODING = "UTF-8";
	private static final String CONTENT_TYPE = "text/xml";

	private static final String PARAMETERS = "?";
	private static final String EQUAL_TO = "=";
	private static final String AND = "&";

	private HttpClient httpClient = new HttpClient();
	private HttpMethod httpMethod;

	private String baseUrl;

	public WebClient(String baseUrl){
		this.baseUrl = baseUrl;
	}
	
	public String insertData(String method, String request) {
		httpMethod = CRUD.CREATE.getHandler(completeURL(method));
		((PostMethod) httpMethod).setRequestEntity(addRequestBody(request));
		return processRequest();
	}
	
	public String insertData(String request) {
		return insertData(null, request);
	}

	public String readData(String method, Map<String, String> params) {
		httpMethod = CRUD.READ.getHandler(completeURL(method, params));
		return processRequest();
	}
	
	public String readData(Map<String, String> params) {
		return readData(null, params);
	}
	
	public String readData(String method) {
		return readData(method, null);
	}
	
	public String readData() {
		return readData(null, null);
	}

	public String updateData(String method, String request) {
		httpMethod = CRUD.UPDATE.getHandler(completeURL(method));
		((PutMethod) httpMethod).setRequestEntity(addRequestBody(request));
		return processRequest();
	}
	
	public String updateData(String request) throws IOException {
		return updateData(request);	
	}
	
	@SuppressWarnings("unused")
	private void deleteData() {
		httpMethod = CRUD.DELETE.getHandler(baseUrl);
	}

	
	
	private RequestEntity addRequestBody(String request) {
		try {
			return new StringRequestEntity(request, CONTENT_TYPE, ENCODING);
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}
	
	private String completeURL(String method){
		if(method == null){
			method = "";
		}
		return baseUrl + method;
	}
	
	private String completeURL(String method, Map<String, String> params){
		StringBuilder uri = new StringBuilder(completeURL(method));
		uri.append(PARAMETERS);
		if (params != null) {
			Iterator<String> param = params.keySet().iterator();
			while (param.hasNext()) {
				String paramKey = param.next();
				uri.append(paramKey).append(EQUAL_TO).append(params.get(paramKey));
				if (param.hasNext()) {
					uri.append(AND);
				}
			}
		}
		return uri.toString();
	}
	
	private String processRequest() {
		String response = "";
		try {
			httpClient.executeMethod(httpMethod);
			response = httpMethod.getResponseBodyAsString();
			httpMethod.releaseConnection();
		} catch (HttpException e) {
		} catch (IOException e) {
		}
		return response;
	}
}
