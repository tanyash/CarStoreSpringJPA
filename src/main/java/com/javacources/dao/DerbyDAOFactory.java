package com.javacources.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by tanya on 3/9/14.
 */
@Component
public class DerbyDAOFactory extends DAOFactory {
    @PersistenceContext
    public EntityManager em;

    public UserDAO getUserDAO(){
        return new UserDAODerby();
    }

    public CarDAO getCarDAO(){
        return new CarDAODerby();
    }

    public OrderDAO getOrderDAO(){
        return new OrderDAODerby();
    }

    public void close(){
        em.close();
    }
}
