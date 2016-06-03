package com.jsp_servlet.example.resource.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp_servlet.example.resource.model.UserService;

/**
 * @author	Lian
 * @time	2016年1月10日 下午10:42:27
 * @desc	信息处理
 */
@WebServlet(
	urlPatterns={"/message.do"},
	initParams={
		@WebInitParam(name = "SUCCESS_VIEW", value = "member.view"),
		@WebInitParam(name = "ERROR_VIEW", value = "member.view")
}
)
public class Message extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String SUCCESS_VIEW;
	private String ERROR_VIEW;

	@Override
	public void init() throws ServletException {
		SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
		ERROR_VIEW = getServletConfig().getInitParameter("ERROR_VIEW");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String blabla = request.getParameter("blabla");
		if (blabla != null && blabla.length() != 0) {
			if (blabla.length() < 140) {
				String username = (String) request.getSession().getAttribute("login");
				UserService userService = (UserService) getServletContext().getAttribute("userService");
				userService.addMessage(username, blabla);
				response.sendRedirect(SUCCESS_VIEW);
			} else {
				request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
			}
		} else {
			response.sendRedirect(ERROR_VIEW);
		}

	}
}
