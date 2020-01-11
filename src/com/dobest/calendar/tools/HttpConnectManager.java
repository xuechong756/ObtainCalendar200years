package com.dobest.calendar.tools;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;


public class HttpConnectManager {
	
	private static HttpConnectManager httpConnectManager;
	private OkHttpClient okHttpClient;
	
	private HttpConnectManager()
	{
		this.okHttpClient = new OkHttpClient();
	}
	
	public static HttpConnectManager getInstance()
	{
		if(HttpConnectManager.httpConnectManager == null)
			HttpConnectManager.httpConnectManager = new HttpConnectManager();
		return HttpConnectManager.httpConnectManager;
	}

	//post请求
	public  void HttpPostConnect(String url, Map<String, String> requestHeader, Map<String, String> requestBody, Callback callback) {
		this.okHttpClient.newCall(mapToRequestHeader(requestHeader).url(url)
				.post(mapToRequestBody(requestBody).build()).build())
				.enqueue(callback);
	}
	
	//get请求
	public  void HttpGetConnect(String url, Map<String, String> requestHeader, Map<String, String> requestBody, Callback callback) {
		this.okHttpClient.newCall(mapToRequestHeader(requestHeader).get().url(url + getGetRequestBody(requestBody)).build()).enqueue(callback);
	}
	
	public OkHttpClient getOkHttpClient()
	{
		return this.okHttpClient;
	}
	
	//设置请求连接时间
	public void setConnectTimeout(int ms)
	{
		this.okHttpClient.setConnectTimeout(ms, TimeUnit.SECONDS);
	}
	
	//设置请求超时时间
	public void setReadTimeout(int ms)
	{
		this.okHttpClient.setReadTimeout(ms, TimeUnit.SECONDS);
	}
	
	//将get请求体转成url后面的请求参数
	private String getGetRequestBody(Map<String, String> requestBody)
	{
		 if(requestBody == null || requestBody.isEmpty()) 
			 return "";
		 Set<String> keys = requestBody.keySet();
		 Iterator<String> iterator = keys.iterator();
		 StringBuilder stringBuider = new StringBuilder("?");
		 while(iterator.hasNext())
		 {
			String key = iterator.next();
			stringBuider.append(key + "=" + requestBody.get(key) + "&");
		 }
		return stringBuider.substring(0, stringBuider.length() - 1);
	}
	
	private  FormEncodingBuilder mapToRequestBody(Map<String, String> requestBody)
	{
		FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
		if(requestBody == null || requestBody.isEmpty())
			return formEncodingBuilder;
		
		Set<String> set = requestBody.keySet();
		Iterator<String> iterator = set.iterator();
		
		while(iterator.hasNext())
		{
			String key = iterator.next();
			formEncodingBuilder.add(key, requestBody.get(key));
		}
		return formEncodingBuilder;
	}
	
	private  Builder mapToRequestHeader(Map<String, String> requestHeader)
	{
		Builder builder = new Request.Builder(); 
		if(requestHeader == null || requestHeader.isEmpty())
			return builder;
		
		Set<String> set = requestHeader.keySet();
		Iterator<String> iterator = set.iterator();
		
		while(iterator.hasNext())
		{
			String key = iterator.next();
			builder.header(key, requestHeader.get(key));
		}
		return builder;
	}
}
