package kz.iskst.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/*
 * This class helps to fill <code>PreparedStatement</code>
 */
public abstract class Filler<T> {
    
	public abstract void fillUpdate(T object, PreparedStatement ps);
	public abstract void fillInsert(T object, PreparedStatement ps);
	
	public void fillAllUpdate(List<T> list, PreparedStatement ps){
		try{
			
		for (T object : list){
			fillUpdate(object, ps);
			ps.addBatch();
		}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
	}
	
	public void fillAllInsert(List<T> list, PreparedStatement ps){
		try{
			
		
		for (T object : list){
			fillInsert(object, ps);
			ps.addBatch();
		}
		
		}
		catch(SQLException se){
			se.printStackTrace();
		}
	}
}
