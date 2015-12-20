package kz.iskst.dao;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactoryC3p0 implements ConnectionFactory {
    
    private static ComboPooledDataSource cpds;
    
    static {
    	try {
    		cpds = new ComboPooledDataSource();
			cpds.setUser(USER);
			cpds.setPassword(PASSWORD);
			cpds.setJdbcUrl(URL);
			cpds.setDriverClass(DRIVER);
			cpds.setAcquireIncrement(3);
			cpds.setMinPoolSize(1);
			cpds.setMaxPoolSize(30);
			cpds.setLogWriter(new PrintWriter("C:/c3p0-Log.txt"));
			cpds.setAutoCommitOnClose(false);
			
			cpds.setLoginTimeout(50000);
			cpds.setMaxStatements(30);
			cpds.setCheckoutTimeout(5000);
    	}
    	catch (SQLException se){
    		System.out.println("SEttings in static connection pool C3p0 failed");
    		se.printStackTrace();
    	} 
    	catch (PropertyVetoException e) {
			e.printStackTrace();
		} 
    	catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public ConnectionFactoryC3p0() {
			
    }
        
    @Override
    public Connection newConnection() throws SQLException, PropertyVetoException {
	
	return cpds.getConnection();
	
    }

    @Override
    public void close() throws Exception {
	cpds.close();
	
    }
    
}
