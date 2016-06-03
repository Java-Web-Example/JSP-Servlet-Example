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
 * @desc	删除消息处理
 */
@WebServlet(
	urlPatterns={"/delete.do"},
	initParams={
		@WebInitParam(name = "SUCCESS_VIEW", value = "member.view")
	}
)
public class Delete extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String SUCCESS_VIEW;

	@Override
	public void init() throws ServletException {
		SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) request.getSession().getAttribute("login");
		String message = request.getParameter("message");
		UserService userService = (UserService) getServletContext().getAttribute("userService");
		userService.deleteMessage(username, message);
		response.sendRedirect(SUCCESS_VIEW);
	}
}
