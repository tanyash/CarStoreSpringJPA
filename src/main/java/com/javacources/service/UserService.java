package com.javacources.service;

import com.javacources.dao.CarDAO;
import com.javacources.dao.DAOFactory;
import com.javacources.dao.OrderDAO;
import com.javacources.dao.UserDAO;
import com.javacources.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.ejb.Stateless;

/**
 * Created by tanya on 3/29/14.
 */
@Service
public class UserService {
    @Autowired
    public UserDAO userDAO;
    @Autowired
    public CarDAO carDAO;
    @Autowired
    public OrderDAO orderDAO;
    public User curUser;

    @Transactional(propagation= Propagation.REQUIRED)
    public int createUser(String login, String password, String name){
        int k = userDAO.insertUser(login, password, name);
        curUser = userDAO.getCurUser();
        return k;
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public User findUser(String login){
        curUser = userDAO.findUserByLogin(login);
        return curUser;
    }

    public User getCurUser() {
        return curUser;
    }
}
