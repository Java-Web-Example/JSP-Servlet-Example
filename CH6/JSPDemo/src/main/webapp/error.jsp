<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<%@page import="java.io.PrintWriter" %>
<html>
	<head>
		<title>错误</title>
	</head>
	<body>
		<h1>网页发生错误: </h1><%= exception %>
		<h2>显示例外堆栈追踪: </h2>
		<%
			exception.printStackTrace(new PrintWriter(out));
		%>
	</body>
</html>