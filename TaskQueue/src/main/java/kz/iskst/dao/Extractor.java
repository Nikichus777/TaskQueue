package kz.iskst.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kz.iskst.exception.DaoException;

public abstract class Extractor<T> {
    	/* 
    	 * This method use <code>rs.next()</code> himself
    	 * don't use <code>rs.next</code> from the outside of this method
    	 *  
    	 */
	List<T> extractAll(ResultSet rs) throws DaoException{
	    List<T> result = new ArrayList<T>();
	    try {
		while (rs.next()) {
		    result.add(extractOne(rs));
		}
		return result;
		
		} 
	    
	    catch (SQLException e) {
		
		e.printStackTrace();
		throw new DaoException("Extractor.extractOne exception",e);
		}
	    
	}
	/*
	 * This method must not use <code>rs.next()</code>
	 * you must  <code>rs.next()</code> from the outside of this method
	 */
	public abstract T extractOne (ResultSet rs) throws DaoException;
}
