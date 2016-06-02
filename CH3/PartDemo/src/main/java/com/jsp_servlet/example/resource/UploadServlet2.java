package com.jsp_servlet.example.resource;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * @author	Lian
 * @time	2015年12月31日 上午12:52:32
 * @desc	使用getPart()取得上传文件
 * 			Tomcat中必须设置@MultipartConfig标注才能使用getPart()相关API
 * 			配置@MultipartConfig属性值, 并使用Part.write()方法储存文件
 */
@MultipartConfig(location = "c:/Github")
@WebServlet("/upload2.do")
public class UploadServlet2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 重写doPost方法取得上传文件
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 为了处理文件中文名, 设置请求字符串编码
		request.setCharacterEncoding("UTF-8");

		// 使用getPart()取得part对象
		Part part = request.getPart("photo");

		// 取得文件名
		String filename = getFilename(part);

		// 将文件写入location指定的目录
		part.write(filename);
	}

	/**
	 * 取得上传文件名
	 * 
	 * @param part
	 * @return
	 */
	private String getFilename(Part part) {
		String header = part.getHeader("Content-Disposition");
		String filename = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
		return filename;
	}
}
