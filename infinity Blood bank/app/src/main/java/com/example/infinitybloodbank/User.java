package com.example.infinitybloodbank;

public class User {

    public String phone, name, pass, district, blood;
    public int donated = 0, received = 0;

    public User(){

    }

    public User(String phone, String name, String pass, String district, String blood){
        this.name = name;
        this.phone = phone;
        this.pass = pass;
        this.district = district;
        this.blood = blood;
    }
}
