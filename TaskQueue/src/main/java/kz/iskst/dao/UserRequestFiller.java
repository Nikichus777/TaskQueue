package kz.iskst.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import kz.iskst.model.UserRequest;

public class UserRequestFiller extends Filler<UserRequest> {
	private static final Logger logger = Logger.getLogger(UserRequestFiller.class);
	@Override
	public void fillInsert(UserRequest object, PreparedStatement ps) {
		logger.debug("try to FILLINSERT");
		try {
			ps.setInt(1, object.getId());
			ps.setString(2, object.getUser().getLogin());
			ps.setString(3, object.getProblemString());
			ps.setInt(4, object.getPriority());
			ps.setLong(5, object.getTimeLong());
			logger.debug("FILLINSERT succefull");
		}
		catch (SQLException se){
			System.out.println("UserRequestFiller: fillInsert failed");
			se.printStackTrace();
		}
	}

	@Override
	public void fillUpdate(UserRequest object, PreparedStatement ps) {
		logger.debug("try to FILLUPDATE");
		try {
			if (object.getId() == 0) throw new RuntimeException("FOR UPDATE USERREQUEST YOU NEED TO SET HIS ID");	
			//SQL = UPDATE = "UPDATE `jdbc`.`UserRequest` SET `USER`=?, `PROBLEM`=?, `PRIORITY`=?, `TIME`=? WHERE `id`=?";
			String login = object.getUser().getLogin();
			ps.setString(1, login);
			ps.setString(2, object.getProblemString());
			ps.setInt(3, object.getPriority());
			ps.setLong(4, object.getTimeLong());
			ps.setInt(5, object.getId());
			logger.debug("FILLUPDATE succefull");
		}
		catch (SQLException se){
			se.printStackTrace();
			System.out.println("UserRequestFiller: fillUpdate failed");
		}
		
	}

}
