package com.jsp_servlet.example.resource.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author	Lian
 * @time	2016年1月7日 上午1:46:11
 * @desc	删除消息
 */
@WebServlet("/delete.do")
public class Delete extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final String USERS = "C:/GitHub/Java-Web-Example/JSP-Servlet-Example/CH4/Gossip/users";
	private final String LOGIN_VIEW = "index.html";
	private final String SUCCESS_VIEW = "member.view";

	/**
	 * 重写doGet方法
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 若无'login'属性, 直接重定向到登录网页
		if (request.getSession().getAttribute("login") == null) {
			response.sendRedirect(LOGIN_VIEW);
		}

		String username = (String) request.getSession().getAttribute("login");
		String message = request.getParameter("message");
		File file = new File(USERS + "/" + username + "/" + message + ".txt");
		if (file.exists()) {
			file.delete();
		}
		response.sendRedirect(SUCCESS_VIEW);
	}
}
