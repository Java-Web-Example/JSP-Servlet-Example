package com.jsp_servlet.example.resource.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp_servlet.example.resource.model.HelloModel;

/**
 * @author	Lian
 * @time	2016年1月4日 下午11:54:38
 * @desc	控制器
 */
@WebServlet("/hello.do")
public class HelloController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private HelloModel model = new HelloModel();

	/**
	 * 重写doGet方法
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 收集请求参数
		String name = request.getParameter("user");

		// 委托HelloModel对象处理
		String message = model.doHello(name);

		// 将结果信息设置至请求对象成为属性
		request.setAttribute("message", message);

		// 转发给hello.view进行响应
		request.getRequestDispatcher("hello.view").forward(request, response);
	}
}
