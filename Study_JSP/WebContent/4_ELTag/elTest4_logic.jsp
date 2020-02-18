<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>논리 연산자</title>
</head>
<body>
	\${(10!=10) && (10==10)} : ${(10!=10) && (10==10)} <br>
	\${(10!=10) and (10==10)} : ${(10!=10) and (10==10)} <br>
	\${(10!=10) || (10==10)} : ${(10!=10) || (10==10)} <br>
	\${(10!=10) or (10==10)} : ${(10!=10) or (10==10)} <br>
	
	\${!(10==10)} : ${!(10==10)} <br>
	\${not(10==10)} : ${not(10==10)} <br>
	
</body>
</html>
	


	