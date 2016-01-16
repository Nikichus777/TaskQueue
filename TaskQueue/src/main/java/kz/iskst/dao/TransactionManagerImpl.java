package kz.iskst.dao;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import kz.iskst.dao.ConnectionFactoryFactory.FactoryType;

/*
 * You can choose you Connection Pool when you create <code>Connection</code> from <code>ConnectionFactoryFactory</code>
 * or you can create connection by default if you use method <code>ConnectionFactoryFactory.newConnection()</code>
 * 
 */

public class TransactionManagerImpl { //implements TransactionManager {
    private static ThreadLocal<Connection> connectionHolder;
    private static FactoryType connType = FactoryType.PROXOOL;

    private static Logger logger = Logger.getLogger(TransactionManagerImpl.class);
    
	public static Connection getConnection() {
		logger.debug("Try to get Connection");
		Connection connect = getConnectionHolder().get();
		if (connect == null) {
			setConnection(connType);
		}

		return getConnectionHolder().get();

	}

	public static void setConnection(FactoryType connType) {
		logger.debug("Try to setConnection in connectionHolder");
		if (getConnectionHolder() == null) setConnectionHolder(new ThreadLocal<Connection>());
		if (getConnectionHolder().get() != null) return;
		try {
			Connection connect = ConnectionFactoryFactory
					.newConnection(connType);
			connect.setAutoCommit(false);
			getConnectionHolder().set(connect);
		} catch (FileNotFoundException | SQLException | PropertyVetoException e) {
			logger.error("CONNECTION CREATE FROM CONNECTION POOL " + connType
					+ " FAILED!!");
			e.printStackTrace();
		}
	}

    public TransactionManagerImpl() {
    	logger.debug("create TransactionManagerImpl, setConnectionHolder");
    	
    }

    private static ThreadLocal<Connection> getConnectionHolder() {
    	return connectionHolder;
    }

    private static void setConnectionHolder(ThreadLocal<Connection> connectionHolder) {
    	TransactionManagerImpl.connectionHolder = connectionHolder;
    }
    
    public static void close(){
    	getConnectionHolder().set(null);
		getConnectionHolder().remove();
    }
    
	//@Override
	public static <T> T doInTransaction(Callable<T> unitOfWork) throws Exception {
		// TODO Автоматически созданная заглушка метода
		return null;
	}

    /*public <T> T doInTransaction(Callable<T> unitOfWork) throws Exception {
    	
		logger.debug("start doInTransaction");
    	if (getConnectionHolder().get() == null)
			setConnection(connType);

		Connection connection = getConnection();
		try {

			T result = unitOfWork.call();
			connection.commit();
			return result;

		} catch (SQLException se) {
			connection.rollback();
			if (se.getSQLState().equals("23000"))
				logger.error("value IS NOT UNIQUE");
			se.printStackTrace();
			throw new SQLException("doInTransactionError: ");
		}

		catch (Exception e) {
			connection.rollback();

			logger.error("CONNECTION  DID ROLLBACK!!");
			e.printStackTrace();

			throw new RuntimeException();
		} finally {
			
			connectionHolder.remove();
			connection.close();

		}
	}*/
}
