package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardDeleteOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		int page = Integer.parseInt(request.getParameter("page"));
		String check = request.getParameter("check");
		
		BoardDAO dao= BoardDAO.getInstance();
		BoardDTO dto =dao.getCont(no);
		
		
		PrintWriter out = response.getWriter();
		if(dto.getBoard_pwd().equals(check)) {
			int res=dao.boardDelete(no);
			
			if(res==1) {//삭제성공
				out.println("<script>");
				out.println("alert('삭제성공!')");
				out.println("location.href='board_list.do?page="+page+"'");
				out.println("</script>");
			}else {
				out.println("<script>");
				out.println("alert('삭제실패!')");
				out.println("history.back()");
				out.println("</script>");
			}
		}else {
			out.println("<script>");
			out.println("alert('비밀번호를 틀렸습니당')");
			out.println("history.back()");
			out.println("</script>");
		}

	}

}
