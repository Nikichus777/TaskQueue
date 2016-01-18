package kz.iskst.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kz.iskst.dao.UserDao;
import kz.iskst.dao.UserDaoImpl;
import kz.iskst.exception.DaoException;
import kz.iskst.exception.NoSuchEntityException;
import kz.iskst.model.User;

public class ValidationController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Автоматически созданная заглушка метода
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String userlogin = req.getHeader("userlogin");
		UserDao ud = new UserDaoImpl();
		try {
			List<User> allUsers = ud.selectAll();
			for (User us : allUsers){
				if (us.getLogin().equals(userlogin)){
					resp.setHeader("userlogin", "1");
				}
				else resp.setHeader("userlogin", "0");
			}
			RequestDispatcher rd = req.getRequestDispatcher("registration.html");
			rd.forward(req, resp);
			
		} catch (DaoException e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
		} catch (NoSuchEntityException e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
		}
		
		
	}

}
