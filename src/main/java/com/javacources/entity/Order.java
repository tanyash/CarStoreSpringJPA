package com.javacources.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by tanya on 3/9/14.
 */
@Entity
@Table(name="OrderTable")

@XmlRootElement(name = "order")
@XmlType()
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id")
    @XmlElement(name = "user")
    private User user;

    @OneToOne(cascade = {CascadeType.ALL})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="car_id")
    @XmlElement(name = "car")
    private Car car;

    @XmlAttribute
    private long nanoTm;

    public Order() {
    }

    public Order(User user, Car car) {
        this.user = user;
        this.car = car;
        this.nanoTm = System.nanoTime();
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getNanoTm() {
        return nanoTm;
    }

    public void setNanoTm(long nanoTm) {
        this.nanoTm = nanoTm;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
