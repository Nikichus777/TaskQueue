package kz.iskst.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kz.iskst.dao.UserDao;
import kz.iskst.dao.UserDaoImpl;
import kz.iskst.exception.DaoException;
import kz.iskst.model.User;
import kz.iskst.model.User.Group;

public class addUserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User newser = new User();
		newser.setLogin(req.getParameter("userlogin"));
		newser.setEmail(req.getParameter("useremail"));
		newser.setName(req.getParameter("username"));
		newser.setSurname(req.getParameter("usersurname"));
		newser.setPatronymic(req.getParameter("userpatronimyc"));
		newser.setGroup(req.getParameter("usergroup"));
		newser.setPassword(req.getParameter("userpassword1"));
		
		UserDao ud = new UserDaoImpl();
		try {
			ud.insert(newser);
			resp.sendRedirect("login.html");
		} catch (DaoException e) {
			// TODO Автоматически созданный блок catch
			resp.sendRedirect("error.jsp");
		}
		
	}

}
