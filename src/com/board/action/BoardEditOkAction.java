package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardEditOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 비밀번호가 일치하면 setter메서드로 데이터넣어주고 DB로 전송후  전송결과 알려주기!
		
		int page =Integer.parseInt(request.getParameter("page"));
		int no =Integer.parseInt(request.getParameter("no"));
		String dto_pwd= request.getParameter("dto_pwd");
		String writer= request.getParameter("writer");
		String title= request.getParameter("title");
		String cont= request.getParameter("cont");
		String pwd= request.getParameter("pwd");
		
		
		PrintWriter out =response.getWriter();
		if(dto_pwd.equals(pwd)) {
			BoardDTO dto = new BoardDTO();
			dto.setBoard_no(no);
			dto.setBoard_writer(writer);
			dto.setBoard_title(title);
			dto.setBoard_cont(cont);
			
			BoardDAO dao = BoardDAO.getInstance();
			int res =dao.boardUpdate(dto);
			
			if(res == 1) {
				out.println("<script>");
				out.println("alert('수정 성공')");
				out.println("location.href='board_list.do?page="+page+"'");
				out.println("</script>");
			}else {
				out.println("<script>");
				out.println("alert('수정실패')");
				out.println("history.back()");
				out.println("</script>");
			}
			
		
		}else {
			out.println("<script>");
			out.println("alert('비밀번호가 틀렸어요!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		

	}

}
