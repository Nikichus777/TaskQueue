package kz.iskst.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import kz.iskst.dao.ConnectionFactoryFactory.FactoryType;

public interface ConnectionFactory {
    final String URL = "jdbc:mysql://localhost:3306/jdbc";
    final String PASSWORD = "4444";
    final String USER = "root";
    final String DRIVER = "com.mysql.jdbc.Driver";

    Connection newConnection() throws SQLException, PropertyVetoException;
    void close() throws Exception;
    
}
