<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="com.jsp_servlet.example.resource.entity.User" />
<jsp:setProperty name="user" property="*" />

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>登入页面</title>
	</head>
	<body>
<%
	// user名称是根据<jsp:useBean>上的id名称而来
	if(user.isValid()) {
%>
	<h1><jsp:getProperty property="name" name="user"/>登入成功</h1>
<%
	} else {
%>
	<h1>登录失败</h1>
<% 
	} 
%>
	</body>
</html>