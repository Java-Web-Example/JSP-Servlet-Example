package com.jsp_servlet.example.resource.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp_servlet.example.resource.listener.OnlineUserCounter;

/**
 * @author	Lian
 * @time	2016年1月10日 下午2:06:19
 * @desc	显示界面
 */
@WebServlet("/welcome.view")
public class Welcom extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 重写doGet方法
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		out.println("<html>");
		out.println("<head>");
		out.println("<title>欢迎</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>在线人数: " + OnlineUserCounter.getCounter() + " 人</h1>");
		if (session != null) {
			String user = (String) session.getAttribute("user");
			out.println("<h1>欢迎: " + user + "</h1>");
			out.println("<a href='logout.do?user=" + user + "'>注销</a>");
		}
		out.println("</body>");
		out.println("</html>");

		out.close();
	}

}
