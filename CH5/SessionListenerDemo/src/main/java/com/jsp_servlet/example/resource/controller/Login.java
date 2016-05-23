package com.jsp_servlet.example.resource.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author	Lian
 * @time	2016年1月10日 下午1:56:05
 * @desc	登录验证
 */
@WebServlet("/login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, String> users;

	public Login() {
		users = new HashMap<String, String>();
		users.put("caterpillar", "123456");
		users.put("momor", "98765");
		users.put("hamimi", "13579");
	}

	/**
	 * 重写doPost方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String passwd = request.getParameter("passwd");

		String page = "form.html";
		if (users.containsKey(name) && users.get(name).equals(passwd)) {
			request.getSession().setAttribute("user", name);
			page = "welcome.view";
		}
		response.sendRedirect(page);
	}

}
