<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ page isELIgnored="false" %> 
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>加法网页</title>
	</head>
	<body>
		${param.a} + ${param.b} = ${param.a + param.b}
	</body>
</html>