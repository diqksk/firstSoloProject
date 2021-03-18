package com.tarining.web.controller.gallery;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taining.web.entity.BoardView;
import com.taining.web.entity.BoardVo;
import com.taining.web.entity.GalleryVo;
import com.training.web.service.BoardService;
import com.training.web.service.GalleryService;

@WebServlet("/gallery/list")
public class ListController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page_=request.getParameter("p"); // 검색페이지
		
		int page = 1; //검색 기본 페이지 1페이지
		if(page_!=null && !page_.equals("")) page=Integer.parseInt(page_);
		
		GalleryService service = new GalleryService();
		List<GalleryVo> list = service.getAllList(page);
		int count = service.getGalleryCount();
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);

		request.getRequestDispatcher("/WEB-INF/view/gallery/gallist.jsp").forward(request,response);
	}
}
