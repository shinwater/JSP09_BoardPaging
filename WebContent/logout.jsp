<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

		//현재 로그인된 사용ㅈ의 모든 세션 정보를 만료시켜야함.
		session.invalidate(); //모든세션의 속성을 만료시키는 메서드!!!! 밙드시 로그아웃에 써줘야함!
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		alert("로그아웃되었습니당");
		location.href="login.do";
		
	</script>
</body>
</html>