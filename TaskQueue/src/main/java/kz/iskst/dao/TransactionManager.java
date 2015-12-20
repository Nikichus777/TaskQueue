package kz.iskst.dao;

import java.sql.Connection;
import java.util.concurrent.Callable;

public interface TransactionManager {
	public  <T> T doInTransaction(Callable<T> unitOfWork) throws Exception;
	public Connection getConnection();
}
