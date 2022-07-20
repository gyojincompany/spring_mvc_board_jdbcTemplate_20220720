<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글목록</title>
</head>
<body>
	<h2>자유게시판 글목록</h2>
	<hr>
	<table width="900" cellpadding="0" cellspacing="0" border="1">
		<tr align="center" bgcolor="#D9E5FF">
			<td>번호</td>
			<td>글쓴이</td>
			<td width="500">제목</td>
			<td>등록일</td>
			<td>조회수</td>			
		</tr>
		
		<c:forEach items="${list }" var="dto">
			<tr>
				<td>${dto.bid }</td>
				<td>${dto.bname }</td>
				<td>
					<c:forEach begin="1" end="${dto.bindent}">&nbsp;&nbsp;</c:forEach>
					<a href="content_view?bid=${dto.bid }">${dto.btitle }</a>
				</td>
				<td>${dto.bdate }</td>
				<td>${dto.bhit }</td>	
			</tr>
		</c:forEach>
		
		<tr align="right">
			<td colspan="5"><input type="button" value="글작성" onclick="location.href='write_form'"></td>	
		</tr>	
	</table>
</body>
</html>