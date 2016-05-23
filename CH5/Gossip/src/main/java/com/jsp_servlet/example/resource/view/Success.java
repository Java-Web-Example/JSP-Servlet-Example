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
 * @time	2016年1月10日 下午10:49:48
 * @desc	
 */
@WebServlet("/success.view")
public class Success extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
		out.println("<html>");
		out.println("<head>");
		out.println("  <meta content='text/html; charset=UTF-8' http-equiv='content-type'>");
		out.println("  <title>会员注册成功</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>会员 " + request.getParameter("username") + " 注册成功</h1>");
		out.println("<a href='index.html'>回首页登入</a>");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}
}
