<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import='java.util.*'
    import='member.*'
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 출력 창</title>
<style type="text/css">
	h1 {
		text-align : center;
	}
</style>
</head>
<body>
	<h1>회원 정보 출력</h1>
<%
	request.setCharacterEncoding("utf-8");
	String name1 = request.getParameter("name");
	MemberVO memberVO = new MemberVO();
	memberVO.setName(name1);
	MemberDAO dao = new MemberDAO();
	List memberList = dao.searchMember(memberVO);
%>
	<table border=1 width=800 align=center>
		<tr align=center bgcolor='#ffff66'>
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>이메일</td>
			<td>가입일자</td>
		</tr>
<%
	for(int i=0;i<memberList.size();i++){
		MemberVO vo = (MemberVO)memberList.get(i);
		String id = vo.getId();
		String pw = vo.getPw();
		String name = vo.getName();
		String email = vo.getEmail();
		Date joinDate = vo.getJoinDate();
%>
		<tr align='center'>
			<td><%=id %></td>
			<td><%=pw %></td>
			<td><%=name %></td>
			<td><%=email %></td>
			<td><%=joinDate %></td>
		</tr>
<%	}
%>
	</table>
</body>
</html>