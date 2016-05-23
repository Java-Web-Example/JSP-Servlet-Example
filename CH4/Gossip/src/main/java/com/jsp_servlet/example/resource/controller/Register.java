package com.jsp_servlet.example.resource.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author	Lian
 * @time	2016年1月7日 上午2:06:12
 * @desc
 */
@WebServlet("/register.do")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String USERS = "C:/GitHub/JSP-Servlet-Example/CH4/Gossip/users";
	private final String SUCCESS_VIEW = "success.view";
	private final String ERROR_VIEW = "error.view";

	/**
	 * 重写doPost方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmedPasswd = request.getParameter("confirmedPasswd");

		List<String> errors = new ArrayList<String>();
		if (isInvalidEmail(email)) {
			errors.add("未填写邮件或邮件格式不正确");
		}
		if (isInvalidUsername(username)) {
			errors.add("使用者名称为空或已存在");
		}
		if (isInvalidPassword(password, confirmedPasswd)) {
			errors.add("请确认密码符合格式并再度确认密码");
		}
		String resultPage = ERROR_VIEW;
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
		} else {
			resultPage = SUCCESS_VIEW;
			createUserData(email, username, password);
		}

		request.getRequestDispatcher(resultPage).forward(request, response);
	}

	private boolean isInvalidEmail(String email) {
		return email == null || !email.matches("^[_a-z0-9-]+([.]" + "[_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");
	}

	private boolean isInvalidUsername(String username) {
		for (String file : new File(USERS).list()) {
			if (file.equals(username)) {
				return true;
			}
		}
		return false;
	}

	private boolean isInvalidPassword(String password, String confirmedPasswd) {
		return password == null || password.length() < 6 || password.length() > 16 || !password.equals(confirmedPasswd);
	}

	private void createUserData(String email, String username, String password) throws IOException {
		File userhome = new File(USERS + "/" + username);
		userhome.mkdir();
		BufferedWriter writer = new BufferedWriter(new FileWriter(userhome + "/profile"));
		writer.write(email + "\t" + password);
		writer.close();
	}

}
