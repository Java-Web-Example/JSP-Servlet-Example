package com.jsp_servlet.example.resource;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用getInputStream()来处理上传的文件
 *
 * @author	Lian
 * @time	2016年4月25日
 * @since	1.0
 */
@WebServlet("/upload.do")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 重写doPost方法来处理上传的文件
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 读取请求body
		byte[] body = readBody(request);

		// 取得所有Body内容的字符串表示
		String textBody = new String(body, "ISO-8859-1");

		// 取得上传的文件名称
		String filename = getFileName(textBody);

		// 取得文件开始于结束位置
		Position p = getFilePosition(request, textBody);

		// 输出至文件
		writeTo(filename, body, p);

		// 文件保存成功, 返回响应信息
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("文件保存成功");
		out.close();
	}

	class Position {
		int begin;
		int end;

		public Position(int begin, int end) {
			this.begin = begin;
			this.end = end;
		}
	}

	/**
	 * 获取请求的Body中的 数据
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	private byte[] readBody(HttpServletRequest request) throws IOException {
		int formDataLength = request.getContentLength();
		// 取得ServletInputStream对象
		DataInputStream dataStream = new DataInputStream(request.getInputStream());
		byte[] body = new byte[formDataLength];
		int totalBytes = 0;
		while (totalBytes < formDataLength) {
			int bytes = dataStream.read(body, totalBytes, formDataLength);
			totalBytes += bytes;
		}
		return body;
	}

	/**
	 * 取得文件边界信息
	 * 
	 * @param request
	 * @param textBody
	 * @return Position
	 * @throws UnsupportedEncodingException
	 */
	private Position getFilePosition(HttpServletRequest request, String textBody) throws UnsupportedEncodingException {
		// 取得文件区段边界信息
		String contentType = request.getContentType();
		String boundaryText = contentType.substring(contentType.lastIndexOf("=") + 1, contentType.length());

		// 取得实际上传文件的起始与结束位置
		int pos = textBody.indexOf("filename=\"");
		pos = textBody.indexOf("\n", pos) + 1;
		pos = textBody.indexOf("\n", pos) + 1;
		pos = textBody.indexOf("\n", pos) + 1;
		int boundaryLoc = textBody.indexOf(boundaryText, pos) - 4;
		int begin = ((textBody.substring(0, pos)).getBytes("ISO-8859-1")).length;
		int end = ((textBody.substring(0, boundaryLoc)).getBytes("ISO-8859-1")).length;

		return new Position(begin, end);
	}

	/**
	 * 得到文件名称
	 * @param reqBody
	 * @return
	 */
	private String getFileName(String reqBody) {
		String filename = reqBody.substring(reqBody.indexOf("filename=\"") + 10);
		filename = filename.substring(0, filename.indexOf("\n"));
		filename = filename.substring(filename.lastIndexOf("\\") + 1, filename.indexOf("\""));
		return filename;
	}

	/**
	 * 保存文件
	 * @param filename
	 * @param body
	 * @param p
	 * @throws IOException
	 */
	private void writeTo(String filename, byte[] body, Position p) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream("c:/Github/" + filename);
		fileOutputStream.write(body, p.begin, (p.end - p.begin));
		fileOutputStream.flush();
		fileOutputStream.close();
	}
}
