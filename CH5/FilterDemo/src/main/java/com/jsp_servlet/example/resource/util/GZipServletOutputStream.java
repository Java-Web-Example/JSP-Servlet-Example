package com.jsp_servlet.example.resource.util;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 * @author	Lian
 * @time	2016年1月10日 下午4:42:21
 * @desc	
 */
public class GZipServletOutputStream extends ServletOutputStream {

	private GZIPOutputStream gzipOutputStream;

	public GZipServletOutputStream(ServletOutputStream servletOutputStream) throws IOException {
		this.gzipOutputStream = new GZIPOutputStream(servletOutputStream);
	}

	/**
	 * @return
	 */
	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param arg0
	 */
	@Override
	public void setWriteListener(WriteListener arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param b
	 * @throws IOException
	 */
	@Override
	public void write(int b) throws IOException {
		gzipOutputStream.write(b);
	}

	public GZIPOutputStream getGZipOutputStream() {
		return gzipOutputStream;
	}

}
