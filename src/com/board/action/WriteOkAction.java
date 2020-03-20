package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class WriteOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String writer=request.getParameter("writer").trim();
		String title=request.getParameter("title").trim();
		String content=request.getParameter("content").trim();
		String pwd=request.getParameter("pwd").trim();
		
		BoardDTO dto = new BoardDTO();
		dto.setBoard_writer(writer);
		dto.setBoard_title(title);
		dto.setBoard_cont(content);
		dto.setBoard_pwd(pwd);
		
		BoardDAO dao = BoardDAO.getInstance();
		int res =dao.boardWrite(dto);
		
		PrintWriter out = response.getWriter();
		if(res == 1) {
			out.println("<script>");
			out.println("alert('글쓰기 성공')");
			out.println("location.href='board_list.do'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('글쓰기 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
				
	}

}
