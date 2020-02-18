<%@page import="java.util.*, member.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%
	request.setCharacterEncoding("utf-8");
	List dataList = new ArrayList();
	dataList.add("a");
	dataList.add("b");
	dataList.add("c");
%>
<c:set var="list" value="<%=dataList %>" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 출력 창</title>
</head>
<body>
	<c:forEach var="i" begin="1" end="10" step="1" varStatus="loop">
		i = ${i }<br>
		반복횟수 : ${loop.count}<br>
	</c:forEach>
	<br>
	<c:forEach var="str" items="${list}">
		${ str } <br>
	</c:forEach>
</body>
</html>