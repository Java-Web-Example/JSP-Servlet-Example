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
 * @time	2016年1月4日 下午11:32:10
 * @desc	被包含的Servlet
 */
@WebServlet("/other.view")
public class OtherServlet extends HttpServlet {

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
		// 得到相应输出对象
		PrintWriter out = response.getWriter();
		out.println("other do one...");
	}
}
