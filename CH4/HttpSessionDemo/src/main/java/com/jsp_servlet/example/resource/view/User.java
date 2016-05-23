package com.jsp_servlet.example.resource.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 	Lian
 * @date	2016年1月6日 下午7:13:59
 * @desc	用户登陆后的展示页面
 */
@WebServlet("/user.view")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 重写doGet方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		// 如果无法取得登录字符, 重定向到登录窗口
		if (session.getAttribute("login") == null) {
			response.sendRedirect("login.html");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			// 得到响应输出对象
			PrintWriter out = response.getWriter();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>欢迎 " + session.getAttribute("login") + "</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>用户 " + session.getAttribute("login") + "已登录</h1><br><br>");
			// 执行注销的超链接
			out.println("<a href='logout.view'>注销</a>");
			out.println("</body>");
			out.println("</html>");

			out.close();
		}
	}
}
