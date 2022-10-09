package com.Coupons.SingInservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userInfo")
public class userData {

    @Id
    private String userId;

    private String name;

    private String email;

    private long phno;

    private String password;

    public userData() {

    }

    public userData(String name, String email, long phno, String password) {
        this.name = name;
        this.email = email;
        this.phno = phno;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhno() {
        return phno;
    }

    public void setPhno(long phno) {
        this.phno = phno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "userData{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phno=" + phno +
                ", password='" + password + '\'' +
                '}';
    }
}
