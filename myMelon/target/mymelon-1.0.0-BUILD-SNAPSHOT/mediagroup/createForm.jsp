<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mediagroup/createForm.jsp</title>
<style type="text/css">
*{
	font=family:gulim;
	font-size: 24px;
}
</style>
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="title">미디어 그룹 등록</div>
<form action="create.do" method="post" name="frm">
<tr>
	<th>그룹제목<th>
	<td><input type="text" id="title" name="title" value="2020년 댄스 음악" required></td>
</tr>
<tr>
	<td colspan="2">
	<input type="submit" value="등록">
	<input type="button" value="목록" onclick="location.href='list.do'">
	</td>
</tr>

</form>

</body>
</html>