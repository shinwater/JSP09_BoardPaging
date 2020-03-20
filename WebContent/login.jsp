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
		<hr width="50%" color="tomato">
		<h3>회원 로그인 화면</h3>
		<hr width="50%" color="tomato">
		
		<form method="post" action="<%=request.getContextPath()%>/login_ok.do"
			onsubmit="return login_check()">
			<table>
				<tr>
					<th>아이디</th>
					<td><input name="id"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="로그인"/>&emsp;
						<input type="reset" value="취소"/>&emsp;
						<input type="button" value="회원가입" onclick="location.href='join.do'"/>&emsp;
						<input type="button" value="비밀번호찾기" onclick="pwd_find()"/>&emsp;
						
					</td>
				</tr>
			
			
			
			
			
			</table>
		</form>
	</div>
</body>
</html>