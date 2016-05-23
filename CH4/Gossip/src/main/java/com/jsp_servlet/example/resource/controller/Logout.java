package com.jsp_servlet.example.resource.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author	Lian
 * @time	2016年1月7日 上午2:03:23
 * @desc	注销功能
 */
@WebServlet("/logout.do")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String LOGIN_VIEW = "index.html";

	/**
	 * 重写doGet方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("login") != null) {
			request.getSession().invalidate();
		}
		response.sendRedirect(LOGIN_VIEW);
	}
}
