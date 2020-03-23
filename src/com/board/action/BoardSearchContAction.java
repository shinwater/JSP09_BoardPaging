package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardSearchContAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 검색리스트에서 글제목을 클릭시 상세내용을 보여주는 클래스
		int board_no =  Integer.parseInt(request.getParameter("no"));
		int nowPage =  Integer.parseInt(request.getParameter("page"));
		String find_field = request.getParameter("field");
		String find_name = request.getParameter("name");
		
		BoardDAO dao = BoardDAO.getInstance();
		//게시글 상세내역 메서드호출
		BoardDTO dto = dao.getCont(board_no);
		
		request.setAttribute("search", dto);
		request.setAttribute("page", nowPage);
		request.setAttribute("find_field", find_field);
		request.setAttribute("find_name", find_name);
		

	}

}
