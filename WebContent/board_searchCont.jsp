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
		<hr width="50%" color="skyblue">
		<h3>MVC-2 모델 게시판 검색게시글 상세내역</h3>
		<hr width="50%" color="skyblue">
		
		<table border="1" cellspacing="0" width="400">
			<c:set var="dto" value="${search }"/>
			<c:if test="${!empty dto }">
				<tr>
					<th>작성자</th>
					<td>${dto.getBoard_writer() }</td>
				</tr>
				<tr>
					<th>글제목</th>
					<td>${dto.getBoard_title() }</td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><textarea rows="7" cols="30" readonly> ${dto.getBoard_cont() }</textarea></td>
				</tr>
				<tr>
					<th>조회수</th>
					<td> ${dto.getBoard_hit() }</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td> ${dto.getBoard_regdate() }</td>
				</tr>
			</c:if>
			<c:if test="${empty dto }">
				<tr>
					<td colspan="2" align="center"> 검색된 레코드가 없습니다.</td>
				</tr>
			
			</c:if>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="검색목록"
							onclick="location.href='board_search.do?page=${page }&find_field=${find_field }&find_name=${find_name }'">
						<input type="button" value="전체목록"
							onclick="location.href='board_list.do?'">
						</td>
				</tr>
		
		</table>
	</div>
</body>
</html>