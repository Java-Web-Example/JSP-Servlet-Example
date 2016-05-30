package com.jsp_servlet.example.resource;

import java.io.IOException;
import java.io.PrintWriter;
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
 * 均以林为参数
 * 
 * @author Lian
 * @time 2016年3月18日
 * @since 1.0
 */
@WebServlet("/encoding")
public class EncodingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 重写doGet方法 TODO Get方式没走通. 应该是编码格式的问题, MLGB.
	 *   请求链接为: http://localhost:8080/EncodingDemo/encoding?nameGet=%AAL
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log("********* Request encoding is: " + request.getCharacterEncoding());
		log("********* Default web container's encoding is: " + System.getProperty("file.encoding"));
		response.setContentType("text/html; charset=BIG5");
		PrintWriter out = response.getWriter();

		// 相当于使用URLDecoder.decode(str, "utf-8")
		String name = request.getParameter("nameGet");
		out.println(name);
		out.println(URLDecoder.decode(name, "BIG5"));
		out.println(URLDecoder.decode(name, "UTF-8"));
		String name2 = new String(name.getBytes("UTF-8"), "BIG5");
		String name3 = new String(name.getBytes("BIG5"), "UTF-8");
		out.println(name2);
		out.println(name3);
		log("********* Method GET, the name is:" + name);
	}

	/**
	 * 重写doPost方法
	 *   因为页面设置了charset=BIG5, 相当于浏览器做了一次加密: java.net.URLEncoder.encode(param, "BIG5");
	 * 而容器的默认编码是UTF-8(可由日志得出), 相当于进行了一次解密: java.net.URLDecoder.decode(str, "UTF-8"), 因此这样显示的中文字符就不正确了.
	 * 可以使用HttpServletRequest的setCharacterEncoding()方法指定取得POST请求参数时使用的编码, 相当于做了一次解密.
	 * @Atttention	一定要在取得任何请求参数前调用setCharacterEncoding()方法才有作用!
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log("********* Request encoding is: " + request.getCharacterEncoding());
		log("********* Default web container's encoding is: " + System.getProperty("file.encoding"));

		// POST的编码处理. 
		request.setCharacterEncoding("BIG5");
		String name = request.getParameter("namePost");
		log("********* Method POST, the name is:" + name);
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
