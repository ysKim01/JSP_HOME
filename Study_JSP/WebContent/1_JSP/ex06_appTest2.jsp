<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String add_sess = (String)session.getAttribute("address1");
	String add_app = (String)application.getAttribute("address2");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장객체 스코프 테스트 1</title>
</head>
<body>
	주소(session)는 <%=add_sess %> 입니다.<br>
	주소(application)는  <%=add_app %> 입니다.<br>
</body>
</html>