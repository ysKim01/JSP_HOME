<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%	request.setCharacterEncoding("utf-8");	%>

<c:set var='id' value='hong' scope='page'/>
<c:set var='pw' value='1234' scope='page'/>
<c:set var='name' value="${'홍길동' }" scope='page'/>
<c:set var='age' value='22' scope='page'/>
<c:set var='height' value='177' scope='page'/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조건문 실습</title>
</head>
<body>
	<c:if test="${true} ">
		<h1>항상 참 입니다.</h1>
	</c:if>
	<c:if test="${true} ">
		<h1>항상 참 입니다.</h1>
	</c:if>
</body>
</html>