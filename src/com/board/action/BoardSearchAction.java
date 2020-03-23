package com.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardSearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 검색창에서 넘어온 데이터를 가지고 검색어에 
		//해당하ㅡㄴ 리스트를 DB에서 조회하여 뷰페이지로 이동시키는 클래스
			
		
		String find_field = request.getParameter("find_field");
		String find_name = request.getParameter("find_name").trim();
		
		//페이징 처리 기법
		int rowsize= 3; //한페이지당 보여질 게시물의 수
		int block=3; //아래에 보여질 페이지의 최대수. 예)[1][2][3]/[4][5][6]
		int totalRecord =0; //DB상의 레코드 전체수(게시글의 수)저장
		int allPage = 0;//전체 페이지 수 - totalRecord/rowsize
		int page =1; //현재 페이지 번호
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		//해당페이지에서의 시작번호
		int startNo = (page*rowsize) -(rowsize-1);
		//해당페이지에서 끝번호
		int endNo = (page*rowsize);
		
		//해당페이지의 시작 블럭
		int startBlock =(((page-1)/block)*block)+1;
		//해당페이지의 끝 블럭
		int endBlock=(((page-1)/block)*block)+block;
		
		BoardDAO dao = BoardDAO.getInstance();
		totalRecord = dao.searchListCount(find_field,find_name);
		
		//검색된 전체 게시물의 수를 한 페이지당 보여질 게시물의 수로 나누어주면 도니다
		//이런 작업을 거치면 전체 페이지 수가 나온다.
		//전체 페이지가 나올 때 나머지가 있으면 무조건 올림이 되어야 한당
		allPage = (int)Math.ceil(totalRecord/(double)rowsize);
		
		if(endBlock>allPage) {
			endBlock = allPage;
		}
		
		List<BoardDTO> list = dao.searchBoardList(find_field,find_name,page,rowsize);
		
		//페이지 처리 작업시 사용했던 모든 값들ㅇ릉 키로 저장헤ㅐ주자
		request.setAttribute("page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("find_field", find_field);
		request.setAttribute("find_name", find_name);
		request.setAttribute("searchList", list);
	}

}
