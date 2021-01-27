<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>media/createForm.jsp</title>
<style type="text/css">
*{
	font=family:gulim;
	font-size: 24px;
}
</style>
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="title">음원 등록</div>
<form action="create.do" method="POST" name="frm" enctype="multipart/form-data"><!-- enctype type이 file이들어가면 무조건 써줘야됨 -->
<input type="hidden" name="mediagroupno" value="${mediagroupno}">
<table class='table'>
<tr>
	<th>제목<th>
	<td><input type="text" id="title" name="title"  size="50"></td>
</tr>
<tr>
	<th>포스터<th>
	<td><input type="file" id="poster" name="posterMF"  size="50"></td>
</tr>
<tr>
	<th>미디어 파일<th>
	<td><input type="file" id="filename" name="filenameMF"  size="50"></td>
</tr>
</table>


	<div class='bottom'>
	<input type='submit' value='등록'>
	<input type='button' value='음원목록' onclick="location.href='list.do?mediagroupno=${mediagroupno}'">
	<input type='button' value='HOME' onclick="location.href='${root}/home.do'">
	</div>

</form>

</body>
</html>