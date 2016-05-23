package com.jsp_servlet.example.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用getReader()读取请求的Body内容
 *
 * @author	Lian
 * @time	2016年4月25日
 * @since	1.0
 */
@WebServlet("/body.view")
public class BodyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 重写doPost方法. 实现获取Body内容
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String body = readBody(request);
		// 取得响应输出对象
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Hello Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println(body);
		out.println("</body>");
		out.println("</html>");

		out.close();
	}

	/**
	 * 获取Body内容的具体实现
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	private String readBody(HttpServletRequest request) throws IOException {
		// 取得BufferReader对象
		BufferedReader reader = request.getReader();
		String input = null;
		String requestBody = "";
		while ((input = reader.readLine()) != null) {
			requestBody += input + "<br>";
		}
		return requestBody;
	}

}
