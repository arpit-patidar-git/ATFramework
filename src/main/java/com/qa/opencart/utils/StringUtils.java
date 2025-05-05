package com.qa.opencart.utils;

public class StringUtils {
	
	public static String randomEmail() {
		String email = "testAutomation" + Math.random()+"@yopmail.com";
		return email;
	}

}
