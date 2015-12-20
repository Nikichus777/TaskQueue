package kz.iskst.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionFactoryDbcp implements ConnectionFactory {
    private final BasicDataSource basicDataSource;
    
    
    public ConnectionFactoryDbcp(){
	BasicDataSource bds = new BasicDataSource();
	bds.setUrl(URL);
	bds.setDriverClassName(DRIVER);
	bds.setUsername(USER);
	bds.setPassword(PASSWORD);
	basicDataSource = bds;
	
    }
    
    @Override
    public Connection newConnection() throws SQLException,
	    PropertyVetoException {
		
	return basicDataSource.getConnection();
    }

    @Override
    public void close() throws Exception {
	basicDataSource.close();
	
    }

}
