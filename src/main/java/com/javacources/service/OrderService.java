package com.javacources.service;

import com.javacources.dao.CarDAO;
import com.javacources.dao.DAOFactory;
import com.javacources.dao.OrderDAO;
import com.javacources.dao.UserDAO;
import com.javacources.entity.Car;
import com.javacources.entity.Order;
import com.javacources.entity.User;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by tanya on 3/29/14.
 */
@Service
public class OrderService {
    @Autowired
    public UserDAO userDAO;
    @Autowired
    public CarDAO carDAO;
    @Autowired
    public OrderDAO orderDAO;
//    @Autowired
//    @Qualifier("factory")
//    public DAOFactory f;

    @Transactional(propagation= Propagation.REQUIRED)
    public int createOrder(User user, Car car){
        return orderDAO.insertOrder(user, car);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public int createOrder(User user, String brand, String model, int year){
        Car car = carDAO.insertCar(brand, model, year);
        return orderDAO.insertOrder(user, car);
    }

    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public boolean deleteOrder(Order order){
        return orderDAO.deleteOrder(order);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public List<Order> findAllUserOrders(User user){
        return orderDAO.findAllUserOrders(user);
    }

    public void close(){
 //       f.close();
    }
}
