package com.jsp_servlet.example.resource.web;

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
import javax.servlet.http.HttpServletResponse;

/**
 * @author	Lian
 * @time	2016年1月10日 下午10:23:10
 * @desc	
 */
@WebFilter(
		urlPatterns = {"/delete.do", "/logout.do", "/message.do", "/member.view"},
		initParams = {@WebInitParam(name="LOGIN_VIEW", value="index.html")})
public class MemberFilter implements Filter {

	private String LOGIN_VIEW;

	/**
	 * 过滤器初始化
	 * @param fConfig
	 * @throws ServletException
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// 设置登录页面
		this.LOGIN_VIEW = fConfig.getInitParameter("LOGIN_VIEW");
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if(req.getSession().getAttribute("login") != null) {
			// 只有在具备login属性时, 才调用doFilter()
			chain.doFilter(request, response);
		} else {
			// 否则重新定向至登录页面
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect(LOGIN_VIEW);
		}
	}

	/**
	 * 过滤器销毁
	 */
	public void destroy() {
	}

}
