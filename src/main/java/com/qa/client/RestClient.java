package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	//GET Method
	
	public void get(String url) throws ClientProtocolException, IOException
	{
		//create one client connection & create closeasblehttpclient object
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpGet httpGet= new HttpGet(url); // http get request
		CloseableHttpResponse closeableHttpResponse=  httpClient.execute(httpGet); //hit the get url & get closeablehhtpclient response
		//Status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code: "+statusCode);
		String responseString =  EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8"); // UTF-8(character format)
		JSONObject responseJsonobject = new JSONObject(responseString);
		System.out.println("Response JSON From Api");
		//All Headers
		Header[] headerArray=closeableHttpResponse.getAllHeaders();
		HashMap<String,String> allHeader = new HashMap<String, String>();
		for(Header header:headerArray)
		{
			allHeader.put(header.getName(), header.getValue());
		}
		System.out.println("Header Array : "+allHeader);
	}
}
