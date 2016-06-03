package com.jsp_servlet.example.resource.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author	Lian
 * @time	2016年1月10日 下午9:30:48
 * @desc	
 */
public class UserService {

	private String USERS;

	public UserService(String USERS) {
		// 设置目录
		this.USERS = USERS;
	}

	/**
	 * 校验是否为不合法用户昵称
	 * 
	 * @param username
	 * @return
	 */
	public boolean isInvalidUsername(String username) {
		for(String file : new File(USERS).list()) {
			if(file.equals(username)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 创建用户目录与基本资料
	 * 
	 * @param email
	 * @param username
	 * @param passwd
	 * @throws IOException
	 */
	public void createUserData(String email, String username, String passwd) throws IOException {
		File userhome = new File(USERS + "/" + username);
		userhome.mkdir();
		BufferedWriter writer = new BufferedWriter(new FileWriter(userhome + "/profile"));
		writer.write(email + "\t" + passwd);
		writer.close();
	}

	/**
	 * 检查登录用户名称与密码
	 * 
	 * @param username
	 * @param passwd
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings("resource")
	public boolean checkLogin(String username, String password) throws IOException {
		if(username != null && password != null) {
			for(String file : new File(USERS).list()) {
				if(file.equals(username)) {
					BufferedReader reader = new BufferedReader(new FileReader(USERS + "/" + file + "/profile"));
					String passwd = reader.readLine().split("\t")[1];
					if(passwd.equals(password)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * @author	Lian
	 * @time	2016年1月10日 下午10:00:45
	 * @desc	文件名称过滤器
	 */
	private class TxtFilenameFilter implements FilenameFilter {

		public boolean accept(File dir, String name) {
			return name.endsWith(".txt");
		}
	}

	private TxtFilenameFilter filenameFilter = new TxtFilenameFilter();

	/**
	 * @author	Lian
	 * @time	2016年1月10日 下午10:04:50
	 * @desc	日起比较器
	 */
	private class DateComparator implements Comparator<Date> {

		public int compare(Date d1, Date d2) {
			return -d1.compareTo(d2);
		}
	}

	private DateComparator comparator = new DateComparator();

	/**
	 * 读取用户信息
	 * 
	 * @param username
	 * @return
	 * @throws IOException 
	 */
	public Map<Date, String> readMessage(String username) throws IOException {
		File border = new File(USERS + "/" + username);
		String[] txts =border.list(filenameFilter);

		Map<Date, String> messages = new TreeMap<Date, String>(comparator);
		for(String txt : txts) {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(
									USERS + "/" + username + "/" + txt), "UTF-8"));
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

	/**
	 * 新增信息
	 * 
	 * @param username
	 * @param blabla
	 * @throws IOException
	 */
	public void addMessage(String username, String blabla) throws IOException {
		String file = USERS + "/" + username + "/" + new Date().getTime() + ".txt";
		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
		writer.write(blabla);
		writer.close();
	}

	/**
	 * 删除信息
	 * 
	 * @param username
	 * @param message
	 */
	public void deleteMessage(String username, String message) {
		File file = new File(USERS + "/" + username + "/" + message + ".txt");
		if(file.exists()) {
			file.delete();
		}
	}
}
