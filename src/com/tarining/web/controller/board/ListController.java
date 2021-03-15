package com.tarining.web.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taining.web.entity.BoardView;
import com.taining.web.entity.BoardVo;
import com.training.web.service.BoardService;

@WebServlet("/board/list")
public class ListController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String field_=request.getParameter("f");  //검색조건
		String query_=request.getParameter("q"); //검색문자
		String page_=request.getParameter("p"); // 검색페이지
		
		
		String field="title"; //기본은 title로 지정
		if(field_!=null&&!field_.equals("")) field=field_;
		
		String query = ""; //검색 기본 문자열 없음
		if(query_!=null && !query_.equals("")) query=query_;
		
		int page = 1; //검색 기본 페이지 1페이지
		if(page_!=null && !page_.equals("")) page=Integer.parseInt(page_);
		
		BoardService service = new BoardService();
		List<BoardView> list = service.getBoardPubList(field,query,page);
		int count = service.getBoardCount(field,query);
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);

		request.getRequestDispatcher("/WEB-INF/view/boardlist.jsp").forward(request,response);
	}
	
	
}
