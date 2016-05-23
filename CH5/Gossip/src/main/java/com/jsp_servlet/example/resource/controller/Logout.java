package com.jsp_servlet.example.resource.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author	Lian
 * @time	2016年1月10日 下午10:42:27
 * @desc	注销处理
 */
@WebServlet(
	urlPatterns={"/logout.do"},
	initParams={
		@WebInitParam(name = "LOGIN_VIEW", value = "index.html")
	}
)
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String LOGIN_VIEW;

	@Override
	public void init() throws ServletException {
		LOGIN_VIEW = getServletConfig().getInitParameter("LOGIN_VIEW");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(LOGIN_VIEW);
	}
}
