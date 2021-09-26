package com.example.infinitybloodbank;

public class Request {

    public String name, phone, bg, location, district, gender, age, reason, from, status, key;
    public int clicked = 0;

    public Request(String name, String phone, String bg, String location, String district, String gender, String age, String reason, String status, String from, String key, int clicked) {
        this.name = name;
        this.phone = phone;
        this.bg = bg;
        this.location = location;
        this.district = district;
        this.gender = gender;
        this.age = age;
        this.reason = reason;
        this.status = status;
        this.from = from;
        this.key = key;
        this.clicked = clicked;
    }

    public Request() {

    }
}
