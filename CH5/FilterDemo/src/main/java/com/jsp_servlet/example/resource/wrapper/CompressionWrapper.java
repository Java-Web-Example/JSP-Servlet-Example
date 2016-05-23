package com.jsp_servlet.example.resource.wrapper;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.jsp_servlet.example.resource.util.GZipServletOutputStream;

/**
 * @author	Lian
 * @time	2016年1月10日 下午4:49:26
 * @desc	压缩封装器. MD--暂时没看懂, 后面接着看
 */
public class CompressionWrapper extends HttpServletResponseWrapper {

	private GZipServletOutputStream gzServletOutputStream;
	private PrintWriter printWriter;

	/**
	 * @param request
	 */
	public CompressionWrapper(HttpServletResponse response) {
		super(response);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// 已经调用过getWriter(), 再调用geOutputStream()就抛出异常
		if (printWriter != null) {
			throw new IllegalStateException();
		}

		// 创建有压缩功能的GZipServletOutputStream对象
		if (gzServletOutputStream == null) {
			gzServletOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
		}
		return gzServletOutputStream;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		// 已经调用过getOutputStream(), 再调用getWriter()就抛出异常
		if (gzServletOutputStream != null) {
			throw new IllegalStateException();
		}

		if (printWriter == null) {
			gzServletOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
			OutputStreamWriter osw = new OutputStreamWriter(gzServletOutputStream, getResponse().getCharacterEncoding());
			printWriter = new PrintWriter(osw);
		}
		return printWriter;
	}

	public GZIPOutputStream getGZIPOutputStream() {
		if (this.gzServletOutputStream == null) {
			return null;
		}
		return this.gzServletOutputStream.getGZipOutputStream();
	}

}
