package com.javacources.dao;

import com.javacources.entity.Car;
import com.javacources.entity.Order;
import com.javacources.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tanya on 3/9/14.
 */
@XmlRootElement(namespace = "store")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class XMLDAOFactory extends DAOFactory {
    @XmlTransient
    public Set<User> users;
    @XmlTransient
    public List<Car> cars;
    @XmlTransient
    public User curUser;


    @XmlElementWrapper(name = "ordersList")
    @XmlElement(name = "order")
    public List<Order> orders;

    @Qualifier("xmlBinder")
    @Autowired
    private XMLBinder xmlBinder;

    public XMLDAOFactory() {
        //this.xmlBinder = DAOSingleton.getXmlBinderSingleton();
        users = new HashSet<User>();
        cars = new ArrayList<Car>();
        orders = new ArrayList<Order>();
        //getData();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Set<User> getUsers() {
        return users;
    }

    public List<Car> getCars() {
        return cars;
    }

    public User getCurUser() {
        return curUser;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void setCurUser(User curUser) {
        this.curUser = curUser;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    @Autowired
    public UserDAO getUserDAO() {
        return new UserDAOXML(this);
    }

    @Override
    @Autowired
    public CarDAO getCarDAO() {
        return new CarDAOXML(this);
    }

    @Override
    @Autowired
    public OrderDAO getOrderDAO() {
        return new OrderDAOXML(this);
    }

    @Override
    public void close() {
        try {
            xmlBinder.setDataToXML(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Set<User> getUsersFromOrders(List<Order> orders){
        users = new HashSet<User>();
        User user = null;
        Car car = null;

        for (Order o: orders){
            user = o.getUser();
            if (!users.contains(user)){
                users.add(user);
            }
        }

        return users;
    }

    private List<Car> getCarsFromOrders(List<Order> orders){
        cars = new ArrayList<Car>();
        User user = null;
        Car car = null;

        for (Order o: orders){
            cars.add(o.getCar());
        }

        return cars;
    }

    private void getData(){
        XMLDAOFactory xmldaoFactory = null;
        try {
            xmldaoFactory = (XMLDAOFactory) xmlBinder.getDataFromXML(this.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (xmldaoFactory != null){
            orders = xmldaoFactory.getOrders();
            if (orders != null){
                users = getUsersFromOrders(orders);
                cars = getCarsFromOrders(orders);
            }
        }
    }


}
