package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostApiTest extends TestBase{
	
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
	
	@Test
	public void postApiTest() throws JsonGenerationException, JsonMappingException, IOException
	{
		restClient= new RestClient();
		HashMap<String,String> headerMap= new HashMap<String, String>();
		headerMap.put("content-type", "application/json");		
		//jacksonApi
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("morpheus","leader");
		mapper.writeValue(new File(System.getProperty("user.dir")+"/src/main/java/com/qa/data/users.json"), users);
		String userJsonString = mapper.writeValueAsString(users);
		System.out.println("user json string : "+userJsonString);
			
	}
}
