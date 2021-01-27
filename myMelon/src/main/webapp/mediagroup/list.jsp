<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib 	prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib 	prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mediagroup/list.jsp</title>
<style type="text/css">
*{
	font=family:gulim;
	font-size: 24px;
}
</style>
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="title">미디어 그룹 목록</div>		
	<table>
	<tr>
		<th>그룹번호</th>
		<th>그룹제목</th>
		<th>수정/삭제</th>
	</tr>
	<!-- forEach ->만약 list에 정보가 5개있다하면 하나씩 뽑아옴 -->
	<c:forEach var="dto" items="${list}">
		<tr>
			<td>${dto.mediagroupno}</td>
			<td><a href="../media/list.do?mediagroupno=${dto.mediagroupno}">${dto.title}</a></td>		
			<td>	
				<input type="button" value="수정" onclick="location.href='update.do?mediagroupno=${dto.mediagroupno}'">
				<input type="button" value="삭제" onclick="location.href='delete.do?mediagroupno=${dto.mediagroupno}'">
			</td>	
		</tr>
	</c:forEach>
	</table>
</body>
</html>