<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.setAttribute("address1", "서울시 강남구");
	application.setAttribute("address2", "김포시 장기동");
%>
<%-- 
	session은 한 브라우저에서는 유효
	application은 다른 브라우저에서도 유효
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장객체 스코프 테스트 1</title>
</head>
<body>
	이름과 주소를 저장합니다.<br>
	<a href='appTest2.jsp'>두번째 페이지로 이동</a>
</body>
</html>