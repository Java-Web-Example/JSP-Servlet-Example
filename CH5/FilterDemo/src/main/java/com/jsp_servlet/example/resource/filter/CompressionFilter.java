package com.jsp_servlet.example.resource.filter;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp_servlet.example.resource.wrapper.CompressionWrapper;

/**
 * @author	Lian
 * @time	2016年1月10日 下午5:03:45
 * @desc	
 */
@WebFilter(filterName = "CompressionFilter", urlPatterns = {"/*"})
public class CompressionFilter implements Filter{

	public void init(FilterConfig arg0) throws ServletException {
	}

	/**
	 * 过滤器执行的真正方法
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String encodings = req.getHeader("accept-encoding");
		if (encodings != null && (encodings.indexOf("gzip") > -1)) {
			// 创建响应封装器
			CompressionWrapper responseWrapper = new CompressionWrapper(res);
			// 设置响应内容编码为gzip格式
			responseWrapper.setHeader("content-encoding", "gzip");

			// 下一个过滤器
			chain.doFilter(request, responseWrapper);

			GZIPOutputStream gzipOutputStream = responseWrapper.getGZIPOutputStream();
			if (gzipOutputStream != null) {
				// 调用GZIPOutputStream的finish()方法完成压缩输出
				gzipOutputStream.finish();
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
	}

}
