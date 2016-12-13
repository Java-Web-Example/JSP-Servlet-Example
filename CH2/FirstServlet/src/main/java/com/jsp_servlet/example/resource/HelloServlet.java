package com.jsp_servlet.example.resource;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自己定义的HelloServlet
 * 继承HttpServlet
 *
 * @author	Lian
 * @date	2016年3月16日
 * @since	1.0
 */
@WebServlet("/hello.view")
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 重新定义doGet
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置响应内容类型器. 代表对客户端响应的响应类型和编码方式
		response.setContentType("text/html;charset=UTF-8");

		// 取得响应输出对象
		PrintWriter out = response.getWriter();

		// 取得 请求参数
		String name = request.getParameter("name");

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Hello Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1> Hello! " + name + " !</h1>");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}

}
