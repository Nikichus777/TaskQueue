package kz.iskst.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class UserRequestController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Cookie[] cookie = req.getCookies();
		System.out.println(cookie.toString());
		resp.getWriter().write("<html><body>trololo ololo decl loh</body></html>");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);
		
		//super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
	
}
