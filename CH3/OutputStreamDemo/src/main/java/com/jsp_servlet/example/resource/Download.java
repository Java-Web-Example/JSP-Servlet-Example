package com.jsp_servlet.example.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author	Lian
 * @time	2016年1月5日 上午1:31:27
 * @desc	使用getOutputStream()输出二进制字符
 */
@WebServlet("/download.do")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 重写doPost方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String passwd = request.getParameter("passwd");
		if ("123456".equals(passwd)) {
			// 设置响应内容类型
			response.setContentType("application/pdf");

			// 取得输入串流
			InputStream in = getServletContext().getResourceAsStream("/WEB-INF/jdbc.pdf");

			// 取得输出串流
			OutputStream out = response.getOutputStream();

			// 读取PDF并输出至浏览器
			writBytes(in, out);
		}
	}

	private void writBytes(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int length = -1;
		while((length = in.read(buffer)) != -1) {
			out.write(buffer, 0, length);
		}
		in.close();
		out.close();
	}

}
