<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비교 연산자</title>
</head>
<body>
	\${10==10} : ${10==10} <br>
	\${10 eq 10} : ${10 eq 10} <br>
	\${"hello"=="hello"} : ${"hello"=="hello"} <br>
	\${10!=10} : ${10!=10} <br>
	\${10 ne 10} : ${10 ne 10} <br>
	<!-- 문자열 비교는 ==, != 만 된다. -->
	
	\${10<10} : ${10<10} <br>
	\${10 lt 10} : ${10 lt 10} <br>
	\${10>10} : ${10>10} <br>
	\${10 gt 10} : ${10 gt 10} <br>
	\${10<=10} : ${10<=10} <br>
	\${10 le 10} : ${10 le 10} <br>
	\${10>=10} : ${10>=10} <br>
	\${10 ge 10} : ${10 ge 10} <br>	 
	 
	
</body>
</html>
	


	