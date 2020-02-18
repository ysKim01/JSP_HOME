<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%!
	Integer isNumber(String str){
		Integer result = null;
		try{
			str = str.trim();
			result = Integer.parseInt(str);
		}catch(Exception e){
			e.printStackTrace();
			result = null;
		}
		return result;
	}
%>
<%
	request.setCharacterEncoding("utf-8");
	Integer dan = isNumber(request.getParameter("dan"));
%>
<c:set var="dan" value="<%=dan%>"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 결과 출력</title>
</head>
<body>
<c:choose>
	<c:when test="${empty dan }">
		<h1>숫자를 제대로 입력하세요.</h1>
	</c:when>
	<c:when test="${dan>9 || dan<2}">
		<h1>2에서 9까지의 숫자를 입력해 주세요.</h1>
	</c:when>
	<c:otherwise>
		<table align='center' border='1'>
		<tr align='center' bgcolor='lightgreen'>
			<th colspan='2'><strong>${dan } 단</strong></th>
		</tr>
		<c:forEach var="i" begin="1" end="9" step="1" >
			<tr align='center'>
				<td>${dan } * ${i } </td>
				<td>${dan * i } </td>
			</tr>
		</c:forEach>
	</table>
	</c:otherwise>
</c:choose>

<a href='gugudanTest.jsp'>구구단 입력창</a>
</body>
</html>