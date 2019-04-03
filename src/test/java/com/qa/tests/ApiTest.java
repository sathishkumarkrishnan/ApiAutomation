package com.qa.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class ApiTest extends TestBase{

	TestBase testbase;
	String serviceURL;
	String apiURL;
	String URL;
	RestClient restClient;
	
	@BeforeMethod
	public void setup()
	{
		testbase= new TestBase();
		serviceURL=prop.getProperty("URL");
		apiURL= prop.getProperty("serviceURL");
		URL= serviceURL+apiURL;
	}
	@Test
	public void getApiTest() throws ClientProtocolException, IOException
	{
		restClient = new RestClient();
		System.out.println(URL);
		restClient.get(URL);
	}
}
