<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String user_id = request.getParameter("user_id");
	String user_pw = request.getParameter("user_pw");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과출력창</title>
</head>
<body>
	<%	if(user_id==null || user_id.length()==0){%>
			아이디를 입력하세요<br>
	<%	}else if(user_id.equals("admin")){ %>
			<button>회원정보 수정하기 </button><button>회원정보 삭제하기 </button><br>
	<%	}else { %>
			<h1>환영합니다. <%=user_id%> 님!!</h1>
	<%	}%>
	<a href='/Study_JSP/1_JSP/login.html'>로그인 하기</a>
</body>
</html>