<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 입력</title>
</head>
<body>	
	<h1>단을 입력하세요</h1>
	<form action='gugudanResult.jsp' method='post'>
		단 : <input type='text' name='dan'><br>
		<input type='submit' value='구구단 출력'>
		<input type='reset' value='다시입력'>
	</form>
</body>
</html>