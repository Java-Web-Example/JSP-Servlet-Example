package com.jsp_servlet.example.resource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
 */
@MultipartConfig
@WebServlet("/upload.do")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 重写doPost方法取得上传文件
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 使用getPart()取得part对象
		Part part = request.getPart("photo");
		String filename = getFilename(part);
		writeTo(filename, part);
	}

	/**
	 * 取得上传文件名
	 * @param part
	 * @return
	 */
	private String getFilename(Part part) {
		String header = part.getHeader("Content-Disposition");
		String filename = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
		return filename;
	}

	/**
	 * 储存文件
	 * @param filename
	 * @param part
	 * @throws IOException 
	 */
	private void writeTo(String filename, Part part) throws IOException {
		InputStream in = part.getInputStream();
		OutputStream out = new FileOutputStream("c:/Github/" + filename);

		byte[] buffer = new byte[1024];
		int length = -1;
		while ((length = in.read(buffer)) != -1) {
			out.write(buffer, 0, length);
		}
		in.close();
		out.close();
	}
}
