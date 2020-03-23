<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 폼</title>

<%-- 회원가입 스타일 적용파일 링크 --%>
<link rel="stylesheet" href="css/member.css">
<%-- JQuery 라이브러리 링크 --%>
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<%-- 회원가입에 있어서 데이터를 검증하는 외부 자바 스크립트 파일 링크 --%>
<script type="text/javascript" src="js/member.js"></script>
<script type="text/javascript">

	//현재 웹문서가 브라우저로 로딩될때 문서의 본문(body)을 읽고 현재의 JQuery를 호출
	$(function(){
		//회원가입 폼 중에서 아이디 중복체크라는 버튼에 마우스가 올라갔을 때 호출되는 무명함수.
		$("#idcheck_btn").mouseover(function(){
			$("#idcheck").hide(); //span태그 idcheck영역을 숨겨라.
			var userId = $("#member_id").val();  //입력된 값을 가져와라
			
			//입력 길이 체크
			if($.trim($("#member_id").val()).length < 4){
				var warningTxt = 
					'<font color="red">아이디는 4자 이상이어야 합니다.</font>';
				$("#idcheck").text(''); //idcheck영역 초기화
				$("#idcheck").show(); //span태그 idcheck영역을 보이게 하자.
				$("#idcheck").append(warningTxt);
				$("#member_id").val('').focus();
				return false;
			}
			//입력 길이 체크
			if($.trim($("#member_id").val()).length > 16){
				var warningTxt = 
					'<font color="red">아이디는 16자 미만이어야 합니다.</font>';
				$("#idcheck").text(''); //idcheck영역 초기화
				$("#idcheck").show(); //span태그 idcheck영역을 보이게 하자.
				$("#idcheck").append(warningTxt);
				$("#member_id").val('').focus();
				return false;
			}
			//아이디 중복여부 확인-Ajax기술(비동기 통신)
			$.ajax({
				type : "post",//뎅;ㅣ터 전송방식(get,post방식)
				url : "idcheck.jsp",//파일의 주소와 경로
				data : {"userId" : userId},
				datatype : "jsp", //통신할 문서의 데이터 타입을 설정.
				
				//통신이 성공한 경우 결과값을 data라는 변수에 저장해주세요!!!!!!!!!!!!!
				success : function(data) {
					if(data == 1){//id가 이미 존재하는경우(중복인경우)
						var warningTxt = 
							'<font color="red">중~복~아이~띠~입니~다~~.</font>';
						$("#idcheck").text(''); //idcheck영역 초기화
						$("#idcheck").show(); //span태그 idcheck영역을 보이게 하자.
						$("#idcheck").append(warningTxt);
						$("#member_id").val('').focus();
						return false;
						
					}else { //id가 중복이 되지않는 경우
						var warningTxt = 
							'<font color="red">시용가능한 아이디 입니당</font>';
						$("#idcheck").text(''); //idcheck영역 초기화
						$("#idcheck").show(); //span태그 idcheck영역을 보이게 하자.
						$("#idcheck").append(warningTxt);
						$("#member_pass").val('').focus();
						return false;
					}
				},
				error : function(){//데이터 통신이 실패한 경우
					alert("data error~~~~~");
				}
				
				
			});//ajax의 end
		});
	});

</script>

</head>
<body>
	<div align="center">
		<hr width="50%" color="red">
		<h3>★☆※회※원※가※입※☆★</h3>
		<hr width="50%" color="red">
		<div id="join_wrap">
			<!-- mem_check()값이 false이면 action값으로 안넘어가! -->
			<form name="f" method="post" action="<%=request.getContextPath() %>/member_join_ok.do" onsubmit="return mem_check()">
				<table id="join_t">
					<tr>
						<th>회원아이디</th>
						<td>
							<input type="text" name="member_id" id="member_id" size="14"/>
							<input type="button" value="아이디중복체크" id="idcheck_btn"/>
							<br/>
							<!-- 중요한부분!!▽ 경고문이 출력되는 위치 -->
							<span id="idcheck"></span>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td>
							<input type="password" name="member_pass" id="member_pass" size="14"/>
						</td>
					</tr>
					<tr>
						<th>비밀번호확인</th>
						<td>
							<input type="password" name="member_pass2" id="member_pass2" size="14"/>
						</td>
					</tr>
					
					<tr>
						<th>회원이름</th>
						<td>
							<input type="text" name="member_name" id="member_name" size="14"/>
						</td>
					</tr>
					<tr>
						<th>닉넹미</th>
						<td>
							<input type="text" name="member_nickname" id="member_nickname" size="20"/>
						</td>
					</tr>
					<tr>
						<th>우편번호</th>
						<td>
							<input type="text" name="member_zip1" id="member_zip1" size="3" readonly onclick="post_search()"/>-
							<input type="text" name="member_zip2" id="member_zip2" size="3" readonly onclick="post_search()"/>
							<input type="button" value="우편번호찾기" onclick="post_check()"/>
						</td>
					</tr>
					
					<tr>
						<th>주소</th>
						<td>
							<input type="text" name="member_addr1" id="member_addr1" size="50" readonly onclick="post_search()"/>
						</td>
					</tr>
					<tr>
						<th>나머지주소</th>
						<td>
							<input type="text" name="member_addr2" id="member_addr2" size="50"/>
						</td>
					</tr>
				</table>
				
				<div id="join menu">
					<input type="submit" value="가입하기"/>&ensp;
					<input type="reset" value="다시작성"/>&ensp;
				</div><%-- id="join_menu" 의 끝! --%>
			</form>
		</div><%-- id="join_wrap" 의 끝! --%>
	</div>
</body>
</html>