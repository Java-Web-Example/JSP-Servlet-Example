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
 * @time	2016年1月6日 上午2:54:30
 * @desc	URL Rewriting
 */
@WebServlet("/search")
public class Search extends HttpServlet {

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		// 取得响应输出对象
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Search Result</title>");
		out.println("</head>");
		out.println("<body>");
		String start = request.getParameter("start");

		if (start == null) {
			start = "1";
		}

		int count = Integer.parseInt(start);
		int begin = 10 * count - 9;
		int end = 10 * count;
		out.println("第 " + begin + " 到 " + end + " 搜索结果<br>");
		out.println("<ul>");
		for(int i=1; i<10; i++) {
			out.println("<li>搜索结果" + i + "</li>");
		}
		out.println("</ul>");
		for (int i = 1; i <= 10; i++) {
			if (i == count) {
				out.println(i);
				continue;
			}
			// 使用URL重写保留分页信息
			out.println("<a href='search?start=" + i + "'>" + i + "</a>");
		}
		out.println("</body>");
		out.println("</html>");

		out.close();
	}
}
