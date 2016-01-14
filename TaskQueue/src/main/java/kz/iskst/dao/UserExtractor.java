package kz.iskst.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import kz.iskst.exception.DaoException;
import kz.iskst.model.User;

public class UserExtractor extends Extractor<User> {

	@Override
	public User extractOne(ResultSet rs) throws DaoException {
	    	try{
			    	
		User us = new User();
		us.setLogin(rs.getString("login"));
		us.setEmail(rs.getString("email"));
		us.setId(rs.getInt("id"));
		if (rs.getString("password") != null)
		us.setPassword(rs.getString("password"));
		if (rs.getString("name") != null)
		us.setName(rs.getString("name"));
		if (rs.getString("surname") != null)
		us.setSurname(rs.getString("surname"));
		if (rs.getString("patronymic") != null)
		us.setPatronymic(rs.getString("patronymic"));
		if (rs.getString("group") != null)
		us.setGroup(rs.getString("group"));
		
		return us;
		
	    	}
				
		
		//throw new DaoException("User-extractor: ResultSet is empty", new SQLException("ResultSet is empty"));
	    	
	    	catch (SQLException se){
	    		throw new DaoException("User-extractor: ResultSet is empty",se);
	    	}
	    
	}
}
