package com.tarining.web.controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taining.web.entity.BoardVo;
import com.training.web.service.BoardService;


@WebServlet("/board/update")
public class UpdateController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("id");
		request.setAttribute("id", id);
		request.getRequestDispatcher("/WEB-INF/view/updatedelete/update.jsp").forward(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String pwd = request.getParameter("pwd");
		BoardService service = new BoardService();
		boolean check=service.checkPassword(id, pwd);
		if(check) {
			BoardVo vo = service.getBoard(id);
			request.setAttribute("n", vo);
			request.setAttribute("id", id);
			request.getRequestDispatcher("/WEB-INF/view/updatedelete/updatereg.jsp").forward(request,response);
		}else {
			System.out.println("뭔가맞지않습니다.");
			response.sendRedirect("/board/detail?id="+id);
		}
	}

}
