package com.jsp_servlet.example.resource.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author	Lian
 * @time	2016年1月7日 上午12:56:52
 * @desc	登录模块处理
 */
@WebServlet("/login.do")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final String USERS = "C:/GitHub/Java-Web-Example/JSP-Servlet-Example/CH4/Gossip/users";
	private final String SUCCESS_VIEW = "member.view";
	private final String ERROR_VIEW = "index.html";

	/**
	 * 重写doPost方法
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String page = ERROR_VIEW;

		if (checkLogin(username, password)) {
			request.getSession().setAttribute("login", username);
			page = SUCCESS_VIEW;
		}

		response.sendRedirect(page);
	}

	/**
	 * 检查用户是否已经登录过
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	private boolean checkLogin(String username, String password) throws IOException {
		if (username != null && password != null) {
			for (String file : new File(USERS).list()) {
				if (file.equals(username)) {
					BufferedReader reader = new BufferedReader(new FileReader(USERS + "/" + file + "/profile"));
					String passwd = reader.readLine().split("\t")[1];
					if (passwd.equals(password)) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
