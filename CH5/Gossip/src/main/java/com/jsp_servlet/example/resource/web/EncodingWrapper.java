package com.jsp_servlet.example.resource.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author	Lian
 * @time	2016年1月10日 下午4:26:29
 * @desc	
 */
public class EncodingWrapper extends HttpServletRequestWrapper {

	private String ENCODING;

	/**
	 * 必须调用父类构造器, 传入HttpServletRequest实例
	 * @param request
	 */
	public EncodingWrapper(HttpServletRequest request, String ENCODING) {
		super(request);
		this.ENCODING = ENCODING;
	}

	@Override
	public String getParameter(String name) {
		String value = getRequest().getParameter(name);
		if (value != null) {
			try {
				byte[] b = value.getBytes("ISO-8859-1");
				value = new String(b, ENCODING);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		return value;
	}

}
