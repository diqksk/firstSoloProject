package com.tarining.web.controller.admin.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taining.web.entity.CommentVo;
import com.training.web.service.CommentService;

@WebServlet("/board/CmtController")
public class CmtController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int board_id= Integer.parseInt(request.getParameter("id"));
		PrintWriter out = response.getWriter();
		out.write(getJSON(board_id));
		System.out.println(getJSON(board_id));
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int board_id =Integer.parseInt(request.getParameter("id"));
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		System.out.println(writer);
		CommentVo vo = new CommentVo();
		vo.setBoardID(board_id);
		vo.setCcontent(content);
		vo.setCwriter(writer);
		CommentService service = new CommentService();
		service.insertComment(vo);
		PrintWriter out = response.getWriter();
		out.write(getJSON(board_id));
		
	}
	
	public String getJSON(int board_id) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\" : [");
		CommentService cmtservice = new CommentService();
		ArrayList<CommentVo> CommentList = cmtservice.getComment(board_id);
		for(int i = 0 ; i < CommentList.size();i++) {
			result.append("[{\"value\" : \""+CommentList.get(i).getCwriter()+"\"},");
			result.append("{\"value\" : \""+CommentList.get(i).getCcontent()+"\"},");
			result.append("{\"value\" : \""+CommentList.get(i).getCregdate()+"\"}],");
		}
		result.append("]}");
		return result.toString();
	}

}
