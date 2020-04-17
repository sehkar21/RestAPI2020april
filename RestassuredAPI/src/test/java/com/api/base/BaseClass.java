package com.api.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	public static RequestSpecification httprrequest;
	public static Response response;
	public  String empid ="1"; //Hard coded - Input for Get details of Single Employee & update employee
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		logger = Logger.getLogger("EmployeeApi");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
	
}
