package com.ApiTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Delete_Employee_Record extends BaseClass {

	RequestSpecification httpRequest;
	Response response;
		
	@BeforeClass
	void deleteEmployee() throws InterruptedException
	{
		logger.info("*********Started TC005_Delete_Employee_Record **********");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		response = httpRequest.request(Method.GET, "/employees");
				
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
			 
		//Capture id
		String empID =jsonPathEvaluator.get("[2].id");
		System.out.println(empID);
		response = httpRequest.request(Method.DELETE, "/delete/"+empID); //Pass ID to delete record
		
		Thread.sleep(3000);
	}
	
	@Test(priority=1)
	void checkResposeBody()
	{
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);

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
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}

	@Test(priority=5)
	void checkserverType()
	{
		String serverType = response.header("Server");
		Assert.assertEquals(serverType, "nginx/1.16.0");
	}

	@Test(priority=6)
	void checkcontentEncoding()
	{
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, "gzip");

	}

	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC005_Delete_Employee_Record **********");
	}
}
