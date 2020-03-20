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
		<form action="board_edit_ok.do">
			
			<input type="hidden" name="page" value="${page }">
			<c:set var="dto" value="${dto }"/>
			<input type="hidden" name="dto_pwd" value="${dto.getBoard_pwd() }">
			<input type="hidden" name="no" value="${dto.getBoard_no() }">
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>글번호</th>
					<td>${dto.getBoard_no() }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer" value="${dto.getBoard_writer() }"> </td>
				</tr>
				<tr>
					<th>글제목</th>
					<td><input type="text" name="title" value="${dto.getBoard_title() }"> </td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><textarea rows="7" cols="30" name="cont">${dto.getBoard_cont() } </textarea></td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${dto.getBoard_hit() } </td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${dto.getBoard_regdate() } </td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="pwd" required></td>
				</tr>
				
				
				<tr>
					
					<td colspan="2" align="center">
						<input type="submit" value="정보수정"> 
						<input type="reset" value="다시쓰기"> 
						<input type="button" value="돌아가기" onclick="location.href='board_list.do?page=${page }'"> 
					</td>
				</tr>
			</table>
		</form>
	
	</div>
</body>
</html>