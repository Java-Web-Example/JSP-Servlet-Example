<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
	String name = "caterpillar";
	String passwd = "123456";

	boolean checkUser(String name, String passwd) {
		return this.name.equals(name) && this.passwd.equals(passwd);
	}
%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>登入页面</title>
	</head>
	<body>
<%
	String name = request.getParameter("name");
	String passwd = request.getParameter("passwd");
	if(checkUser(name, passwd)) {
%>
	<h1><%= name %>登入成功</h1>
<%
	} else {
%>
	<h1>登录失败</h1>
<% 
	} 
%>
	</body>
</html>