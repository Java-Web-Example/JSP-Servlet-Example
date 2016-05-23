package com.jsp_servlet.example.resource;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author	Lian
 * @time	2016年1月6日 上午2:28:48
 * @desc	用户访问首页时的处理
 */
@WebServlet("/index.do")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 重写doGet方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * 重写doPost方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * 真正处理逻辑的方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 取得Cookie
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				String name = cookie.getName();
				String value = cookie.getValue();
				// 如果这个cookie的name与value与预期的相匹配, 允许自动登录
				if("user".equals(name) && "caterpillar".equals(value)) {
					request.setAttribute(name, value);
					request.getRequestDispatcher("/user.view").forward(request, response);
					return;
				}
			}
		}

		// 如果没有对应的Cookie名称与数值, 表示尚未允许自动登录, 重定向到登录窗体
		response.sendRedirect("login.html");
	}
}
