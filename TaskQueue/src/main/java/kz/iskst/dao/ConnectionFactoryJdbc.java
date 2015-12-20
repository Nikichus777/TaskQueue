package kz.iskst.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryJdbc implements ConnectionFactory {
    @Override
    public Connection newConnection() throws SQLException,
	    PropertyVetoException {
	
	return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public void close() throws Exception {
	// TODO Auto-generated method stub
	// Nothing to close
	
    }

}
