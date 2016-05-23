package com.jsp_servlet.example.resource;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 演示获取Header中信息的例子
 *
 * @author	Lian
 * @time	2016年3月18日
 * @since	1.0
 */
@WebServlet("/header.view")
public class HeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 重写doGet方法
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 取得响应输出对象
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Header Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		// 取得应用环境路径
		out.println("<h1> HeaderSevlet at " + request.getContextPath() + " !</h1>");
		// 取得所有标头名称
		Enumeration<String> names = request.getHeaderNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			// 取得标头值
			out.println(name + ": " + request.getHeader(name) + "<br>");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
