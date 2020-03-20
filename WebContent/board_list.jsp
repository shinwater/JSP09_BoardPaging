<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="css/bootstrap_3-3-2.css">
<style type="text/css">
table {
   border: 1px solid black;
   border-collapse: collapse; /* == cellspacing="0" */
   width: 350px;
}

th, td {
   border: 1px solid black;
   border-collapse: collapse; /* == cellspacing="0" */
   text-align: center;
}
</style>

</head>
<body>
	<div align="center">
		<%@ include file="include/header.jsp" %>
		<div class="clear"></div>
		
		<hr width="50%" color="red">
		<h3>MVC-2 모델 BOARD1 테이블 전체 리스트</h3>
		<hr width="50%" color="red">
		
		<table>
			<tr>
				<th>글번호</th><th>글제목</th>
				<th>조회수</th><th>등록일</th>
			</tr>
			
			<c:set var="list" value="${List }"></c:set>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getBoard_no() }</td>
						<td><a href="board_cont.do?no=${dto.getBoard_no()}&page=${page }">${dto.getBoard_title() }</a></td>
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
						<a href="board_list.do?page=1">◀◀</a></li>
					<li><a href="board_list.do?page=${startBlock-1 }">◀</a></li>
				</c:if>
				<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
					<li><a href="board_list.do?page=${i }">${i }</a></li>
				</c:forEach>
				
				<c:if test="${endBlock < allPage }">
					<li><a href="board_list.do?page=${endBlock+1 }">▶</a></li>
					<li class="paginate_button next">
						<a href="board_list.do?page=${allPage }">▶▶</a></li>
					
				</c:if>
			</ul>
		
		</div>
	</div>
</body>
</html>