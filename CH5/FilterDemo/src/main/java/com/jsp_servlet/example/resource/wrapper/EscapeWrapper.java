package com.jsp_servlet.example.resource.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * @author	Lian
 * @time	2016年1月10日 下午4:11:30
 * @desc	实现字符替换过滤器
 */
public class EscapeWrapper extends HttpServletRequestWrapper {

	/**
	 * 必须调用父类构造器, 传入HttpServletRequest实例
	 * @param request
	 */
	public EscapeWrapper(HttpServletRequest request) {
		super(request);
	}

	/**
	 * 重新定义getParameter()方法
	 * @param name
	 * @return
	 */
	@Override
	public String getParameter(String name) {
		String value = getRequest().getParameter(name);
		return StringEscapeUtils.escapeHtml(value);
	}
}
