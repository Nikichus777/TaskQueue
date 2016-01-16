package kz.iskst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kz.iskst.exception.DaoException;
import kz.iskst.exception.NoSuchEntityException;

import org.apache.log4j.Logger;

public abstract class AbstractDao<T> {
    Logger logger = Logger.getLogger(AbstractDao.class);
    
   // protected static TransactionManager txManager = new TransactionManagerImpl(); // -static
	
    static //non-static
	{
    	 //txManager = new TransactionManagerImpl();
	}
	
    protected Connection getConnection() throws DaoException {
	logger.debug("Try to get connection");
	//txManager = new TransactionManagerImpl();
	Connection conn = TransactionManagerImpl.getConnection();
	if (conn != null)
	    return conn;
	else
	    throw new DaoException(
		    "AbstractDao.getConnection: Can't set serializable to connection",
		    new SQLException());
    }
	
   protected Connection getSerizlizableConnection() throws DaoException{
	    	//txManager = new TransactionManagerImpl();
		   Connection conn = TransactionManagerImpl.getConnection();
	   try {
		  
		   conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		    return conn;
	   } catch (SQLException e) {
		   e.printStackTrace();
		   if (conn != null) closeQuietly(conn);
		   throw new DaoException("AbstractDao.getSerializableConnection: Can't set serializable to connection",e);
	   } 	  
   }
   
    protected T selectById(String sql, Extractor<T> extractor,
	    Enricher<T> enricher, int id) throws DaoException,
	    NoSuchEntityException {
	logger.debug("selectById start");
	Connection conn = getConnection();
	try {
	    T result;

	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setInt(1, id);
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
		result = extractor.extractOne(rs);
		enricher.enrich(result);
		logger.debug("selectById succefull");
		return result;
	    }

	    throw new NoSuchEntityException("AbstractDao.selectById ");
	} catch (SQLException se) {
	    se.printStackTrace();
	    throw new DaoException(sql);
	} finally {
	  //  closeQuietly(conn);
	}
    }

    protected List<T> selectAll(String sql, Extractor<T> extractor,
	    Enricher<T> enricher) throws DaoException, NoSuchEntityException {
	Connection conn = getConnection();
	try {
	    logger.debug("selectAll start");
	    List<T> result = null;
	    PreparedStatement ps = conn.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
	    result = extractor.extractAll(rs);
	    if (result != null) {
		enricher.enrichAll(result);
		logger.debug("selectAll succefull");
		return result;
	    } else
		throw new NoSuchEntityException("AbstractDao.selectAll");
	} catch (SQLException se) {
	    se.printStackTrace();
	    throw new DaoException(sql);
	} finally {
	 //   closeQuietly(conn);
	}

    }

    protected int insert(String sql, T object, Filler<T> filler) throws DaoException {
    		Connection conn = null;
    	try {
	    conn = getSerizlizableConnection();
	    PreparedStatement ps = conn.prepareStatement(sql,
		    Statement.RETURN_GENERATED_KEYS);
	    filler.fillInsert(object, ps);

	    ps.executeUpdate();

	    ResultSet rs = ps.getGeneratedKeys();
	    if (rs.next())
		return rs.getInt(1);
	    else
		return 0;
    	}
    	catch (SQLException se){
    	    se.printStackTrace();
    	    throw new DaoException(sql);
    	}
    	finally{
    	//    closeQuietly(conn);
	    }
    	}
    
	 
    protected List<Integer> insertBatch(String sql, List<T> batch, Filler<T> filler) throws DaoException {
	Connection conn = null;
	try {
	    conn = getConnection();
	    PreparedStatement ps = conn.prepareStatement(sql,
		    Statement.RETURN_GENERATED_KEYS);
	    List<Integer> generated = new ArrayList<Integer>();
	    filler.fillAllInsert(batch, ps);
	    ps.executeBatch();
	    ResultSet rs = ps.getGeneratedKeys();
	    while (rs.next()){
		generated.add(rs.getInt(1));
	    }

	    return generated;
	} catch (SQLException se) {
	    se.printStackTrace();
	    throw new DaoException(sql);
	} finally {
	  // closeQuietly(conn);
	    }
	
    }
    
    protected void update(String sql, T object, Filler<T> filler) throws DaoException{
    	Connection conn = null;
	try {
	    conn = getSerizlizableConnection();
	    PreparedStatement ps = conn.prepareStatement(sql);
	    filler.fillUpdate(object, ps);

	    ps.executeUpdate();

	} catch (SQLException se) {
	    se.printStackTrace();
	    throw new DaoException(sql);
	} finally {
	  //  closeQuietly(conn);
	}
    }
	
    protected void delete(String sql, int id) throws DaoException{
	Connection conn = null;
	try {
    	    conn = getSerizlizableConnection();
    	    PreparedStatement ps = conn.prepareStatement(sql);
    	    ps.setInt(1, id);
    	    ps.executeUpdate();
								
    	}
    	catch (SQLException se){
    		se.printStackTrace();
    		throw new DaoException(sql);
    	}
    	finally{
    		//closeQuietly(conn);
    	}
    }
	
    protected void deleteBatch(String sql, List<Integer> id) throws DaoException {
		for (int i : id){
			delete(sql, i);
		}
		
	}
    
    protected void closeQuietly(Connection conn) throws DaoException {
    	try{
    	    logger.debug("try to commit and close connection");
    	    if (conn != null){
	    	conn.commit();
	    	conn.close();	    	
	    	}
    	}
    	catch(SQLException se){
    		se.printStackTrace();
    		throw new DaoException("AbstractDao.closeQuietly:  ",se);
    		
    	}
    	
        }

}
