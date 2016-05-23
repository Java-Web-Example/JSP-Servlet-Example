package com.jsp_servlet.example.resource.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author	Lian
 * @time	2016年1月10日 上午1:30:01
 * @desc	实现ServletContextListener接口
 * 			这样容器就会在启动时加载并运行对应的方法
 */
@WebListener
public class ContextParameterReader implements ServletContextListener {

	/**
	 * Servlet Destroyed
	 * @param sce
	 */
	public void contextDestroyed(ServletContextEvent sce) {
	}

	/**
	 * Servlet Initialized
	 * @param sce
	 */
	public void contextInitialized(ServletContextEvent sce) {
		// 取得ServletContext
		ServletContext context = sce.getServletContext();

		// 取得初始化参数
		String avatars = context.getInitParameter("AVATAR");

		// 设置ServletContext属性
		context.setAttribute("AVATAR_DIR", avatars);
	}

}
