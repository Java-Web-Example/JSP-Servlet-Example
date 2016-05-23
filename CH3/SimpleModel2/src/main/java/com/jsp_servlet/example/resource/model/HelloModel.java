package com.jsp_servlet.example.resource.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author	Lian
 * @time	2016年1月4日 下午11:55:24
 * @desc	实体类
 */
public class HelloModel {
	private Map<String, String> messages = new HashMap<String, String>();

	public HelloModel() {
		messages.put("caterpillar", "Hello");
		messages.put("Justin", "Welcome");
		messages.put("Motor", "Hi");
	}

	public String doHello(String user) {
		String message = messages.get(user);
		return message + ", " + user + "!";
	}
}
