package com.jsp_servlet.example.resource;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author	Lian
 * @time	2016年1月6日 上午2:28:48
 * @desc	用户访问首页时的处理
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
		response.setContentType("text/html;charset=UTF-8");

		// 请求中若没有"user"属性, 表示用户尚未登录
		if (request.getAttribute("user") == null) {
			response.sendRedirect("login.html");
		}

		// 得到响应输出对象
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet User</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>" + request.getAttribute("user") + "已登录</h1>");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}
}
