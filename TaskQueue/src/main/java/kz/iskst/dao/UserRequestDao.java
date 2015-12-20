package kz.iskst.dao;

import java.util.Date;

import kz.iskst.model.User;
import kz.iskst.model.UserRequest;

public interface UserRequestDao  extends Dao<UserRequest>{
    UserRequest selectRequestByUser(User user) throws DaoException;
    UserRequest selectRequestByPriority(int priority) throws DaoException;
    UserRequest selectRequestByTime(Date time) throws DaoException;
    
}
