package com.tarining.web.controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taining.web.entity.BoardVo;
import com.taining.web.entity.CommentVo;
import com.training.web.service.BoardService;
import com.training.web.service.CommentService;


@WebServlet("/board/detail")
public class DetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		int p = Integer.parseInt(request.getParameter("p"));
		BoardService service = new BoardService();
		BoardVo vo = service.getBoard(id);
		BoardVo prevo = service.getPreBoard(id);
		BoardVo nextvo = service.getNextBoard(id);
		
		request.setAttribute("p",p);
		request.setAttribute("n", vo);
		request.setAttribute("pn", prevo);
		request.setAttribute("nn", nextvo);

		
		//forward -> 작업했던 내용을 이어받아서 화면을 전이시키는애
		request.getRequestDispatcher("/WEB-INF/view/boardDetail.jsp").forward(request, response);;
	}
	
	
}
