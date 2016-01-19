package kz.iskst.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import kz.iskst.dao.UserDao;
import kz.iskst.dao.UserDaoImpl;
import kz.iskst.dao.UserRequestDaoImpl;
import kz.iskst.exception.DaoException;
import kz.iskst.exception.NoSuchEntityException;
import kz.iskst.model.User;

public class ValidationController extends HttpServlet {

	/**
	 * 
	 */
	private Logger logger = Logger.getLogger(ValidationController.class);
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Автоматически созданная заглушка метода
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.debug("Validation started");
		
		String userlogin = req.getParameter("userlogin");//req.getHeader("userlogin");
		String useremail = req.getParameter("useremail");
		PrintWriter pw = resp.getWriter();
		try {
		    if (!(new UserRequestDaoImpl().selectAll().isEmpty())){
			if(userlogin != null) checkUserLogin(req,resp,userlogin);
			if (useremail != null) checkUserEmail(req,resp,useremail);
			
		    }
		    else {
			
			resp.setHeader("userlogin", "0");
			pw.write("0");
			logger.debug("No one user exist");
		    }
		} catch (DaoException | NoSuchEntityException e) {
		    resp.setHeader("userlogin", "0");
		    pw.write("0");
		    logger.debug("ERROR DAO/NoSuchEnt-Excp");
		    e.printStackTrace();
		}			
		
	    
	}
	
	private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp, String userlogin){
		
		if (!Pattern.matches("^[A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*$", userlogin)){
			resp.setHeader("userlogin", "2");
		}
		else {
		UserDao ud = new UserDaoImpl();
		try {
			List<User> allUsers = ud.selectAll();
			PrintWriter pw = resp.getWriter();
			
			
			for (User us : allUsers){
				if (us.getLogin().equals(userlogin)){
					resp.setHeader("userlogin", "1");					
					
					pw.write("1");
					logger.debug("User allready exist");
					return;
				}
				else {
					resp.setHeader("userlogin", "0");
					pw.write("0");
					logger.debug("User not exist");
				}
			}
			
			pw.flush(); pw.close();
			
			
		} catch (DaoException e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
		} catch (NoSuchEntityException e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
		}
		}
	}

    private void checkUserEmail(HttpServletRequest req,
	    HttpServletResponse resp, String useremail) {
	Pattern pattern = Pattern
		.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	Matcher matcher = pattern.matcher(useremail);
	if (!matcher.matches()) {
	    resp.setHeader("useremail", "2");
	} else {
	    UserDao ud = new UserDaoImpl();
	    try {
		List<User> allUsers = ud.selectAll();
		PrintWriter pw = resp.getWriter();
		for (User us : allUsers) {
		    if (us.getEmail().equals(useremail)) {
			resp.setHeader("useremail", "1");
			pw.write("1");
			logger.debug("Email allready exist");
			return;
		    } else {
			resp.setHeader("useremail", "0");
			pw.write("0");
			logger.debug("Email not exist");
		    }
		}
		pw.flush();
		pw.close();

	    } catch (DaoException e) {
		// TODO Автоматически созданный блок catch
		e.printStackTrace();
	    } catch (NoSuchEntityException e) {
		// TODO Автоматически созданный блок catch
		e.printStackTrace();
	    } catch (IOException e) {
		// TODO Автоматически созданный блок catch
		e.printStackTrace();
	    }
	}
    }

}
