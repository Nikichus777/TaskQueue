package kz.iskst.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import kz.iskst.model.User;
import kz.iskst.model.UserRequest;

/**
 * 
 * @author npk
 * There are 2 ways to reestablish object User in field user:
 * get it from UserDaoImpl and  create Enricher and get it from Enricher
 * In second way we must create object User with null fields and enrich them from Enricher
 */
public class UserRequestExtractor extends Extractor<UserRequest> {

	@Override
	public UserRequest extractOne(ResultSet rs) throws DaoException {
		
		try{
		
		    //UserDaoImpl udi = new UserDaoImpl();
		    //User user = udi.selectByLogin(rs.getString(2));
		    
		    User user = new User();
		    user.setLogin(rs.getString("user"));
		    //user.setEmail(null);
		    //user.setLogin(null);
		    UserRequest ureq = new UserRequest(user,rs.getString("problem"),rs.getInt("priority"),rs.getLong("time"));
		    ureq.setId(rs.getInt("id"));
		
		    return ureq;
				
		}
		catch(SQLException se){
		   throw new DaoException("UserRequestExtractor: ResultSet is empty!!",se);	 
		}
		
		
	}


}
