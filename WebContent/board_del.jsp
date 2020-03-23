<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
	<form action="board_del_ok.do">
	<input type="hidden" name="no" value="${no }">
	<input type="hidden" name="page" value="${page }">
	
	<table>
	<tr>
	<th>비밀번호확인</th>
	<td><input type="text" name="check"></td>
	</tr>
	<tr>
	<td colspan="2">
	<input type="submit" value="확;인"></td>
	</tr>
	
	</table>
	
	
	</form>
	
	</div>
</body>
</html>