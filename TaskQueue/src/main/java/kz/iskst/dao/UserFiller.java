package kz.iskst.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import kz.iskst.model.User;

public class UserFiller extends Filler<User> {
	private static final Logger logger = Logger.getLogger(UserFiller.class);
	/* 
	 * @see kz.iskst.dao.UserDaoImpl#UPDATE
	 * 
	 * SQL from  INSERT - "INSERT INTO `USER`(`ID`,`LOGIN`, `EMAIL`,`PASSWORD`,`NAME`,`SURNAME`,`PATRONYMIC`,`GROUP`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	 * Uses 8 parameters from <code>id</code> to <code>group</code> in direct order 
	 */
	@Override
	public void fillInsert(User object, PreparedStatement ps) {
		
		logger.debug("try to fill PrepredStatement user object");
		try{
		    	
			ps.setInt(1, object.getId());
			ps.setString(2, object.getLogin());
			ps.setString(3, object.getEmail());
			ps.setString(4, object.getPassword());
			ps.setString(5, object.getName());
			ps.setString(6, object.getSurname());
			ps.setString(7, object.getPatronymic());
			if (object.getGroup() != null)
			ps.setString(8, object.getGroup().toString());
			else ps.setString(8, null);
		}
		catch (SQLException se){
			System.out.println("UserFiller.fillInsert: Error");
			se.printStackTrace();			
		}
	}
	
	/*
	 * kz.iskst.dao.Filler#fillUpdate(java.lang.Object, java.sql.PreparedStatement)
	 * SQL = "UPDATE `jdbc`.`user` SET `login`=?, `email`=?,`PASSWORD`=?,`NAME`=?,`SURNAME`=?,`PATRONYMIC`=?,`GROUP`=? WHERE `id`=? and `login`=?";
	 * SQL uses 9 parameters: 7 (without <code>id</code>) in direct order from <code>login</code> to <code>group</code> and 2 after WHERE
	 */
	@Override
	public void fillUpdate(User object, PreparedStatement ps) {
		try{
	    	if (object.getId() == 0)	throw new RuntimeException("FOR UPDATE USER YOU NEED TO SET HIS ID");
	    	
			ps.setString(1, object.getLogin());
			ps.setString(2, object.getEmail() );
			ps.setString(3, object.getPassword());
			ps.setString(4, object.getName());
			ps.setString(5, object.getSurname());
			ps.setString(6, object.getPatronymic());
			ps.setString(7, object.getGroup().toString());
			ps.setInt(8, object.getId());
			ps.setString(9, object.getLogin());
			
		}
		catch (SQLException se){
			System.out.println("UserFiller.fillUpdate: Error");
			se.printStackTrace();
		}
		
	}
	
}
