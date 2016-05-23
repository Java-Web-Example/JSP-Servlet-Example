package com.jsp_servlet.example.resource.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * @author	Lian
 * @time	2016年1月10日 下午4:19:50
 * @desc	
 */
@WebFilter("/*")
public class EscapeFilter implements Filter {

	/**
	 * init初始化方法
	 * @param fConfig
	 * @throws ServletException
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

	/**
	 * 过滤器的真正执行方法
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest requestWrapper = new EscapeWrapper((HttpServletRequest)request);
		chain.doFilter(requestWrapper, response);
	}


	
	public void destroy() {
	}

}
