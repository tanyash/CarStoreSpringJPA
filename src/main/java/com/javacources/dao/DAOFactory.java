package com.javacources.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by tanya on 3/9/14.
 */
@Component
public abstract class DAOFactory {
    public static final int DERBY_VALUE = 1;
    public static final int XML_VALUE = 2;

//    @Value("#{storeProperties.whichFactory}")
    public static int whichFactory;

    public abstract UserDAO getUserDAO();
    public abstract CarDAO getCarDAO();
    public abstract OrderDAO getOrderDAO();

    public static DAOFactory getDAOFactory(String whichFactory){
        int w = Integer.valueOf(whichFactory);
        switch(w){
            case DERBY_VALUE:
                return new DerbyDAOFactory();
            case XML_VALUE:
                return new XMLDAOFactory();
            default:
                return null;
        }
    }
    public abstract void close();

}


