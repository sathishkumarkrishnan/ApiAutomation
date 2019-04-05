package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetApiTest extends TestBase{

	TestBase testbase;
	String serviceURL;
	String apiURL;
	String URL;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod
	public void setup()
	{
		testbase= new TestBase();
		serviceURL=prop.getProperty("URL");
		apiURL= prop.getProperty("serviceURL");
		URL= serviceURL+apiURL;
	}
	@Test(priority=1)
	public void getApiTest() throws ClientProtocolException, IOException
	{
		restClient = new RestClient();
		System.out.println(URL);
		closeableHttpResponse=restClient.get(URL);
		//Status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code: "+statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not 200");
		
		//JSON String
		String responseString =  EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8"); // UTF-8(character format)
		JSONObject responseJsonobject = new JSONObject(responseString);
		System.out.println("Response JSON From Api"+responseJsonobject);
		
		String perPageValue= TestUtil.getValueByJPath(responseJsonobject, "/per_page");
		System.out.println("Value of perpage is : "+perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue)  ,3);
		
		String totalValue= TestUtil.getValueByJPath(responseJsonobject, "/total");
		System.out.println("Value of totalValue is : "+totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue) ,12);
		
		String id = TestUtil.getValueByJPath(responseJsonobject, "/data[0]/id");
		String first_name = TestUtil.getValueByJPath(responseJsonobject, "/data[0]/first_name");
		String last_name = TestUtil.getValueByJPath(responseJsonobject, "/data[0]/last_name");
		String avatar = TestUtil.getValueByJPath(responseJsonobject, "/data[0]/avatar");
		
		System.out.println("Value of id is "+id);
		System.out.println("Value of first_name is "+first_name);
		System.out.println("Value of last_name is "+last_name);
		System.out.println("Value of avatar is "+avatar);
		
		//All Headers
		Header[] headerArray=closeableHttpResponse.getAllHeaders();
		HashMap<String,String> allHeader = new HashMap<String, String>();
		for(Header header:headerArray)
		{
			allHeader.put(header.getName(), header.getValue());
		}
		System.out.println("Header Array : "+allHeader);
	}
	 
	@Test(priority=2)
	public void getApiTestWithoutHeader() throws ClientProtocolException, IOException
	{
		restClient = new RestClient();
		
		HashMap<String,String> headerMap = new HashMap<String, String>();
		headerMap.put("content-type", "application/json");
	//	headerMap.put("username", "test@amazon.com");
	//	headerMap.put("password", "test123");
	//	headerMap.put("Auth-token", "12345");
		
		System.out.println(URL);
		closeableHttpResponse=restClient.get(URL,headerMap);
		//Status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code: "+statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not 200");
		
		//JSON String
		String responseString =  EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8"); // UTF-8(character format)
		JSONObject responseJsonobject = new JSONObject(responseString);
		System.out.println("Response JSON From Api"+responseJsonobject);
		
		String perPageValue= TestUtil.getValueByJPath(responseJsonobject, "/per_page");
		System.out.println("Value of perpage is : "+perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue)  ,3);
		
		String totalValue= TestUtil.getValueByJPath(responseJsonobject, "/total");
		System.out.println("Value of totalValue is : "+totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue) ,12);
		
		String id = TestUtil.getValueByJPath(responseJsonobject, "/data[0]/id");
		String first_name = TestUtil.getValueByJPath(responseJsonobject, "/data[0]/first_name");
		String last_name = TestUtil.getValueByJPath(responseJsonobject, "/data[0]/last_name");
		String avatar = TestUtil.getValueByJPath(responseJsonobject, "/data[0]/avatar");
		
		System.out.println("Value of id is "+id);
		System.out.println("Value of first_name is "+first_name);
		System.out.println("Value of last_name is "+last_name);
		System.out.println("Value of avatar is "+avatar);
		
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
