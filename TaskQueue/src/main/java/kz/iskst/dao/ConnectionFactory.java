package kz.iskst.dao;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import kz.iskst.dao.ConnectionFactoryFactory.FactoryType;

public interface ConnectionFactory {
    final Properties props = new Initializer().getProperties();
    final String URL = props.getProperty("URL");//"jdbc:mysql://localhost:3306/jdbc";
    final String PASSWORD = props.getProperty("PASSWORD");//"4444";
    final String USER = props.getProperty("USER");//"root";
    final String DRIVER = props.getProperty("DRIVER");//"com.mysql.jdbc.Driver";

    Connection newConnection() throws SQLException, PropertyVetoException;
    void close() throws Exception;
    
    static class Initializer{
	public Properties getProperties(){
	    FileInputStream fis;
	    Properties property = new Properties();
	    try {
		fis = new FileInputStream("src/main/resources/config.properties");
				
            fis.close();
            return property;
	    } catch (IOException e) {
		Properties def = new Properties();
		final String URL = "jdbc:mysql://localhost:3306/jdbc";
		final String PASSWORD = "4444";
		final String USER = "root";
		final String DRIVER = "com.mysql.jdbc.Driver";
		def.setProperty("URL", URL);
		def.setProperty("PASSWORD", PASSWORD);
		def.setProperty("USER", USER);
		def.setProperty("DRIVER", DRIVER);		
		
		return def;
	    }
	  
            
	}
    }
}
