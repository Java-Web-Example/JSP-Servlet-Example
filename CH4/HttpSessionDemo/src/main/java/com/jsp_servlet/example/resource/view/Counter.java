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
 * @date	2016年1月6日 下午9:48:27
 * @desc	使用URL Rewriting 代替Session
 */
@WebServlet("/counter")
public class Counter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 重写doPost方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		int count = 0;
		HttpSession session = request.getSession();
		if (session.getAttribute("count") != null) {
			Integer c = (Integer) session.getAttribute("count");
			count = c + 1;
		}
		session.setAttribute("count", count);
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet Count</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Servlet Count " + count + "</h1>");
		out.println("<a href='" + response.encodeURL("counter") + "'>递增</a>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}
