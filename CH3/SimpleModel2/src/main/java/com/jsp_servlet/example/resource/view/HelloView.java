package com.jsp_servlet.example.resource.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author	Lian
 * @time	2016年1月5日 上午12:04:15
 * @desc	响应对象
 */
@WebServlet("/hello.view")
public class HelloView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String htmlTemplate = 
			"<html>" +
			"  <head>" +
			"    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>" +
			"    <title>%s</title>" +
			"  </head>" +
			"  <body>" +
			"    <h1>%s</h1>" +
			"  </body>" +
			"</html>";

	/**
	 * 重写doGet方法进行相应
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 取得请求参数
		String user = request.getParameter("user");

		// 取得请求属性
		String message = (String) request.getAttribute("message");

		// 产生HTML结果
		String html = String.format(htmlTemplate, user, message);

		// 输出HTML结果
		response.getWriter().print(html);
	}
}
