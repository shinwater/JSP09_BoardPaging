<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="tomato"/>
		<h3>MVC-2 모델 게시판 글쓰기 폼</h3>
		<hr width="50%" color="tomato"/>
		<br/><br/><br/><br/><br/>
		
		<form method="post" action="<%=request.getContextPath() %>/board_write_ok.do">
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>작성자</th><!-- value="${name }" -->
					<td><input name="writer"></td>
				</tr>
				<tr>
					<th>글제목</th>
					<td><input name="title"></td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><textarea rows="7" cols="30" name="content"></textarea> </td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="글ㅆ크기"/>&ensp; 
						<input type="reset" value="취송"/>&ensp; 
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>