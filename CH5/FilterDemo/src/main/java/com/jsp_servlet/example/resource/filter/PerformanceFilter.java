package com.jsp_servlet.example.resource.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * @author	Lian
 * @time	2016年1月10日 下午4:02:02
 * @desc	过滤器Filter的使用
 */
@WebFilter(filterName="performace", urlPatterns={"/*"})
public class PerformanceFilter implements Filter{

	private FilterConfig config;
	
	/**
	 * 初始化工作
	 * @param config
	 * @throws ServletException
	 */
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	/**
	 * 过滤器中实际执行的操作
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		long begin = System.currentTimeMillis();
		chain.doFilter(request, response);
		long end = System.currentTimeMillis();
		config.getServletContext().log("Request process in " + (end -begin) + " millionseconds");
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

}
