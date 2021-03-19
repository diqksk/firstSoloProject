package com.tarining.web.controller.gallery;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.training.web.service.BoardService;
import com.training.web.service.GalleryService;


@WebServlet("/gallery/delete")
public class DeleteController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("id");
		request.setAttribute("id", id);
		request.getRequestDispatcher("/WEB-INF/view/galUpdateDelete/delete.jsp").forward(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String pwd = request.getParameter("pwd");
		System.out.println(pwd);
		GalleryService service = new GalleryService();
		boolean check=service.checkPassword(id, pwd);
		if(check) {
			try {
			service.deleteGallery(id);
			}
			catch (Exception e) {
				System.out.println("삭제시 오류발생.");
				response.sendRedirect("/gallery/list");
			}
			response.sendRedirect("/gallery/list");
		}else {
			System.out.println("뭔가맞지않습니다.");
			response.sendRedirect("/gallery/detail?id="+id);
		}
	}
	

}
