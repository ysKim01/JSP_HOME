<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인클루드 테스트 1</title>
</head>
<body>
	환영합니다. JSP 페이지 입니다.<br>
	<!-- duke_image.jsp를 여는데  name,imgName이란 파라미터로 각각 듀크, duke.png의 값을 갖고 간다. -->
	<jsp:include page='duke_image.jsp' flush='true'>
		<jsp:param value="듀크2" name="name"/>
		<jsp:param value="duke2.png" name="imgName"/>
	</jsp:include>
	<br>
	jsp 페이지 끝 부분 입니다.
</body>
</html>