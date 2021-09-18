package com.example.infinitybloodbank;

public class User {

    public String phone, name, pass, district, blood, active;
    public int donated = 0, received = 0;

    public User(){

    }

    public User(String phone, String name, String pass, String district, String blood, String active){
        this.name = name;
        this.phone = phone;
        this.pass = pass;
        this.district = district;
        this.blood = blood;
        this.active = active;
    }
}