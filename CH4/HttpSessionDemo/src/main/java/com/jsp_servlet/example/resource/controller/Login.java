package com.jsp_servlet.example.resource.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 	Lian
 * @date	2016年1月6日 下午5:16:35
 * @desc	登录
 */
@WebServlet("/login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 重写doPost方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String passwd = request.getParameter("passwd");
		if("caterpillar".equals(user) && "123456".equals(passwd)) {
			// 设定登录字符
			request.getSession().setAttribute("login", user);
			request.getRequestDispatcher("user.view").forward(request, response);
		} else {
			response.sendRedirect("login.html");
		}
	}
}
