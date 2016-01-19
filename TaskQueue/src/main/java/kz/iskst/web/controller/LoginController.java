package kz.iskst.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import kz.iskst.dao.UserDao;
import kz.iskst.dao.UserDaoImpl;
import kz.iskst.exception.DaoException;
import kz.iskst.exception.NoSuchEntityException;
import kz.iskst.model.User;

public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Logger logger = Logger.getLogger(LoginController.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.debug("LOGIN STARTED");
		UserDao ud = new UserDaoImpl();
		try {
			User us = ud.selectByLogin(req.getParameter("login"));
			if (us != null){
			if (us.getPassword().equals(req.getParameter("password"))){
								
				HttpSession  session = req.getSession(true);
				session.setAttribute("login", us.getLogin());
				logger.debug("SESSION SETATTRIBUTE!!");
				//req.getRequestDispatcher("/index.jsp").forward(req, resp);
				//req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
				logger.debug("RESPONSE SEND REDIRECT");
				resp.addHeader("login", "2");
				//resp.sendRedirect("index.jsp");				
			}
			else resp.addHeader("login", "1");
			} 
							
			
		} catch (DaoException | NoSuchEntityException e) {
			resp.addHeader("login", "1");
			
			e.printStackTrace();
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doGet(req, resp);
	}

}
