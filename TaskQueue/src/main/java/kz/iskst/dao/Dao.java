package kz.iskst.dao;

import java.util.List;

import kz.iskst.exception.DaoException;
import kz.iskst.exception.NoSuchEntityException;

public interface Dao<T> {

	public abstract T selectById(int id) throws DaoException, NoSuchEntityException;

	public abstract List<T> selectAll() throws DaoException, NoSuchEntityException;

	public abstract int insert(T obj) throws DaoException;
	
	public abstract List<Integer> insertBatch(List<T> batch) throws DaoException;

	public abstract void update(T obj) throws DaoException;
	
	public abstract void delete(int id) throws DaoException;
	
	public abstract void deleteBatch(List<Integer> id) throws DaoException;
	
	public abstract void close() throws DaoException;
	
}