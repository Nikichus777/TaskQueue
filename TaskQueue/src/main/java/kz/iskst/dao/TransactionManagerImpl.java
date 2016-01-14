package kz.iskst.dao;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Callable;

import kz.iskst.dao.ConnectionFactoryFactory.FactoryType;

/*
 * You can choose you Connection Pool when you create <code>Connection</code> from <code>ConnectionFactoryFactory</code>
 * or you can create connection by default if you use method <code>ConnectionFactoryFactory.newConnection</code>
 * 
 */
public class TransactionManagerImpl implements TransactionManager {
    private ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();
    private FactoryType connType = FactoryType.PROXOOL;

    public Connection getConnection() {
	Connection connect = connectionHolder.get();

	if (connect == null) {
	    setConnection(connType);
	}
	return connectionHolder.get();

    }

    public void setConnection(FactoryType connType) {
	try {
	    Connection connect = ConnectionFactoryFactory
		    .newConnection(connType);
	    connect.setAutoCommit(false);
	    connectionHolder.set(connect);
	} catch (FileNotFoundException | SQLException | PropertyVetoException e) {
	    System.out.println("CONNECTION CREATE FROM CONNECTION POOL "
		    + connType + " FAILED!!");
	    e.printStackTrace();
	}
    }

    public TransactionManagerImpl() {

    }

    public ThreadLocal<Connection> getConnectionHolder() {
	return connectionHolder;
    }

    public void setConnectionHolder(ThreadLocal<Connection> connectionHolder) {
	this.connectionHolder = connectionHolder;
    }

    public <T> T doInTransaction(Callable<T> unitOfWork) throws Exception {

	setConnection(connType);
	Connection connection = getConnection();
	try {

	    T result = unitOfWork.call();
	    connection.commit();
	    return result;

	} catch (SQLException se) {
	    connection.rollback();
	    if (se.getSQLState().equals("23000"))
		System.out.println("value IS NOT UNIQUE");
	    se.printStackTrace();
	    throw new RuntimeException(new SQLException(
		    "doInTransactionError: "));
	}

	catch (Exception e) {
	    connection.rollback();

	    System.out.println("CONNECTION  DID ROLLBACK!!");
	    e.printStackTrace();

	    throw new RuntimeException();
	} finally {
	    connectionHolder.remove();
	    connection.close();

	}
    }
}
