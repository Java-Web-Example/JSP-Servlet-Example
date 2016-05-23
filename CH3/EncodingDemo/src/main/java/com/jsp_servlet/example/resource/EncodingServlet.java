package com.jsp_servlet.example.resource;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理POST和GET提交字符的编码格式
 * 
 * @author Lian
 * @time 2016年3月18日
 * @since 1.0
 */
@WebServlet("/encoding")
public class EncodingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 重写doGet方法 TODO Get方式没走通. 先放这儿.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 相当于使用URLDecoder.decode(str, "utf-8")
		String name = request.getParameter("nameGet");
		name = new String(name.getBytes("UTF-8"), "BIG5");
		System.out.println(name);
	}

	/**
	 * 重写doPost方法
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST的编码处理
		request.setCharacterEncoding("BIG5");
		String name = request.getParameter("namePost");
		System.out.println("POST : " + name);
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String name = "廉新忠";
		name = URLEncoder.encode(name, "BIG5");
		System.out.println(name);

		name = URLDecoder.decode(name, "BIG5");
		// System.out.println(name);

		name = new String(name.getBytes("BIG5"), "UTF-8");
		System.out.println(name);
	}

}
