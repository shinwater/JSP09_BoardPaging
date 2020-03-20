package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardContAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 글제목을 클릭했을때 상세내용을 보여주는 클래스.
		
		int board_no = Integer.parseInt(request.getParameter("no"));
		int nowPage = Integer.parseInt(request.getParameter("page"));
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.boardHit(board_no); //조회수 증가 메서드 호출
		BoardDTO dto =dao.getCont(board_no); //게시물의 상세 내역을 조회하는 메서드 호출
		
		request.setAttribute("cont", dto);
		request.setAttribute("page", nowPage);
	}

}
