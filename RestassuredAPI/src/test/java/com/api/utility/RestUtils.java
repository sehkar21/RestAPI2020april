package com.api.utility;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	public static String empname() {
		String generatedstring = RandomStringUtils.randomAlphabetic(7);
		return("john" + generatedstring);
	}
	
	public static String empsal() {
		String generatedsalary = RandomStringUtils.randomNumeric(5);
		return (generatedsalary);
	}
	
	public static String empage() {
		String generatedAge = RandomStringUtils.randomNumeric(2);
		return(generatedAge);
	}
	
	
}
