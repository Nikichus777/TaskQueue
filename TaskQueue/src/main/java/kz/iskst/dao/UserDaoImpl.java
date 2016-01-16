package kz.iskst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kz.iskst.exception.DaoException;
import kz.iskst.exception.NoSuchEntityException;
import kz.iskst.model.User;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    public static final String SELECT_ALL = "SELECT * FROM `USER`";
    public static final String SELECT_BY_ID = "SELECT * FROM `USER` WHERE `ID` = ?";
    public static final String SELECT_BY_LOGIN = "SELECT * FROM `USER` WHERE `login` = ?";
    public static final String INSERT = "INSERT INTO `USER`(`ID`,`LOGIN`, `EMAIL`,`PASSWORD`,`NAME`,`SURNAME`,`PATRONYMIC`,`GROUP`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE `jdbc`.`user` SET `login`=?, `email`=?,`PASSWORD`=?,`NAME`=?,`SURNAME`=?,`PATRONYMIC`=?,`GROUP`=? WHERE `id`=? and `login`=?";    
    public static final String DELETE = "DELETE FROM `jdbc`.`user` WHERE `id`=?";
	
	@Override
    public int insert(User us) throws DaoException {
		
	return insert(INSERT, us, new UserFiller());
		
		//return insert(INSERT, User, new  )
		/*PreparedStatement ps = conn.prepareStatement(INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, us.getLogin());
		ps.setString(2, us.getEmail());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		int ret = 0;
		if (rs.next()){
		ret = rs.getInt(1);
		//conn.commit();
		//System.out.println(ret);
		return ret;
		}
		//conn.rollback();
		throw new SQLException("INSERT INTO TABLE ERROR");*/
		
    }

    @Override
    public User selectById(int id) throws DaoException, NoSuchEntityException {
    	return selectById(SELECT_BY_ID, new UserExtractor(), new UserEnricher(), id);
		
		/*PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID);
		ps.setInt(1, id);
		ResultSet rs =  ps.executeQuery();
		return new UserExtractor().extractOne(rs);		*/
		
		
    }

    @Override
    public List<User> selectAll() throws DaoException, NoSuchEntityException {
		return selectAll(SELECT_ALL, new UserExtractor(), new UserEnricher());
    	/*try{
	    	Connection conn = new TransactionManagerImpl().getConnection();
	    	PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
			ResultSet rs = ps.executeQuery();
					
			return new UserExtractor().extractAll(rs);
		}
		catch (SQLException se){
    		throw new DaoException("Dao Exception: sql " + SELECT_ALL + " not execute");
    	}*/
	}

    @Override
    public List<Integer> insertBatch(List<User> batch) throws DaoException {
    	return insertBatch(INSERT, batch, new UserFiller());
	/*try{
    		    	
	    	Connection conn = new TransactionManagerImpl().getConnection();
	    	PreparedStatement ps = conn.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
	    	List<Integer> generated = new ArrayList<Integer>();
	    	for (User us : batch){
		    	ps.setString(1, us.getLogin());
		    	ps.setString(2, us.getEmail());
		    	ps.addBatch();		    	
			}
			for (int i : ps.executeBatch()){
				generated.add(i);
			}
	    	
			conn.commit();
			//close();
			return generated;
    	}
    	catch (SQLException se){
    		se.printStackTrace();
    		throw new DaoException("Dao Exception: sql " + INSERT + " not execute");
    	}*/
    }

    
    
/*    @Override
    public void close() throws DaoException {
	// TODO Auto-generated method stub
//	conn.commit();
//	conn.close();
    }*/


    @Override
	public User selectByLogin(String login) throws DaoException,
			NoSuchEntityException {
		try {
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(SELECT_BY_LOGIN);
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User user = new UserExtractor().extractOne(rs);
				return user;
			}
			throw new NoSuchEntityException("UserDaoImpl SelectByLogin failed");

		} catch (SQLException se) {
			throw new DaoException("Dao Exception: sql " + SELECT_BY_LOGIN
					+ " not execute");
		}
	}

	@Override
	public void update(User obj) throws DaoException {
		update(UPDATE, obj, new UserFiller());
		
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
