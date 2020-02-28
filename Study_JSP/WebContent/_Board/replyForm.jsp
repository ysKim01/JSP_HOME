<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%	request.setCharacterEncoding("utf-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>답글쓰기창</title>
<script src='http://code.jquery.com/jquery-latest.min.js'></script>
<script type="text/javascript">
	function readURL(input){
		if(input.files && input.files[0]){
			var reader = new FileReader();
			reader.onload = function(e){
				$("#preview").attr("src", e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	function backToList(obj){
		obj.action="${contextPath}/board/listArticles.do";
		obj.submit()
	}
</script>
</head>
<body>
<h1 style="text-align: center;">답글 쓰기</h1>
<form name="frmReply" method="post" action="${contextPath }/board/addReply.do" enctype="multipart/form-data">
<table border="0" align="center">
	<tr>
		<td align="right">글쓴이 : &nbsp;</td>
		<td><input type="text" size="5" value="Lee" disabled></td>
	</tr>
	<tr>
		<td align="right">글제목 : </td>
		<td><input type="text" size="67" maxlength="100" name="title"></td>
	</tr>
	<tr>
		<td align="right" valing="top"><br>글내용 : &nbsp;</td>
		<td><textarea name=content rows="10" cols="65" maxlength="4000"></textarea></td>
	</tr>
	<tr>
		<td aling="right">이미지파일 첨부 : </td>
		<td><input type="file" name="imageFileName" onchange="readURL(this);" /></td>
		<td><img id="preview" src="#" width="200" height="200" /></td>
	</tr>
	<tr>
		<td align="right"></td>
		<td>
			<input type="submit" value="답글쓰기" />
			<input type="button" value="취소" onclick="backToList(this.form)" />
		</td>
	</tr>
</table>
</form>
</body>
</html>












