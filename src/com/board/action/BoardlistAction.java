package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardlistAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// DB의 게시글 전체 레코드를 조회하는 클래스
		// BoardDAO dao = BoardDAO.getInstance();
		// List<BoardDTO> list =dao.getBoardList();
		// request.setAttribute("List",list);
		
		
		//페이징 처리할거얌
		int rowsize = 3;//한페이지에 보여질 게시글의 수
		int block = 3 ; //아래에 보여질 페이지의 최대 수  -예) [1][2][3]> / <[4][5][6]>
		int totalRecord = 0; //DB상의 레코드 전체 수(게시물의 수)
		int allPage = 0; //전체 페이지수 == total / rowsize  *나머지가 발생하면 올림
		
		int page = 0; //초기값: 0
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}else {
			page = 1;// main.jsp페이지에서 처음으로 "게시물 목록" 태그를 클릭한 경우
		}
		
		//해당 페이지에서 시작 번호
		int startNo = (page * rowsize) - (rowsize - 1);
		//해당 페이지의 끝 번호
		int endNo = page * rowsize;
		
		//해당 페이지의 시작 블럭
		int startBlock = (((page-1)/block)*block)+1;
		//해당 페이지의 마지막블럭
		int endBlock = (((page-1)/block)*block)+block;
		
		BoardDAO dao = BoardDAO.getInstance();
		totalRecord =dao.getListCount();
		
		//전체 게시물의 수를 한 페이지당 보여질 게시물의 수로 나누어 주어야한다.
		//이 과정을 거치면 전체 페이지 수가 나온다.
		// 전체 페이지가 나올 때 나머지가 있으면 무조건 올림~~
		
		allPage = (int)Math.ceil((double)totalRecord/rowsize);
		
		if(endBlock >allPage) {// 마지막 블럭이 456니와서 endblock이 6인디 allpage가 5라면!?
			endBlock = allPage;
		}
	}

}
