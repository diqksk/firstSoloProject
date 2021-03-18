package com.tarining.web.controller.admin.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.training.web.service.AccountService;

@WebServlet("/admin/login")
public class LoginController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/admin/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AccountService service = new AccountService();
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String access = request.getParameter("access");
		if (access.equals("logout")) {
			session.invalidate();
			response.sendRedirect("/admin/login");
		} else {
			boolean check = service.LoginCheck(id, password);
			if (check) {
				session.setAttribute("login_id", id);
				System.out.println("세션성공");
				response.sendRedirect("/admin/board/list");
			} else {
				System.out.println("세션실패");
			}
		}
	}
}
