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
 * @time	2016年1月5日 上午1:51:28
 * @desc	注册控制器
 */
@WebServlet("/register.do")
public class Resiter extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final String USERS = "C:/Github/Java-Web-Example/JSP-Servlet-Example/CH3/Gossip/src/main/resources/users";
	private final String SUCCESS_VIEW = "success.view";
	private final String ERROR_VIEW = "error.view";

	/**
	 * 重写doPost方法
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 取得请求参数
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmedPasswd = request.getParameter("confirmedPasswd");

		// 验证请求参数
		List<String> errors = new ArrayList<String>();
		if (isInvalidEmail(email)) {
			errors.add("未填写邮件或邮件格式不正确!");
		}
		if (isInvalidUsername(username)) {
			errors.add("用户名称为空或已存在!");
		}
		if (isInvalidPassword(password, confirmedPasswd)) {
			errors.add("请确认密码符合格式并再次确认密码!");
		}

		// 窗体验证出错, 设置手机错误的List为请求属性
		String resultPage = ERROR_VIEW;
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
		} else {
			resultPage = SUCCESS_VIEW;
			// 创建用户资料
			createUserDate(email, username, password);
		}

		request.getRequestDispatcher(resultPage).forward(request, response);
	}

	/**
	 * 验证邮箱的有效性
	 * 
	 * @param email
	 * @return
	 */
	private boolean isInvalidEmail(String email) {
		return email == null || !email.matches("^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");
	}

	/**
	 * 检查用户资料夹是否创建来确认用户是否已注册
	 * 
	 * @param username
	 * @return
	 */
	private boolean isInvalidUsername(String username) {
		for(String file : new File(USERS).list()) {
			if(file.equals(username)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 验证密码的有效性
	 * 
	 * @param password
	 * @param confirmedPasswd
	 * @return
	 */
	private boolean isInvalidPassword(String password, String confirmedPasswd) {
		return password == null || 
				password.length() < 6 ||
				password.length() > 16 ||
				!password.equals(confirmedPasswd);
	}

	/**
	 * 创建用户资料时, 在profile中存储邮件和密码
	 * 
	 * @param email
	 * @param username
	 * @param password
	 * @throws IOException 
	 */
	private void createUserDate(String email, String username, String password) throws IOException {
		File userHome = new File(USERS + "/" + username);
		userHome.mkdir();
		BufferedWriter writer = new BufferedWriter(new FileWriter(userHome + "/profile"));
		writer.write(email + "\t" + password);
		writer.close();
	}
}
