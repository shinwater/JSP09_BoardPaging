<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="css/bootstrap_3-3-2.css">

</head>
<body>
	<div align="center">
		<%@ include file="include/header.jsp" %>
		<div class="clear"></div>
		
		<hr width="50%" color="red">
		<h3>MVC-2 모델 BOARD1 테이블 검색 리스트</h3>
		<hr width="50%" color="red">
		
		<table border="0" cellspacint="0" width="400">
			<tr>
				<th>글번호</th><th>글제목</th>
				<th>조회수</th><th>등록일</th>
			</tr>
			
			<c:set var="list" value="${searchList }"></c:set>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getBoard_no() }</td>
						<td><a href="board_searchCont.do?no=${dto.getBoard_no()}&page=${page }&field=${find_field}&name=${find_name}">${dto.getBoard_title() }</a></td>
						<td>${dto.getBoard_hit() }</td>
						<td>${dto.getBoard_regdate().substring(0,10) }</td>
					</tr>
				</c:forEach>
			
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="4">
						<h3>검색된 레코드가 없습니다.</h3>
					</td>
				</tr>	
			</c:if>
			
			<tr>
				<td colspan="4" align="right">
					<input type="button" value="글쓰기"
						onclick="location.href='<%=request.getContextPath() %>/board_write.do'">
				</td>
			</tr>
		</table>
		
		<div>
			<ul class="pagination"><!-- 순서없는 목록 -->
				<c:if test="${page >block }">
					<li class="paginate_button previous">
						<a href="board_search.do?page=1&find_field=${find_field }&find_name=${find_name}">◀◀</a></li>
					<li><a href="board_search.do?page=${startBlock-1 }&find_field=${find_field }&find_name=${find_name}">◀</a></li>
				</c:if>
				
				<!-- 기억해애  -->
				<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
					<c:if test="${i == page }"><!--현재페이지일떄  -->
					<li class="active"><!-- 클릭시 색깔바뀜ㅇ -->
					<a href="board_search.do?page=${i }&find_field=${find_field }&find_name=${find_name}">${i }</a></li>
					</c:if>
					<c:if test="${i != page}">
					<li><a href="board_search.do?page=${i }&find_field=${find_field }&find_name=${find_name}">${i }</a></li>
					</c:if>
				</c:forEach>
				
				<c:if test="${endBlock < allPage }">
					<li><a href="board_search.do?page=${endBlock+1 }&find_field=${find_field }&find_name=${find_name}">▶</a></li>
					<li class="paginate_button next">
						<a href="board_search.do?page=${allPage }&find_field=${find_field }&find_name=${find_name}">▶▶</a></li>
					
				</c:if>
				
			</ul>
		</div>
		<div align="center">
			<input type="button" value="전체 목록" onclick="location.href='<%=request.getContextPath()%>/board_list.do'"></div>
	</div>
</body>
</html>