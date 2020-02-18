<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var='id' value='hong' scope='page'/>
<c:set var='pw' value='1234' scope='page'/>
<%-- <c:set var='name' value="${'홍길동' }" scope='page'/> --%>
<c:set var='age' value='22' scope='page'/>
<c:set var='height' value='177' scope='page'/>
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
			<td width='10%'><b>나이</b></td>
			<td width='10%'><b>키</b></td>
		</tr>
		<c:choose>
			<c:when test="${empty name }">
				<tr align='center'>
					<td colspan=5>이름을 입력하세요</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr align='center'>
				<td width='10%'>${id }</td>
				<td width='10%'>${pw }</td>
				<td width='10%'>${name }</td>
				<td width='10%'>${age }</td>
				<td width='10%'>${height }</td>
			</tr>
			</c:otherwise>
		</c:choose>
	</table>
	
</body>
</html>