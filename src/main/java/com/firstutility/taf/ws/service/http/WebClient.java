package com.firstutility.taf.ws.service.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class WebClient {

	private static final String ENCODING = "UTF-8";
	private static final String CONTENT_TYPE = "text/xml";

	private static final String PARAMETERS = "?";
	private static final String EQUAL_TO = "=";
	private static final String AND = "&";

	private HttpClient httpClient = HttpClientBuilder.create().build();
	private HttpRequestBase httpMethod;
	
	private boolean useProxy = false;
	private HttpHost proxy;
	private RequestConfig proxyConfig;
	
	private static final Logger log = Logger.getLogger(WebClient.class);

	private String baseUrl;

	public WebClient(String baseUrl) {
		this.baseUrl = baseUrl;
		this.useProxy = false;
	}
	
	public WebClient(String baseUrl, String proxyHost, int port, String scheme) {
		this.baseUrl = baseUrl;
		if (!proxyHost.isEmpty()) {
			this.proxy = new HttpHost(proxyHost, port, scheme);
		    this.proxyConfig = RequestConfig.custom().setProxy(proxy).build();
		    this.useProxy = true;
		} else
			this.useProxy = false;
	}
	
	private void setproxyConfiguration() {
		httpMethod.setConfig(proxyConfig);
	}
	
	public String insertData(String method, String request) {
		log.info("[WebClient] Execute [" +baseUrl+method+ "] POST request with [" +request+ "] request content.");
		httpMethod = CRUD.CREATE.getHandler(completeURL(method));
		((HttpPost) httpMethod).setEntity(addRequestBody(request));
		return processRequest();
	}
	
	public String insertData(String request) {
		return insertData(null, request);
	}

	public String readData(String method, Map<String, String> params) {
		log.info("[WebClient] Execute [" + baseUrl+method+ "] GET request.");
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
		log.info("[WebClient] Execute [" +baseUrl+method+ "] PUT request with [" +request+ "] request content.");
		httpMethod = CRUD.UPDATE.getHandler(completeURL(method));
		((HttpPut) httpMethod).setEntity(addRequestBody(request));
		return processRequest();
	}
	
	public String updateData(String request) throws IOException {
		return updateData(request);	
	}
	
	public String deleteData(String method) {
		httpMethod = CRUD.DELETE.getHandler(completeURL(method));
		return processRequest();
	}

	private StringEntity addRequestBody(String request) {
		StringEntity entityRequest = null;
		try {
			entityRequest = new StringEntity(request, ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		entityRequest.setContentType(CONTENT_TYPE);
		return entityRequest;
	}
	
	private String completeURL(String method) {
		if (method == null)
			method = "";
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
				if (param.hasNext())
					uri.append(AND);
			}
		}
		return uri.toString();
	}
	
	private String processRequest() {
		
		HttpResponse response = null;
		String responseString = "";
		try {
			if (useProxy)
				setproxyConfiguration();
			response = httpClient.execute(httpMethod);
			HttpEntity entity = response.getEntity();
			responseString = EntityUtils.toString(entity, ENCODING);
			
			log.debug("[WebClient] responseString: \n" + responseString);
			httpMethod.releaseConnection();
		} catch (HttpResponseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		return responseString;
	}
}
