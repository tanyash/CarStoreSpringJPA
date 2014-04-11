package com.javacources.dao;

import com.javacources.dao.OrderDAO;
import com.javacources.dao.XMLDAOFactory;
import com.javacources.entity.Car;
import com.javacources.entity.Order;
import com.javacources.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tanya on 3/9/14.
 */
@Component
public class OrderDAOXML implements OrderDAO {
    @Autowired
    XMLDAOFactory xmldaoFactory;

    public OrderDAOXML() {
    }

    public OrderDAOXML(XMLDAOFactory xmldaoFactory) {
        this.xmldaoFactory = xmldaoFactory;
    }

    @Override
    public int insertOrder(User user, Car car) {
        if (user == null || car == null){
            return -1;
        }
         if (!xmldaoFactory.getUsers().contains(user)){
             xmldaoFactory.getUsers().add(user);
         }

        if (!xmldaoFactory.getCars().contains(car)){
            xmldaoFactory.getCars().add(car);
        }

        xmldaoFactory.getOrders().add(new Order(user, car));
        return 1;
    }

    @Override
    public boolean deleteOrder(Order order) {
        if (existsOrder(order.getUser(), order.getCar())){
            xmldaoFactory.getOrders().remove(order);
            xmldaoFactory.getCars().remove(order.getCar());

//            if (findAllUserOrders(order.getUser()).size() == 0){
//                xmldaoFactory.getUsers().remove(order.getUser());
//            }

            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAllUserOrders(User user) {
        Iterator it = xmldaoFactory.getOrders().iterator();
        int k = 0;

        while(it.hasNext()){
            Order o = (Order) it.next();
            if (user.equals(o.getUser())){
                it.remove();
                k++;
            }
        }
        if (k > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<Order> findAllUserOrders(User user) {
        List<Order> userOrder = new ArrayList<Order>();
        for (Order o: xmldaoFactory.getOrders()){
            if ((o.getUser().getLogin()).equals(user.getLogin())){
                userOrder.add(o);
            }
        }

        return userOrder;
    }

    @Override
    public boolean existsOrder(User user, Car car) {
        for (Order o: xmldaoFactory.getOrders()){
            if (user.equals(o.getUser()) && car.equals(o.getCar())){
                return true;
            }
        }
        return false;
    }
}
