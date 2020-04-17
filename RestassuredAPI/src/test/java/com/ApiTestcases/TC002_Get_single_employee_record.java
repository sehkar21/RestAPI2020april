package com.ApiTestcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_single_employee_record extends TC001_Get_All_Employees {
	RequestSpecification httpRequest;
	Response response;
		
	@BeforeClass
	void getEmployeeData() throws InterruptedException
	{
		logger.info("*********Started TC002_Get_Single_Employee_Record **********");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employee/"+empid);
		
		Thread.sleep(7000);
	}
	@Test(priority=1)
	public void CheckresponseBody() {
		logger.info("*************CheckresponseBody**************");
	String getbody = 	response.getBody().asString();
	logger.info("statuscode is: "+ getbody);
	Assert.assertTrue(getbody!=null);
	}
	
	@Test(priority=2)
	public void checkresponsecode() {
		logger.info("*************checkresponsecode**************");
		int statuscode = response.getStatusCode();
		logger.info("status code is :" + statuscode);
		Assert.assertEquals(statuscode, 200);
	}
	
	@Test(priority=3)
	public void CheckResponseTime() {
		logger.info("*************CheckResponseTime**************");
		long responsetime = response.getTime();
		logger.info("The response time : " + responsetime);
		Assert.assertTrue(responsetime<10000);
	}
	
	@Test(priority=4)
	public void checkstatusline() {
		String statusline =response.getStatusLine();
		System.out.println("The status line is : " + statusline);
		logger.info("The status line is :" + statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
	}
	
	@Test(priority=5)
	public void checkcontenttype() {
	String contenttype =	response.header("content-type");
		System.out.println("The content type is :" + contenttype);
		logger.info("*************Checking Content type *********");
		Assert.assertEquals(contenttype, "text/html; charset=UTF-8");
	
	}
	
	@Test(priority=6)
	public void checkservertype() {
		logger.info("**********checking server type***********");
		String servertype = response.header("server");
		System.out.println("The server type is : " + servertype);
		Assert.assertEquals(servertype, "nginx/1.16.0");
	}
	
	@Test(priority=7)
	public void checkcontentencoding() {
		logger.info("************checking content encoding**********");
		String Encoding = response.header("Content-Encoding");
		System.out.println(Encoding);
		Assert.assertEquals(Encoding, "gzip");
	}
	
	@Test(priority=8)
	public void checkcontentlength() {
		logger.info("************checking content Length**********");
		String Length = response.header("Content-Length");
		System.out.println(Length);
		if(Integer.parseInt(Length)<100)
			logger.warn("**content length is less than 100 **");
		Assert.assertTrue(Integer.parseInt(Length)>100);
	
	}
	
	/*
	 * @Test(priority=9) public void checkcookies() {
	 * logger.info("*******cookies**********"); String cookie =
	 * response.getCookie("PHPSESSID"); System.out.println(cookie);
	 * Assert.assertEquals(cookie, "c32ccfa1f9ff0087cdc7eb25d07a7f7f"); }
	 */
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC002_Get_Single_Employee_Record **********");
	}
	
	
	
	
	
	
	}


