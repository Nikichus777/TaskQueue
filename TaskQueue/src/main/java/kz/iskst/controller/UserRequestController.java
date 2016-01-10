package kz.iskst.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kz.iskst.dao.DaoException;
import kz.iskst.dao.NoSuchEntityException;
import kz.iskst.dao.UserDaoImpl;
import kz.iskst.model.User;



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
		User us = new User("Miikee","Friday@mail.dds");
		req.setAttribute("us", us);
		req.setAttribute("JJJ", "OOOO");
		resp.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/hidden.jsp");
		dispatcher.forward(req, resp);
				
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserDaoImpl udi = new UserDaoImpl();
		try {
			List<User> userList = udi.selectAll();
			req.getParameter("");
		} catch (DaoException | NoSuchEntityException e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
		}
		
	}
	
}
