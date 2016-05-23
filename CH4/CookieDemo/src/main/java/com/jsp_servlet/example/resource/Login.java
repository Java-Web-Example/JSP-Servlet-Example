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
 * @desc	登录处理
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
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 取得登录参数
		String user = request.getParameter("user");
		String passwd = request.getParameter("passwd");

		if ("caterpillar".equals(user) && "123456".equals(passwd)) {
			String login = request.getParameter("login");
			// login参数值为auto, 表示自动登录
			if ("auto".equals(login)) {
				// 创建Cookie, 设定一星期内有效, 并新增至响应之中
				Cookie cookie = new Cookie("user", "caterpillar");
				cookie.setMaxAge(7 * 24 * 60 * 60);
				response.addCookie(cookie);
			}
			request.setAttribute("user", user);
			request.getRequestDispatcher("user.view").forward(request, response);
		} else {
			response.sendRedirect("login.html");
		}
	}
}
