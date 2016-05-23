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
 * @time	2015年12月25日 上午2:36:56
 * @desc	路径Servlet
 */
@WebServlet("/servlet/*")
public class PathServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * 重写doGet方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置响应内容类型器
		resp.setContentType("text/html;charset=UTF-8");

		// 取得响应输出对象
		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Hello Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println(req.getRequestURI() + "<br>");
		out.println(req.getContextPath() + "<br>");
		out.println(req.getServletPath() + "<br>");
		out.println(req.getPathInfo() + "<br>");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}
}
