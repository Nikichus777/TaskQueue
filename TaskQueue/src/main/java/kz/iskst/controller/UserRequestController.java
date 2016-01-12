package kz.iskst.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kz.iskst.dao.DaoException;
import kz.iskst.dao.NoSuchEntityException;
import kz.iskst.dao.UserDao;
import kz.iskst.dao.UserDaoImpl;
import kz.iskst.dao.UserRequestDao;
import kz.iskst.dao.UserRequestDaoImpl;
import kz.iskst.model.User;
import kz.iskst.model.User.Group;
import kz.iskst.model.UserRequest;
import kz.iskst.model.UserRequest.Problems;



public class UserRequestController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserRequestDao urd = new UserRequestDaoImpl();
		UserDao ud = new UserDaoImpl();
		User user = new User("Michael", "michael@mail.rr");
		user.setGroup(Group.GEODESISTS);
		user.setName("Michael");
		user.setPassword("mypass");
		user.setSurname("Shumaher");
		user.setPatronymic("Batkovich");
		List<UserRequest> list = new ArrayList<UserRequest>();
		try {
		    ud.insert(user);
		    System.out.println("test");
		
		    UserRequest ureq = new UserRequest();
		    ureq.setPriority(5);
		    ureq.setProblem(Problems.INDORCAD);
		    ureq.setUser(user);
		    urd.insert(ureq);
		    list = urd.selectAll();
			
		} catch (DaoException | NoSuchEntityException e) {
			
			/*PrintWriter pw = resp.getWriter();
			pw.write("<html><head><title>ERROR, DataBase not not allowed!</title></head><body>Ошибка! База данных не доступна!</body></html>");
			pw.flush();
			pw.close();*/
		}
		
		
		req.setAttribute("allRequestList", list);
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
