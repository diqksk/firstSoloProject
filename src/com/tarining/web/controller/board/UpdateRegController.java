package com.tarining.web.controller.board;

import java.io.File; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import com.taining.web.entity.BoardVo;
import com.training.web.service.BoardService;

@MultipartConfig(
		fileSizeThreshold = 1024*1024,
		maxFileSize = 1024*1024*50,
		maxRequestSize = 1024*1024*50*5
		) //filesizethreshold 크기 이상의 용량이면 disk 사용하도록함
@WebServlet("/board/updatereg")
public class UpdateRegController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/boardlist.jsp").forward(request,response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		content=content.replace("\r\n", "<br>");
		String writer = request.getParameter("writer");
		int p = Integer.parseInt(request.getParameter("p"));
		int id= Integer.parseInt(request.getParameter("id"));
		BoardService service = new BoardService();
		
		
		BoardVo vo = new BoardVo(); 
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		vo.setId(id);
		
		service.updateBoard(vo);
		response.sendRedirect("detail?id="+id+"&p="+p);
	}
}
