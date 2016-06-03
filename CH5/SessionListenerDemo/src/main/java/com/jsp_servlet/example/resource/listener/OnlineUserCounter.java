package com.jsp_servlet.example.resource.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author	Lian
 * @time	2016年1月10日 下午2:02:10
 * @desc	统计目前已登录在线人数的功能
 */
@WebListener
public class OnlineUserCounter implements HttpSessionListener {

	private static int counter;

	public static int getCounter() {
		return counter;
	}

	/**
	 * 当Session被创建时, 计数器++
	 * 
	 * @param se
	 */
	public void sessionCreated(HttpSessionEvent se) {
		OnlineUserCounter.counter++;

	}

	/**
	 * 当Session被销毁时, 计数器--
	 * 
	 * @param se
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		OnlineUserCounter.counter--;
	}

}
