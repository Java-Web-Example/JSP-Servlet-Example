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
 * @time	2016年1月5日 上午1:19:54
 * @desc	处理中文字符
 */
@WebServlet("/pet")
public class Pet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 重写doPost方法
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置请求对象字符串编码
		request.setCharacterEncoding("UTF-8");

		// 设置响应内容类型
		response.setContentType("text/html; charset=UTF-8");

		// 取得输出对象
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>感谢填写</title>");
		out.println("</head>");
		out.println("<body>");
		// 取得请求参数值
		out.println("联系人: <a href='mailto: " + request.getParameter("email") + "'>" + request.getParameter("user") + "</a>");
		out.println("<br>喜爱的宠物类型");
		out.println("<ul>");

		// 取得复选项请求参数值
		for (String type : request.getParameterValues("type")) {
			out.println("<li>" + type + "</li>");
		}
		out.println("</ul>");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}

}
