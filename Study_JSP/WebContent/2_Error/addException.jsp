<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="true"
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Error Page</h1>
	<h2>======= toString() =======</h2>
	<h2><%=exception.toString() %></h2>
	<h2>======= getMessage() =======</h2>
	<h2><%=exception.getMessage() %></h2>

	<h2>======= printStackTrace() =======</h2>
	<h2><%exception.printStackTrace(); %></h2><%-- 콘솔출력 --%>

</body>
</html>