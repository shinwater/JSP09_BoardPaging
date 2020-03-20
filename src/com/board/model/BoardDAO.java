package com.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sun.org.apache.xml.internal.dtm.DTMDOMException;

public class BoardDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;

	// 싱글톤 객체 만들기
	// 1. 싱글톤 객체를 만들때는 우선적으로 접근지정자는 private으로 선언한다.
	// 2. 정적 멤버로 선언한다. -static으로 선언

	private static BoardDAO instance = new BoardDAO();// static:메서드 영역에 만들어진다아

	// 3. 기본생성자는 외부에서 접근이 되지 않도록 해야한다. -private으로 생성자 생성
	// 외부에서 new를 사용하지 못하게 하는 접근 기법.
	private BoardDAO() {
	}

	// 4. 생성자 대신에 싱글톤 객체를 return 해주는 getInstance() 메서드를 만들어 주자.
	public static BoardDAO getInstance() {// static에 있는 instance를 받아줘야하기때문에 static
		if (instance == null) {// 객체생성했기때문에 null일리가 없지만 혹시나모르니까아...
			instance = new BoardDAO();
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

	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// board 테이블의 전체 게시물의 수를 조회하는 메서드
	public int getListCount() {
		int count = 0;

		try {
			openConn();
			sql = "select count(*) from board1";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return count;
	}// getListCount() 메서드 end

	// board1 테이블에서 게시물을 가져오는 메서드
	public List<BoardDTO> getBoardList(int page, int rowsize) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();

		// 해당 페이지에서 시작 번호
		int startNo = (page * rowsize) - (rowsize - 1);
		// 해당 페이지의 끝 번호
		int endNo = page * rowsize;
		
		
		
		try {
			openConn();
			
			//row_number() over : 특정 컬럼을 기준으로 행 번호를 부여할 때 사용하는 함수.
			sql = "select * from "
					+ "(select p.*, row_number() "
					+ "over(order by board_no desc) rnum "
					+ "from board1 p)"
					+ "where rnum >=? and rnum <=?";//중~~요 ():서브쿼리
			
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
	            dto.setBoard_pwd(rs.getString("board_pwd"));
	            dto.setBoard_hit(rs.getInt("board_hit"));
	            dto.setBoard_regdate(rs.getString("board_regdate"));
	            
	            list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}//getBoardList() end;
	
	
	//board1 테이블에 게시글을 추가하는 메서드
	public int boardWrite(BoardDTO dto) {
		int res = 0, count = 1;
		
		try {
			openConn();
			con.setAutoCommit(false);
			sql = "select max(board_no) from board1";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}//초기엔 count=1;
			
			//실제 DB에 게시물을 ㅈㅓ장
			sql="insert into board1 values(?,?,?,?,?,default,sysdate)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getBoard_writer());
			pstmt.setString(3, dto.getBoard_title());
			pstmt.setString(4, dto.getBoard_cont());
			pstmt.setString(5, dto.getBoard_pwd());
			
			res = pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return res;
	}//boardWrite() end;
	
	//board1 테이블의 게시물의 조회수를 증가시키는 메서드
	public void boardHit(int no) {
		try {
			openConn();
			sql ="update board1 set board_hit= board_hit + 1 where board_no=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
	}//boardHit() end
	
	//board1테이블의 게시물번호에 해당하는 글을 조회하는 메서드
	public BoardDTO getCont(int no) {
		BoardDTO dto = new BoardDTO();
		
		try {
			openConn();
			sql="select * from board1 where board_no=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
	            dto.setBoard_pwd(rs.getString("board_pwd"));
	            dto.setBoard_hit(rs.getInt("board_hit"));
	            dto.setBoard_regdate(rs.getString("board_regdate"));
	            
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return dto;
	}//getCont() end;

	//board1테이블에 정보를 수정하는 메서드
	public int boardUpdate(BoardDTO dto) {
		int res = 0;
		
		try {
			openConn();
			con.setAutoCommit(false);
			sql= "update board1 set board_writer=?, board_title=?, board_cont=? where board_no=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getBoard_writer());
			pstmt.setString(2, dto.getBoard_title());
			pstmt.setString(3, dto.getBoard_cont());
			pstmt.setInt(4, dto.getBoard_no());
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return res;
	}//boardUpdate() end
}
