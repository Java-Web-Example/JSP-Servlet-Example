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
 * @time	2016年1月10日 下午8:11:25
 * @desc
 */
@WebServlet("/guestbook")
public class GuestBook extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 重写doPost方法
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String message = request.getParameter("message");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet GuestBook</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("Name: " + name + "<br>");
		out.println("Message: " + message + "<br>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}
