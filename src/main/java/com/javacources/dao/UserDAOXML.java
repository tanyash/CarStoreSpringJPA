package com.javacources.dao;


import com.javacources.dao.UserDAO;
import com.javacources.dao.XMLDAOFactory;
import com.javacources.entity.Car;
import com.javacources.entity.Order;
import com.javacources.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Iterator;


/**
 * Created by tanya on 3/9/14.
 */
@Component
public class UserDAOXML implements UserDAO {
    @Autowired
    XMLDAOFactory xmldaoFactory;

    public UserDAOXML() {
    }

    public UserDAOXML(XMLDAOFactory xmldaoFactory) {
        this.xmldaoFactory = xmldaoFactory;
    }

    public void setXmldaoFactory(XMLDAOFactory xmldaoFactory) {
        this.xmldaoFactory = xmldaoFactory;
    }

    @Override
    public int insertUser(String login, String password, String name) {
        User u = findUserByLogin(login);
        if (u != null){
            if (password.equals(u.getPassword())){
                xmldaoFactory.setCurUser(u);
                return 0;//This user has been already saved
            }
            else{
                xmldaoFactory.setCurUser(null);
                return -1;//Password is wrong
            }
        }

        User newUser = new User(login, password, name);

        xmldaoFactory.getUsers().add(newUser);
        xmldaoFactory.setCurUser(newUser);
        return 1;

    }

    @Override
    public boolean deleteUser(User user) {
        if (existsUser(user)){

            //Delete all his orders with the referral cars
            Iterator it = xmldaoFactory.getOrders().iterator();
            Order o;
            Car car;
            while(it.hasNext()){
                o = (Order) it.next();
                if (o.getUser().equals(user)){
                    car = o.getCar();
                    it.remove();
                    xmldaoFactory.getCars().remove(car);
                }
            }

            xmldaoFactory.getUsers().remove(user);
            if (xmldaoFactory.getCurUser().equals(user)){
                xmldaoFactory.setCurUser(null);
            }
            return true;//Successful deletion
        }
        return false;
    }

    @Override
    public User findUserByLogin(String login) {
        for (User u: xmldaoFactory.getUsers()){
            if (login.equals(u.getLogin())){
                return u;
            }
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        User u = findUserByLogin(user.getLogin());

        if (u != null){
            if (!((u.getPassword().equals(user.getPassword())) && (u.getUserName().equals(user.getUserName())))){
                xmldaoFactory.getUsers().remove(u);
                xmldaoFactory.getUsers().add(user);
                return true;//Successful update
            }
        }
        return false;
    }

    @Override
    public boolean existsUser(User user) {
        for (User u: xmldaoFactory.getUsers()){
            if (u.equals(user)){
                return true;
            }
        }
        return false;
    }

    public User getCurUser() {
        return xmldaoFactory.getCurUser();
    }
}
