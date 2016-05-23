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
@WebServlet("/logout.view")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 重写doGet方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("login");
		// 使HttpSession失效
		session.invalidate();

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>登出</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>使用者 " + user + " 已登出</h1>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}
