package com.jsp_servlet.example.resource.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author	Lian
 * @time	2016年1月5日 上午2:15:01
 * @desc	注册成功展示界面
 */
@WebServlet("/success.view")
public class Success extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 重写doPost方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置响应编码
		response.setContentType("text/html; charset=UTF-8");

		// 取得响应输出对象
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<meta content='text/html; charset=UTF-8' http-equiv='content-type'>");
		out.println("<title>新增会员成功</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>会员" + request.getParameter("username") + "注册成功</h1>");
		out.println("<a href='index.html'>回首页登录</a>");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}

}
