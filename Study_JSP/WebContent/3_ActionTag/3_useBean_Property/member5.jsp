<%@page import="member.*, java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%	request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id='memberVO' class='member.MemberVO'/>
<jsp:setProperty name='memberVO' property='*' />
<!-- 전송된 매개변수 이름과 빈 속성을 비교해서 동일한 빈에 값을 자동설정 -->

<jsp:useBean id='dao' class='member.MemberDAO' />

<%
	dao.addMember(memberVO);
	List membersList = dao.listMembers();
%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table align='center' width='100%'>
		<tr align='center' bgcolor='#99ccff'>
			<td width='7%'>아이디</td>
			<td width='7%'>패스워드</td>
			<td width='5%'>이름</td>
			<td width='11%'>이메일</td>
		</tr>
		<tr align='center'>
			<td><jsp:getProperty name='memberVO' property='id' /></td>
			<td><jsp:getProperty name='memberVO' property='pw' /></td>
			<td><jsp:getProperty name='memberVO' property='name' /></td>
			<td><jsp:getProperty name='memberVO' property='email' /></td>
		</tr>
		<tr height='1' bgcolor='#99ccff'>
			<td colspan='5'></td>
		</tr>
	</table>
</body>
</html>