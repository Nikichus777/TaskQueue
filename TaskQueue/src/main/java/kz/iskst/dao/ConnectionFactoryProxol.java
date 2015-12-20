package kz.iskst.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactoryProxol implements ConnectionFactory {
    private Properties info;
    private String alias = "anon";
    private String url = "proxool." + alias + ":" + DRIVER + ":" + URL;
    
    public Properties getInfo() {
        return info;
    }
    public void setInfo(Properties info) {
        this.info = info;
    }
    
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    
    public ConnectionFactoryProxol() {
	
	// TODO Auto-generated constructor stub
	try {
	    Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
	} catch (ClassNotFoundException e) {
	    // TODO Auto-generated catch block
	    
	    e.printStackTrace();
	    System.out.println("PROXOOL NOT FOUND");
	}
	info = new Properties();
	info.setProperty("user",USER);
	info.setProperty("password", PASSWORD);
	info.setProperty("proxool.maximum-connection-count", "20");
	info.setProperty("proxool.house-keeping-test-sql", "SELECT CURRENT_DATE");
	
    }
    @Override
    public Connection newConnection() throws SQLException,
	    PropertyVetoException {
	
	return DriverManager.getConnection(url, info);
    }

    @Override
    public void close() throws Exception {
	// TODO Auto-generated method stub
	//Nothing to use
	
    }

}
