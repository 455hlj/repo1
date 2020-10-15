package com.clic.domain;

import java.io.Serializable;

/**
 * <h3>CloudMessagePush</h3>
 * <p>用户pojo</p>
 *
 * @author : John Fallen
 * @date : 2020-09-16 16:36
 **/
public class Admin implements Serializable {
    private String username;
    private String password;
    private String department;
    private String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
