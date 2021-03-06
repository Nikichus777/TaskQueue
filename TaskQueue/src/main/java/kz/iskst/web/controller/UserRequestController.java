package kz.iskst.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kz.iskst.dao.UserDao;
import kz.iskst.dao.UserDaoImpl;
import kz.iskst.dao.UserRequestDao;
import kz.iskst.dao.UserRequestDaoImpl;
import kz.iskst.exception.DaoException;
import kz.iskst.exception.NoSuchEntityException;
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
	StringBuilder error = new StringBuilder("");
	UserRequestDao urd = new UserRequestDaoImpl();
	List<UserRequest> list = new ArrayList<UserRequest>();
	HttpSession session = req.getSession(false);
	if (session != null){
    		if (session.getAttribute("login") == null){
    		    session.removeAttribute("login");			    
    		}
	}
	
	/*UserDao ud = new UserDaoImpl();
	User user = new User();
	user.setEmail("emylo@mm.ss");
	user.setGroup(Group.GEODESISTS);
	user.setName("Michael155");
	user.setPassword("mypass");
	user.setSurname("Shumaher");
	user.setPatronymic("Batkovich");*/
	
	
	//HttpSession session = req.getSession();
	//session.setAttribute("login", "molodec");
	/*
	 try {
	   
	} catch (EmailIncorrectException e1) {
	    e1.printStackTrace();
	    error.append("������������ ������ Email \n");
	} catch (LoginIncorrectException e2) {
	    error.append("������������ ������ Login \n");
	    e2.printStackTrace();
	}*/
	
	
	try {
	  //  ud.insert(user);
	 
	   /* UserRequest ureq = new UserRequest();
	    ureq.setPriority(5);
	    ureq.setProblem(Problems.INDORCAD);
	    ureq.setUser(user);
	    urd.insert(ureq);*/
	    list = urd.selectAll();
	    
	} catch (DaoException | NoSuchEntityException e) {
	    error.append("��������� ������ ��� ������ � ����� ������ \n");
	    /*
	     * PrintWriter pw = resp.getWriter(); pw.write(
	     * "<html><head><title>ERROR, DataBase not not allowed!</title></head><body>������! ���� ������ �� ��������!</body></html>"
	     * ); pw.flush(); pw.close();
	     */
	}
	String test = "it's all ok";
	req.setAttribute("test", test);
	req.setAttribute("error", error);
	req.setAttribute("allRequestList", list);
	resp.setContentType("text/html; charset=UTF-8");
	RequestDispatcher dispatcher = req.getRequestDispatcher("/hidden.jsp");
	dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	doGet(req, resp);

    }

}
