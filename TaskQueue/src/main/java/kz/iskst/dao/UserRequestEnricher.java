package kz.iskst.dao;

import java.sql.SQLException;
import java.util.List;

import kz.iskst.model.User;
import kz.iskst.model.UserRequest;

public class UserRequestEnricher extends Enricher<UserRequest> {

    @Override
    public void enrich(UserRequest record) {
	UserDao udi = new UserDaoImpl();
	try {
	    User user = udi.selectByLogin(record.getUser().getLogin());
	    
	    record.setUser(user);
	    
	} catch (DaoException | NoSuchEntityException e) {
	    
	    e.printStackTrace();
	}
	
	
    }
    
    
}
