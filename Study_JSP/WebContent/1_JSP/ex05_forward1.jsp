<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setAttribute("name", "ysKim");
	request.setAttribute("address", "Gimpo");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>none</title>
</head>
<body>
<%
	RequestDispatcher dispatch = request.getRequestDispatcher("forward2.jsp");
	dispatch.forward(request, response);
%>
</body>
</html>