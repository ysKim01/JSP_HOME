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
<title>c:redirect</title>
</head>
<body>
	<c:redirect url="/5_CustomTag/1_CoreTag/memberOutput.jsp">
		<c:param name='id' value='hong'/>
		<c:param name='pw' value='1234'/>
		<c:param name='name' value="홍길동" />
		<c:param name='email' value='Hong@page.com'/>
	</c:redirect>
	<!-- 이것도 get방식 -->
</body>
</html>