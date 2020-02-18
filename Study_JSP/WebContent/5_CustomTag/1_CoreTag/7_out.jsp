<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 출력 창</title>
</head>
<body>
	<table align='center' border='1'>
		<tr align='center' bgcolor='lightgreen'>
			<td width='10%'><b>아이디</b></td>
			<td width='10%'><b>패스워드</b></td>
			<td width='10%'><b>이름</b></td>
			<td width='10%'><b>이메일</b></td>
		</tr>
		<tr align='center'>
			<td width='10%'><c:out value="${param.id}"/></td>
			<td width='10%'><c:out value="${param.pw}"/></td>
			<td width='10%'><c:out value="${param.name}"/></td>
			<td width='10%'><c:out value="${param.email}"/></td>
		</tr>
	</table>
	
</body>
</html>