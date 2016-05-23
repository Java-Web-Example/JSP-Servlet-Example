package com.jsp_servlet.example.resource.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author	Lian
 * @time	2016年1月7日 上午1:07:47
 * @desc	负责显示会员网页
 */
@WebServlet("/member.view")
public class Member extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String USERS = "C:/GitHub/JSP-Servlet-Example/CH4/Gossip/users";
	private final String LOGIN_VIEW = "index.html";

	/**
	 * 真正处理的方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 若无'login'属性, 直接重定向至登录网页
		if (request.getSession().getAttribute("login") == null) {
			response.sendRedirect(LOGIN_VIEW);
			return;
		}

		String username = (String) request.getSession().getAttribute("login");

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("  <meta content='text/html; charset=UTF-8' http-equiv='content-type'>");
		out.println("<title>Gossip 微网志</title>");
		out.println("<link rel='stylesheet' href='css/member.css' type='text/css'>");
		out.println("</head>");
		out.println("<body>");

		out.println("<div class='leftPanel'>");
		out.println("<img src='images/caterpillar.jpg' alt='Gossip 微网志' /><br><br>");

		// 用户注销链接
		out.println("<img src='images/caterpillar.jpg' alt='Gossip 微博' /><br><br>");
		out.println("<a href='logout.do?username=" + username + "'>注销 " + username + "</a>" );

		out.println("</div>");
		out.println("<form method='post' action='message.do'>");
		out.println("分享新鲜事...<br>");

		// 判断是否回填信息
		String blabla = request.getParameter("blabla");
		if(blabla == null) {
			blabla = "";
		} else {
			out.println("信息要 140 字以内<br>");
		}
		out.println("<textarea cols='60' rows='4' name='blabla'>" + blabla + "</textarea>");

		out.println("<br>");
		out.println("<button type='submit'>送出</button>");
		out.println("</form>");
		out.println("<table style='text-align: left; width: 510px; height: 88px;' border='0' cellpadding='2' cellspacing='2'>");
		out.println("<thead>");
		out.println("<tr><th><hr></th></tr>");
		out.println("</thead>");
		out.println("<tbody>");

		// 读取文件并逐个显示信息
		Map<Date, String> messages = readMessage(username);
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINA);
		for(Date date : messages.keySet()) {
			out.println("<tr><td style='vertical-align: top;'>");
			out.println(username + "<br>");
			out.println(messages.get(date) + "<br>");
			out.println(dateFormat.format(date));
			out.println("<a href='delete.do?message=" + date.getTime() + "'>删除</a>");
			out.println("<hr></td></tr>");
		}

		out.println("</tbody>");
		out.println("</table>");
		out.println("<hr style='width: 100%; height: 1px;'>");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}

	// 用以过滤.txt文件名
	private class TxtFilenameFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			return name.endsWith(".txt");
		}
	}

	private TxtFilenameFilter filenameFilter = new TxtFilenameFilter();

	// TreeMap排序用, 因为希望信息的日期越近的在越上头显示
	private class DateComparator implements Comparator<Date> {
		public int compare(Date d1, Date d2) {
			return -d1.compareTo(d2);
		}
	}

	private DateComparator comparator = new DateComparator();

	private Map<Date, String> readMessage(String username) throws IOException {
		File border = new File(USERS + "/" + username);
		String[] txts = border.list(filenameFilter);

		Map<Date, String> messages = new TreeMap<Date, String>(comparator);
		for(String txt : txts) {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(USERS + "/" + username + "/" + txt), "UTF-8"));
			String text = null;
			StringBuilder builder = new StringBuilder();
			while((text = reader.readLine()) != null) {
				builder.append(text);
			}
			Date date = new Date(Long.parseLong(txt.substring(0, txt.indexOf(".txt"))));
			messages.put(date, builder.toString());
			reader.close();
		}
		return messages;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
