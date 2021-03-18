package com.tarining.web.controller.admin.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taining.web.entity.BoardView;
import com.taining.web.entity.BoardVo;
import com.training.web.service.BoardService;

@WebServlet("/admin/board/list")
public class ListController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] openIds = request.getParameterValues("open-id");
		String[] delIds = request.getParameterValues("del-id");
		String cmd = request.getParameter("cmd");
		String ids_= request.getParameter("ids");
		String[] ids = ids_.trim().split(" ");
		
		
		BoardService service = new BoardService();
		
		switch(cmd) {
		case "일괄공개" :
			for(String openId:openIds) 
				System.out.printf("open id : %s\n",openId);
			
			List<String> oids = Arrays.asList(openIds);
			
			List<String> cids = new ArrayList(Arrays.asList(ids));
			cids.removeAll(oids);
			System.out.println(Arrays.asList(ids));
			System.out.println(oids);
			System.out.println(cids);
				
			//transaction처리 (업무단위)
			service.pubBoardAll(oids,cids);
			
			break;
		case "일괄삭제" :
				
				int[] ids1 = new int[delIds.length];
				for(int i=0; i<delIds.length;i++)
					ids1[i] = Integer.parseInt(delIds[i]);
				int result = service.deleteBoardAll(ids1);
	
			break;
		}
		response.sendRedirect("list");

	}
	
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
		List<BoardView> list = service.getBoardList(field,query,page);
		int count = service.getBoardCount(field,query);
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);

		request.getRequestDispatcher("/WEB-INF/view/admin/boardlist.jsp").forward(request,response);
	}
	
	
}
