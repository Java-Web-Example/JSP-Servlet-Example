<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="error.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>加法网页</title>
	</head>
	<body>
<%
	String a = request.getParameter("a");
	String b = request.getParameter("b");
	Integer result = Integer.parseInt(a) + Integer.parseInt(b);
	out.println("a + b = " + result);
%>
	</body>
</html>