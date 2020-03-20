package com.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;

	// 싱글톤 객체 만들기
	// 1. 싱글톤 객체를 만들때는 우선적으로 접근지정자는 private으로 선언한다.
	// 2. 정적 멤버로 선언한다. -static으로 선언

	private static MemberDAO instance = new MemberDAO();// static:메서드 영역에 만들어진다아

	// 3. 기본생성자는 외부에서 접근이 되지 않도록 해야한다. -private으로 생성자 생성
	// 외부에서 new를 사용하지 못하게 하는 접근 기법.
	private MemberDAO() {
	}

	// 4. 생성자 대신에 싱글톤 객체를 return 해주는 getInstance() 메서드를 만들어 주자.
	public static MemberDAO getInstance() {// static에 있는 instance를 받아줘야하기때문에 static
		if (instance == null) {// 객체생성했기때문에 null일리가 없지만 혹시나모르니까아...
			instance = new MemberDAO();
		}
		return instance; // 참조변수 리턴!
	}

	public Connection openConn() {

		try {
			// 1. JNDI 서버 객체 생성
			InitialContext ic = new InitialContext();

			// 2.lookup() 메서드를 이용하여 매칭되는 커넥션을 찾는다.

			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle");

			// 3.DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다.
			con = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;

	}// openConn() end
	
	//DB상의 아이디와 비밀번호를 확인하는 메서드
	public int userCheck(String id,String pwd) {
		int result = 0;//회원 여부를 저장할 변수
		
		try {
			con = openConn();
			sql="select * from jsp_member where member_id=? and member_state=1"; 
			//member_state=1 : 현재 가입되어있는 회원 2:탈퇴했지만 아직 DB를 가지고 있는 회원
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(pwd.equals(rs.getString("member_pwd"))){//현재 홛동중인 회원인 경우
					result = 1;
				}else {//비밀번호가 틀린경우
					result = -1;
				}
			}else { //아이디가 없거나 탈퇴회원이거나!
				result = -2;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}	
		return result;
	}//userCheck() 메서드 end;
	
	//DB상에 인자로 넘어온 아이디에 해당하는 회원의 정보를 반환하는 메서드
	public MemberDTO getMember(String id) {
		MemberDTO dto = new MemberDTO();
		
		
		try {
			con= openConn();
			sql="select * from jsp_member where member_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setMember_id(rs.getString("member_id"));
				dto.setMember_pwd(rs.getString("member_pwd"));
				dto.setMember_name(rs.getString("member_name"));
				dto.setMember_nickname(rs.getString("member_nickname"));
				dto.setMember_zip1(rs.getString("member_zip1"));
				dto.setMember_zip2(rs.getString("member_zip2"));
				dto.setMember_addr1(rs.getString("member_addr1"));
				dto.setMember_addr2(rs.getString("member_addr2"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
}
