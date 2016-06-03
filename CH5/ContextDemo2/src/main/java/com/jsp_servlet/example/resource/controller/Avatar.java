package com.jsp_servlet.example.resource.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author	Lian
 * @time	2016年1月10日 上午1:08:03
 * @desc	使用ServletConfig取出文件信息
 */
@WebServlet("/avatar.view")
public class Avatar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String AVATAR_DIR;

	@Override
	public void init() throws ServletException {
		// 取得ServletContext属性
		AVATAR_DIR = (String) getServletContext().getAttribute("AVATAR_DIR");
	}

	/**
	 * 重写doGet方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter(); 

		out.println("<html>");
		out.println("<head>");
		out.println("<title>头像显示</title>");
		out.println("</head>");
		out.println("<body>");

		// 取得头像路径
		for (String avatar : getServletContext().getResourcePaths(AVATAR_DIR)) {
			avatar = avatar.replaceFirst("/", "");
			// 设置<img>标签的src属性
			out.println("<img src='" + avatar + "'>");
		}

		out.println("</body>");
		out.println("</html>");

		out.close();
	}

}
