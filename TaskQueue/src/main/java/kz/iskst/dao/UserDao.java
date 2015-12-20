package kz.iskst.dao;

import kz.iskst.model.User;

public interface UserDao extends Dao<User> {
    User selectByLogin(String login) throws DaoException, NoSuchEntityException;
    
}
