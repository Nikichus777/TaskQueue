package kz.iskst.dao;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactoryFactory {
    public static enum FactoryType {
    	RAW, DBCP, C3P0, PROXOOL
    };

    public static FactoryType currentType = FactoryType.C3P0;

    public static void setFactoryType(FactoryType ft) {
	currentType = ft;
    }

    public static Connection newConnection(FactoryType ft) throws SQLException,
	    PropertyVetoException, FileNotFoundException {

	switch (ft) {

	case RAW:
	    return new ConnectionFactoryJdbc().newConnection();

	case DBCP:
	    return new ConnectionFactoryDbcp().newConnection();

	case C3P0:
	    return new ConnectionFactoryC3p0().newConnection();

	case PROXOOL:
	    return new ConnectionFactoryProxol().newConnection();

	default:
	    throw new RuntimeException("FABRIC NOT FOUND");
	}

    }

    public static Connection newConnection() throws SQLException,
	    PropertyVetoException, FileNotFoundException {
	switch (currentType) {

	case RAW:
	    return new ConnectionFactoryJdbc().newConnection();

	case DBCP:
	    return new ConnectionFactoryDbcp().newConnection();

	case C3P0:
	    return new ConnectionFactoryC3p0().newConnection();

	case PROXOOL:
	    return new ConnectionFactoryProxol().newConnection();

	default:
	    throw new RuntimeException("FABRIC NOT FOUND");
	}
    }

    public static ConnectionFactory newConnectionFabric(FactoryType ft) {

	switch (ft) {
	case RAW:
	    return new ConnectionFactoryJdbc();
	    
	case DBCP:
	    return new ConnectionFactoryDbcp();
	    
	case C3P0:
	   	return new ConnectionFactoryC3p0();
	   	
	case PROXOOL:
	    return new ConnectionFactoryProxol();
	    
	default:
	    throw new RuntimeException("FABRIC NOT FOUND");
	}

    }

    public static ConnectionFactory newConnectionFabric() {
    	
	    return newConnectionFabric(currentType);
	
    }

}
