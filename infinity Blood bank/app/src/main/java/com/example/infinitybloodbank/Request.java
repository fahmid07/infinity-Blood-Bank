package com.example.infinitybloodbank;

public class Request {

    public String name, phone, bg, location, district, gender, age, reason, from;
    int status;

    public Request(String name, String phone, String bg, String location, String district, String gender, String age, String reason, int status, String from) {
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
    }

    public Request() {

    }
}
