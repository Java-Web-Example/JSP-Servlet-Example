package com.jsp_servlet.example.resource;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author	Lian
 * @time	2016年1月4日 下午11:32:10
 * @desc	测试RequestDispatcher的include方法--可以将另一个Servlet的操作流程包括至目前Servlet操作流程之中
 */
@WebServlet("/some.view")
public class SomeServlet extends HttpServlet {

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
		out.println("some do one");

		// 得到RequestDispatcher接口的实现对象实例
		RequestDispatcher dispatcher = request.getRequestDispatcher("other.view");
		dispatcher.include(request, response);

		out.println("some do two");
		out.close();
	}
}
