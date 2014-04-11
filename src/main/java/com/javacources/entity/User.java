package com.javacources.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by tanya on 3/9/14.
 */
@XmlRootElement(name = "user")
@XmlType(propOrder = { "login", "password", "userName" })

@Entity
@Table(name="UserTable")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private String userName;

    public User(){
        this.login = "1";
        this.password = "1";
        this.userName = "guest";

    }

    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.userName = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
