package com.jsp_servlet.example.resource.entity;

/**
 * @author	Lian
 * @time	2016年1月12日 上午12:52:31
 * @desc	javabean, 测试jsp:useBean
 */
public class User {
	private String name;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValid() {
		return "caterpillar".equals(name) && "123456".equals(password);
	}
}
