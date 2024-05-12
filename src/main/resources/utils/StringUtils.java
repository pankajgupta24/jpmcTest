package com.qa.jp.utils;

public class StringUtils {

	public static String getRandomEmailId() {
		return "api" + System.currentTimeMillis() + "@api.com";
	}

}
