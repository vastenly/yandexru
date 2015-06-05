package com.firstutility.taf.ws.service.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.HttpResponseException;
import org.apache.http.entity.StringEntity;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.HttpClientBuilder;

public class WebClient {

	private static final String ENCODING = "UTF-8";
	private static final String CONTENT_TYPE = "text/xml";

	private static final String PARAMETERS = "?";
	private static final String EQUAL_TO = "=";
	private static final String AND = "&";

	HttpClient httpClient = HttpClientBuilder.create().build();
	private HttpRequestBase httpMethod;

	private String baseUrl;

	public WebClient(String baseUrl){
		this.baseUrl = baseUrl;
	}
	
	public String insertData(String method, String request) {
		httpMethod = CRUD.CREATE.getHandler(completeURL(method));
		((HttpPost) httpMethod).setEntity(addRequestBody(request));
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
		((HttpPut) httpMethod).setEntity(addRequestBody(request));
		return processRequest();
	}
	
	public String updateData(String request) throws IOException {
		return updateData(request);	
	}
	
	@SuppressWarnings("unused")
	private void deleteData() {
		httpMethod = CRUD.DELETE.getHandler(baseUrl);
	}

	
	
	private StringEntity addRequestBody(String request) {
		try {
			return new StringEntity (request, CONTENT_TYPE, ENCODING);
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
		
		HttpResponse response = null;
		String stringResponse = "";
		try {
			response = httpClient.execute(httpMethod);
			httpMethod.releaseConnection();
			stringResponse = response.getEntity().getContent().toString();
		} catch (HttpResponseException e) {
		} catch (IOException e) {
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stringResponse;
	}
}
