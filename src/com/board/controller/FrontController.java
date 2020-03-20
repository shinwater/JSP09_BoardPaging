package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.action.*;


public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 5679718683735199043L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 한글인코딩 처리- ~.do 객체는 전부오니깐.. 내가 지금안써도 꼭 만들어주긔
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		// getRequestURI():"/프로젝트명/파일명(*.do)"라는 문자열을 반환하는 메서드
		String uri = request.getRequestURI();
		System.out.println("URI ==> " + uri);

		// getContextPath() : 현재 프로젝트명을 문자열로 반환하는 메서드
		String path = request.getContextPath();
		System.out.println("path ==> " + path);

		String command = uri.substring(path.length() + 1);
		System.out.println(command);

		Action action = null;
		String viewPage = null;
		
		
		if(command.equals("login.do")) {
			//단순히 로그인 페이지만 보여주면 되니깐!
			viewPage = "login.jsp";
		}else if(command.equals("login_ok.do")) {
			action = new LoginOkAction();
			action.execute(request, response);
		}else if(command.equals("board_list.do")) {
			action = new BoardlistAction();
			action.execute(request, response);
		}
		
		
		
		
			//페이지이동
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}
}
