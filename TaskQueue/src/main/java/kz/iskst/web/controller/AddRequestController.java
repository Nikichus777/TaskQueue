package kz.iskst.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kz.iskst.dao.UserDao;
import kz.iskst.dao.UserDaoImpl;
import kz.iskst.dao.UserRequestDao;
import kz.iskst.dao.UserRequestDaoImpl;
import kz.iskst.exception.DaoException;
import kz.iskst.exception.NoSuchEntityException;
import kz.iskst.model.User;
import kz.iskst.model.UserRequest;

public class AddRequestController extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	String problem = req.getParameter("userproblem");
	int priority = Integer.parseInt(req.getParameter("userpriority"));
	String login = (String) req.getSession().getAttribute("login");
	
	UserDao ud = new UserDaoImpl();
	UserRequestDao urd = new UserRequestDaoImpl();
	
	User user = new User("default","default@mail.ru");
	
	try {
	    user = ud.selectByLogin(login);
	    UserRequest ur = new UserRequest(user, problem, priority,new Date());
	    urd.insert(ur);
	    resp.sendRedirect("index.jsp");
	} catch (DaoException | NoSuchEntityException e) {
	    e.printStackTrace();
	    resp.sendRedirect("error.jsp");    
	}
	
    }

}
