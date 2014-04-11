package com.javacources.dao;

import com.javacources.entity.Car;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by tanya on 3/9/14.
 */
@Repository
public class CarDAODerby implements CarDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Car insertCar(String brand, String model, int year) {
        Car car;
        car = new Car(brand, model, year);
        try {
            em.persist(car);
        }
        catch(Exception e){
            car = null;
        }
        finally{

        }
        return car;
    }

    @Override
    public boolean deleteCar(Car car) {
        boolean k = false;
        try {
            em.remove(car);
            k = true;
        }
        catch(Exception e){
        }
        finally{

        }
        return k;
    }

}
