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
 * @time	2016年1月5日 上午1:51:28
 * @desc	注册控制器
 */
@WebServlet("/login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String USERS = "C:/Github/JSP-Servlet-Example/CH3/Gossip/src/main/resources/users";
	private final String SUCCESS_VIEW = "member.view";
	private final String ERROR_VIEW = "index.html";
	/**
	 * 重写doPost方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 取得请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// 检查用户名称与密码是否符合, 若是则转发会员页面; 否则重定向回首页
		if (checkLogin(username, password)) {
			request.getRequestDispatcher(SUCCESS_VIEW).forward(request, response);
		} else {
			response.sendRedirect(ERROR_VIEW);
		}

	}

	/**
	 * 验证用户名和密码是否正确
	 * @param username
	 * @param password
	 * @return
	 * @throws IOException
	 */
	private boolean checkLogin(String username, String password) throws IOException {
		if(username != null && password != null) {
			for(String file : new File(USERS).list()) {
				if(file.equals(username)) {
					BufferedReader reader = new BufferedReader(new FileReader(USERS + "/" + file + "/profile"));
					String passwd = reader.readLine().split("\t")[1];
					if(passwd.equals(password)) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
