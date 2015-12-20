/**
 * 
 */
package kz.iskst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import kz.iskst.dao.ConnectionFactoryFactory.FactoryType;
import kz.iskst.model.User;
import kz.iskst.model.UserRequest;

/**
 * @author npk
 *
 */

public class UserRequestDaoImpl extends AbstractDao<UserRequest> implements UserRequestDao  {

    public static final String SELECT_ALL = "SELECT * FROM `UserRequest`";
    public static final String SELECT_BY_ID = "SELECT * FROM `UserRequest` WHERE `id` = ?";
    public static final String INSERT = "INSERT INTO `jdbc`.`UserRequest`(`id`,`USER`, `PROBLEM`,`PRIORITY`,`TIME`) VALUES (?,?,?,?,?)";
    public static final String SELECT_BY_USER_LOGIN = "SELECT * FROM `UserRequest` WHERE `user` = ?";
    public static final String SELECT_BY_PRIORITY = "SELECT * FROM `UserRequest` WHERE `priority` = ?";
    public static final String SELECT_BY_TIME = "SELECT * FROM `UserRequest` WHERE `time` = ?";
    public static final String UPDATE = "UPDATE `jdbc`.`UserRequest` SET `USER`=?, `PROBLEM`=?, `PRIORITY`=?, `TIME`=? WHERE `id`=?";
    public static final String DELETE = "DELETE FROM `jdbc`.`UserRequest` WHERE `id`=?";
    
    private TransactionManager txManager;
    private Connection conn;
    
    public TransactionManager getTxManager() {
		return txManager;
	}

	
	@Override
    public UserRequest selectRequestByUser(User user) throws DaoException {
		try{
			TransactionManager txManager = new TransactionManagerImpl();
			Connection conn = txManager.getConnection();
			PreparedStatement ps = conn.prepareStatement(SELECT_BY_USER_LOGIN);
	    	ps.setString(1, user.getLogin());
	    	ResultSet rs = ps.executeQuery();
	    	
	    	return new UserRequestExtractor().extractOne(rs);
		}
		catch (SQLException se){
			se.printStackTrace();
			throw new DaoException("select request by time: sql " + SELECT_BY_USER_LOGIN + " not execute",se);
		}
		finally{
			close();
		}
    }

    public UserRequestDaoImpl() {
	
    }


    @Override
    public UserRequest selectRequestByPriority(int priority) throws DaoException {
		
		try {
			TransactionManager txManager = new TransactionManagerImpl();
			Connection conn = txManager.getConnection();
			PreparedStatement ps;
			ps = conn.prepareStatement(SELECT_BY_PRIORITY);
			ps.setInt(1, priority);
			ResultSet rs = ps.executeQuery();
			return new UserRequestExtractor().extractOne(rs);
		} catch (SQLException se) {
			se.printStackTrace();
			throw new DaoException("select request by time: sql " + SELECT_BY_PRIORITY + " not execute",se);
		
		}
		finally{
			close();
		}
		
    }


    @Override
    public int insert(UserRequest ureq) throws DaoException {
		return insert(INSERT, ureq, new UserRequestFiller());
    	/*TransactionManager txManager = new TransactionManagerImpl();
		Connection conn; 
		conn = txManager.getConnection();
    	PreparedStatement ps = conn.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
		
		ps.setString(1, ureq.getUser().getLogin());
		ps.setString(2, ureq.getProblemString());
		ps.setInt(3, ureq.getPriority());
		ps.setLong(4, ureq.getTimeLong());
		ps.executeUpdate();
		conn.commit();
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) return rs.getInt(1);
		else return 0;*/
			
    }

    @Override
    public List<Integer> insertBatch(List<UserRequest> batch) throws DaoException {
    	return insertBatch(INSERT, batch, new UserRequestFiller());
    	
    }

    @Override
    public void close() throws DaoException {
		try {
			if (conn != null) conn.close();
			//if (txManager != null)
			
		} catch (SQLException e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
			throw new DaoException("DaoException: " + this.getClass().getName(),e);
		}
		

    }

    @Override
    public List<UserRequest> selectAll() throws DaoException, NoSuchEntityException {
    	return selectAll(SELECT_ALL, new UserRequestExtractor(), new UserRequestEnricher());
	/*TransactionManager txManager = new TransactionManagerImpl();
	Connection conn; 
	conn = txManager.getConnection();
    	List<UserRequest> requests;
    	try {
			PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
			ResultSet rs = ps.executeQuery();
			requests = new UserRequestExtractor().extractAll(rs);
			return requests;
    	} catch (SQLException e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
			throw new RuntimeException("SELECT ALL FAILED!");
		}*/
    	
    }

    @Override
    public UserRequest selectById(int id) throws DaoException, NoSuchEntityException {
	    return selectById(SELECT_BY_ID, new UserRequestExtractor(), new UserRequestEnricher(), id);
	    
	    /*	    TransactionManager txManager = new TransactionManagerImpl();
	    Connection conn; 
	    conn = txManager.getConnection();
	    PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID);
	    ps.setInt(1, id);
	    ResultSet rs = ps.executeQuery();
	    System.out.println("SELECT BY ID");
	    return new UserRequestExtractor().extractOne(rs);*/
	}

	@Override
	public UserRequest selectRequestByTime(Date time) throws DaoException{
		try{
		    conn = txManager.getConnection();
		    PreparedStatement ps = conn.prepareStatement(SELECT_BY_TIME);
		    ps.setLong(1, time.getTime());		
		    ResultSet rs = ps.executeQuery();
			
		    return new UserRequestExtractor().extractOne(rs);
		}
		catch (SQLException se){
			throw new DaoException("select request by time: sql " + SELECT_BY_TIME + " not execute");
		}
		finally{
			close();
		}
	}


	@Override
	public void update(UserRequest obj) throws DaoException {
		update(UPDATE, obj, new UserRequestFiller());
		
	}


	@Override
	public void delete(int id) throws DaoException {
		delete(DELETE, id);
		
	}


	@Override
	public void deleteBatch(List<Integer> id) throws DaoException {
		deleteBatch(DELETE, id);
		
	}

}
