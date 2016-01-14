package kz.iskst.dao;

import kz.iskst.exception.DaoException;
import kz.iskst.exception.NoSuchEntityException;
import kz.iskst.model.User;

public interface UserDao extends Dao<User> {
    User selectByLogin(String login) throws DaoException, NoSuchEntityException;
    
}
