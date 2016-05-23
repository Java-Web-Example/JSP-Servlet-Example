package com.jsp_servlet.example.resource.controller;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author	Lian
 * @time	2016年1月7日 上午1:28:26
 * @desc	处理消息
 */
@WebServlet("/message.do")
public class Message extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String USERS = "C:/GitHub/JSP-Servlet-Example/CH4/Gossip/users";
	private final String LOGIN_VIEW = "index.html";
	private final String SUCCESS_VIEW = "member.view";
	private final String ERROR_VIEW = "member.view";

	/**
	 * 重写doPost方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 若无'login'属性, 直接重定向到登录网页
		if(request.getSession().getAttribute("login") == null) {
			response.sendRedirect(LOGIN_VIEW);
		}

		request.setCharacterEncoding("UTF-8");
		String blabla = request.getParameter("blabla");
		if(blabla != null && blabla.length() != 0) {
			// 如果信息在140字内, 保存到用户目录中的文档; 否则转发会员网页
			if(blabla.length() < 140) {
				String username = (String) request.getSession().getAttribute("login");
				addMessage(username, blabla);
				response.sendRedirect(SUCCESS_VIEW);
			} else {
				request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
			}
		} else {
			//如果没有信息, 重定向到会员网页
			response.sendRedirect(ERROR_VIEW);
		}
	}

	/**
	 * 新增消息
	 * @param username
	 * @param blabla
	 * @throws IOException 
	 */
	private void addMessage(String username, String blabla) throws IOException {
		String file = USERS + "/" + username + "/" + new Date().getTime() + ".txt";
		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(
						new FileOutputStream(file), "UTF-8"));
		writer.write(blabla);
		writer.close();
	}
}
