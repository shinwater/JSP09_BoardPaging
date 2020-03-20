--jsp_member 테이블 생성

create table jsp_member(
	member_id varchar2(20) primary key,			-- 회원 아이디
	member_pwd varchar2(20) not null,			-- 회원 비밀번호
	member_name varchar2(30) not null,			-- 회원 이름
	member_nickname varchar2(50) not null,		-- 회원 별명
	member_zip1 varchar2(3) not null,			-- 우편번호(앞 3자리)
	member_zip2 varchar2(3) not null,			-- 우편번호(뒤 3자리)
	member_addr1 varchar2(100) not null,		-- 주소1
	member_addr2 varchar2(100) not null,		-- 주소2
	member_regdate date,						-- 회원 가입일
	member_state number(1),						-- 회원 상태(1,2)
	member_delcont varchar2(1000),				-- 회원 탈퇴 사유
	member_deldate date							-- 회원 탈퇴일

);

insert into jsp_member(member_id,member_pwd,member_name,member_nickname,member_zip1,member_zip2,member_addr1,
member_addr2,member_regdate,member_state) values('hong','1111','홍길동','의적','123','789','서울시 강남구 테헤란로','쌍용교육센터',sysdate,1);

insert into jsp_member(member_id,member_pwd,member_name,member_nickname,member_zip1,member_zip2,member_addr1,
member_addr2,member_regdate,member_state) values('leess','2222','이순신','충무공','123','789','서울시 강남구 테헤란로','쌍용교육센터',sysdate,1);

insert into jsp_member(member_id,member_pwd,member_name,member_nickname,member_zip1,member_zip2,member_addr1,
member_addr2,member_regdate,member_state) values('yooks','3333','유관순','열사','123','789','서울시 강남구 테헤란로','쌍용교육센터',sysdate,1);
