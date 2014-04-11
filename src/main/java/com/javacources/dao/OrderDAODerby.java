package com.javacources.dao;

import com.javacources.entity.Car;
import com.javacources.entity.Order;
import com.javacources.entity.User;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by tanya on 3/9/14.
 */
@Repository
public class OrderDAODerby implements OrderDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public int insertOrder(User user, Car car) {
        int k = 0;
        Order order = new Order(user, car);
        try {
            em.persist(order);
            k = 1;
        }
        catch(Exception e){
            k = -1;
        }
        finally{

        }
        return k;
    }

    @Override
    public boolean deleteOrder(Order order) {
        boolean k = false;
        Car car = order.getCar();
        try {
            order.setUser(null);
            order.setCar(null);
            em.merge(order);
            //em.remove(order);
            //em.remove(car);

//            if (findAllUserOrders(order.getUser()).size() == 0){
//                em.remove(order.getUser());
//            }
            k = true;
        }
        catch(Exception e){
        }
        finally{

        }
        return k;
    }

    @Override
    public boolean deleteAllUserOrders(User user) {
        boolean k = false;

        List<Order> ordersU = findAllUserOrders(user);
        for (Order o: ordersU){
            deleteOrder(o);
        }
        k = true;

        return k;
    }

    @Override
    public List<Order> findAllUserOrders(User user) {
        List<Order> userOrders = null;
        Order o = null;

        Query query = em.createQuery("SELECT o FROM Order o " +
                "WHERE o.user.id = :parUser");
        try{
            query.setParameter("parUser", user.getId());
            userOrders = query.getResultList();
        }
        finally{
        }
        return userOrders;
    }

    @Override
    public boolean existsOrder(User user, Car car) {
        return false;
    }
}
