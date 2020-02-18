<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현 언어에서 사용되는 데이터들</title>
</head>
<body>
	\${100}: $100<br>
	\${"안녕하세요"} : ${"안녕하세요"} <br> 
	\${10+1} : ${10+1} <br> 
	\${"10"+1} : ${"10"+1} <br> 
	<!-- 문자열 이라도 숫자형이면 자동 변환해서 연산 -->
	\${null+1} : ${null+1} <br> 
	<%--\${"안녕"+1} : ${"안녕"+1} <br>  이거 안됨 --%>
	<%-- \${"hello" + "world"} : ${"hello" + "world"} 문자열 연산이라는게 안됨 --%>
</body>
</html>
	


	