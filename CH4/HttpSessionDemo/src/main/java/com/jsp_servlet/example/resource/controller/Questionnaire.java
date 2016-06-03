package com.jsp_servlet.example.resource.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 	Lian
 * @date	2016年1月6日 下午1:53:45
 * @desc	使用HttpSession代替隐藏域实现问卷调查
 */
@WebServlet("/questionnaire")
public class Questionnaire extends HttpServlet {

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
		processRequest(request, response);
	}

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
		processRequest(request, response);
	}

	/**
	 * 真正处理逻辑的方法
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 取得响应输出对象
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Questionnaire</title>");
		out.println("</head>");
		out.println("<body>");

		// page请求参数决定显示哪一页问卷
		String page = request.getParameter("page");
		out.println("<form action='questionnaire' method='post'>");
		if (page == null) {
			// 第一页问卷题目
			out.println("问题一: <input type='text' name='p1q1'><br>");
			out.println("问题二: <input type='text' name='p2q2'><br>");

			out.println("<input type='submit' name='page' value='下一页'>");
		} else if ("下一页".equals(page)) {
			String p1q1 = request.getParameter("p1q1");
			String p2q2 = request.getParameter("p2q2");

			// 第二页问卷题目
			out.println("问题三: <input type='text' name='p3q3'><br>");

			// 改用HttpSession储存第一页答案
			HttpSession session = request.getSession();
			session.setAttribute("p1q1", p1q1);
			session.setAttribute("p2q2", p2q2);

			// 第一页问卷答案, 使用隐藏域发送答案
			out.println("<input type='submit' name='page' value='完成'>");
		} else if ("完成".equals(page)) {
			// 改用HttpSession取得第一页答案
			HttpSession session = request.getSession();

			out.println(session.getAttribute("p1q1") + "<br>");
			out.println(session.getAttribute("p2q2") + "<br>");
			out.println(request.getParameter("p3q3") + "<br>");
		}
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}

}
