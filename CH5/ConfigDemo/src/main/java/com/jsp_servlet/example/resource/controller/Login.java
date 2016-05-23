package com.jsp_servlet.example.resource.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 	Lian
 * @date	2016年1月9日 下午5:18:31
 * @desc	ServletConfig的使用方法
 */
@WebServlet(
	name="Login",
	urlPatterns={"/login.do"},
	initParams = {
		@WebInitParam(name="SUCCESS", value="success.view"),
		@WebInitParam(name="ERROR", value="error.view")
	})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String SUCCESS_VIEW;
	private String ERROR_VIEW;

	/**
	 * 取得初始化参数
	 * @throws ServletException
	 */
	@Override
	public void init() throws ServletException {
		SUCCESS_VIEW = getInitParameter("SUCCESS");
		ERROR_VIEW = getInitParameter("ERROR");
	}

	/**
	 * 重写doPost方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String name = request.getParameter("name");
		String passwd = request.getParameter("passwd");
		if("caterpillar".equals(name) && "123456".equals(passwd)) {
			// 登陆成功
			request.getRequestDispatcher(SUCCESS_VIEW).forward(request, response);
		} else {
			// 登录失败
			request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
		}
	}

}
