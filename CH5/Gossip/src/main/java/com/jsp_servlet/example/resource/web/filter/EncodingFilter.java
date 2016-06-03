package com.jsp_servlet.example.resource.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

import com.jsp_servlet.example.resource.web.wrapper.EncodingWrapper;

/**
 * @author	Lian
 * @time	2016年1月10日 下午4:19:50
 * @desc	
 */
@WebFilter(urlPatterns = {"/*"},
		initParams = {
			@WebInitParam(name = "ENCODING", value = "UTF-8")
		})
public class EncodingFilter implements Filter {

	private String ENCODING;

	/**
	 * init初始化方法
	 * 
	 * @param fConfig
	 * @throws ServletException
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		ENCODING = fConfig.getInitParameter("ENCODING");
	}

	/**
	 * 过滤器的真正执行方法
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if ("GET".equals(req.getMethod())) {
			req = new EncodingWrapper(req, ENCODING);
		} else {
			req.setCharacterEncoding(ENCODING);
		}
		chain.doFilter(req, response);
	}

	public void destroy() {
	}

}
