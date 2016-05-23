package com.jsp_servlet.example.resource.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.jsp_servlet.example.resource.model.UserService;

/**
 * @author	Lian
 * @time	2016年1月10日 下午10:31:06
 * @desc	
 */
@WebListener
public class GossipListener implements ServletContextListener {

	/**
	 * 将UserService设置成为一个全局属性
	 * @param sce
	 */
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		String USERS = context.getInitParameter("USERS");
		context.setAttribute("userService", new UserService(USERS));
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}

}
