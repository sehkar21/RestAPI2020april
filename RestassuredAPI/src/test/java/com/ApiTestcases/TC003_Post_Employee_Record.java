package com.ApiTestcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.base.BaseClass;
import com.api.utility.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_Post_Employee_Record extends BaseClass{

	RequestSpecification httprequest;
	Response response;
	
	String empname = RestUtils.empname();
	String empsal = RestUtils.empsal();
	String empage = RestUtils.empage();
	
	@BeforeClass
	public void createemployee() throws InterruptedException {
		logger.info("*******Starting TC003 create Employee ************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httprequest = RestAssured.given();
		
		// JSONObject is a class that represents a simple JSON. We can add Key-Value pairs using the put method
		//{"name":"John123X","salary":"123","age":"23"}
		
		JSONObject json = new JSONObject();
		json.put("name", empname);
		json.put("salary", empsal);
		json.put("age", empage);
		
		// Add a header stating the Request body is a JSON
		httprequest.header("Content-Type", "application/json");
		
		// Add the Json to the body of the request
		httprequest.body(json.toJSONString());
		
		response = 	httprequest.request(Method.POST,"/create");
		
		Thread.sleep(5000);	
	}
	
	@Test(priority=1)
	public void checkresponsebody() {
			String responsebody	= response.getBody().asString();
			Assert.assertEquals(responsebody.contains(empname), true);
			Assert.assertEquals(responsebody.contains(empsal), true);
			Assert.assertEquals(responsebody.contains(empage), true);		
			System.out.println(responsebody);
	}
	@Test(priority=2)
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode(); // Gettng status code
		Assert.assertEquals(statusCode, 200);
	}
		
	@Test(priority=3)
	void checkstatusLine()
	{
		String statusLine = response.getStatusLine(); // Gettng status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	@Test(priority=4)
	void checkContentType()
	{
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json;charset=utf-8");
	}

	@Test(priority=5)
	void checkserverType()
	{
		String serverType = response.header("Server");
		Assert.assertEquals(serverType, "nginx/1.16.0");
	}

	/*
	 * @Test(priority=6) void checkcontentEncoding() { String Encoding =
	 * response.header("Content-Encoding"); System.out.println(Encoding);
	 * Assert.assertEquals(Encoding, "gzip");
	 * 
	 * }
	 */
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC003_Post_Employee_Record **********");
	}

	

}
