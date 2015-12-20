package kz.iskst.jdbcTest;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import kz.iskst.dao.UserDao;
import kz.iskst.dao.UserDaoImpl;
import kz.iskst.dao.UserRequestDao;
import kz.iskst.dao.UserRequestDaoImpl;
import kz.iskst.model.User;
import kz.iskst.model.User.Group;
import kz.iskst.model.UserRequest;
import kz.iskst.model.UserRequest.Problems;


public class JdbcTest{
    static Logger logger = Logger.getLogger(JdbcTest.class);

    public static void main(String[] args) throws PropertyVetoException,
	    FileNotFoundException, ClassNotFoundException {

	try {
	    testUserDao();
	    // testUserRequestDao();

	}

	catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    private static void  testUserDao(){
    	try {
    		UserDaoImpl udi = new UserDaoImpl();
    		
    		User user = new User("Serega","Kotov@kotov.kk");
    		user.setName("Sergey");
    		user.setSurname("Kotov");
    		user.setPassword("md5password");
    		user.setPassword("Urevich");
    		user.setGroup(Group.GEOLOGISTS);
    		logger.debug("UDI, before insert user: " + user );
    		int id = udi.insert(user);
    		logger.debug("UDI, Select by login: " + udi.selectByLogin("Serega"));
    		logger.debug("UDI, Select by ID: " + udi.selectById(id));
    				
    		logger.debug("UDI, Select ALL: " + udi.selectAll());
    		
    		user.setEmail("changedemail@mail.changed");
    		user.setSurname("KOTOVICH");
    		logger.debug("UDI, UPDATE: ");
    		user.setId(id);
    		udi.update(user);
    		logger.debug("UDI, after update - Select by ID: " + udi.selectById(id));
    		udi.delete(user.getId());
    		logger.debug("UDI, after delete select All" + udi.selectAll());
    		
    		ModelGenerator gena = new ModelGenerator();
    		List<User> users = gena.getUserList(10);
    		
    		logger.debug("UDI, BATCH INSERT: " + users);
    		List<Integer> ids = udi.insertBatch(users);
    		logger.debug("generated  by MySql ids: " + ids);    		
    		logger.debug("UDI, after insertBatch: " + udi.selectAll());
    		udi.deleteBatch(ids);
    		logger.debug("UDI, after deleteBatch: " + udi.selectAll());
    		
    		
    		
    	}
    	
    	catch (Exception e) {
    	    e.printStackTrace();
    	    
    	}
    	finally {
    		
    	}
    }
    
    private static void testUserRequestDao() {
	try {
	    logger.debug("Start test UserRequestDao");
	    UserDao userdi = new UserDaoImpl();
	    User user = new User("Serega", "Kotov@kotov.kk");
	    user.setName("Sergey");
	    user.setSurname("Kotov");
	    user.setPassword("md5password");
	    user.setPassword("Urevich");
	    user.setGroup(Group.GEOLOGISTS);
	    logger.debug("UDI, before insert user: " + user);
	    int userid = userdi.insert(user);

	    UserRequestDao urdi = new UserRequestDaoImpl();
	    UserRequest ureq = new UserRequest(user, Problems.OFFICE, 8);

	    logger.debug("UREQDI, before insert userRequest: " + ureq);
	    int id = urdi.insert(ureq);
	   
	    logger.debug("UREQDI, Select by ID: " + urdi.selectById(id));

	    logger.debug("UREQDI, Select ALL: " + urdi.selectAll());

	    ureq.setProblem(Problems.PC_NOT_LOAD);

	    ureq.setId(id);
	    logger.debug("UREQDI, UPDATE: " + ureq);
	    urdi.update(ureq);
	    logger.debug("UREQDI, after update - Select by ID: "
		    + urdi.selectById(id));
	    urdi.delete(ureq.getId());
	    logger.debug("UREQDI, after delete - select All"
		    + urdi.selectAll());
	   

	    ModelGenerator gena = new ModelGenerator();
	    List<UserRequest> ureqs = gena.getUserRequestsList(5);

	    logger.debug("UREQDI, BATCH INSERT: " + ureqs);
	    List<Integer> ids = urdi.insertBatch(ureqs);
	    logger.debug("generated  by MySql ids: " + ids);
	    System.out
		    .println("UREQDI, after insertBatch: " + urdi.selectAll());
	    urdi.deleteBatch(ids);
	    System.out
		    .println("UREQDI, after deleteBatch: " + urdi.selectAll());
	    userdi.delete(userid);
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

}
