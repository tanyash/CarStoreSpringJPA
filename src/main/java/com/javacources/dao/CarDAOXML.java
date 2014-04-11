package com.javacources.dao;


import com.javacources.dao.CarDAO;
import com.javacources.dao.XMLDAOFactory;
import com.javacources.entity.Car;
import com.javacources.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by tanya on 3/9/14.
 */
@Component
public class CarDAOXML implements CarDAO {
    @Autowired
    XMLDAOFactory xmldaoFactory;

    public CarDAOXML() {
    }

    public CarDAOXML(XMLDAOFactory xmldaoFactory) {
        this.xmldaoFactory = xmldaoFactory;
    }

    @Override
    public Car insertCar(String brand, String model, int year) {
        Car car;
        car = new Car(brand, model, year);
        xmldaoFactory.getCars().add(car);
        return car;
    }

    @Override
    public boolean deleteCar(Car car) {
        //Check for orders with this car
        for (Order o: xmldaoFactory.getOrders()){
            if (o.getCar().equals(car)){
                return false;
            }
        }

        for (Car c: xmldaoFactory.getCars()){
            if (c.equals(car)){
                xmldaoFactory.getCars().remove(c);
                return true;
            }
        }

        return false;
    }

}
