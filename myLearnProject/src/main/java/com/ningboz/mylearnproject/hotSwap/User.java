package com.ningboz.mylearnproject.hotSwap;

public class User {
    private String username;
    private Integer age;

    public User() {
    }

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void showUser(){
        System.out.println(String.format("version:1.0.1  姓名：%s,年龄：%s",username,age));
    }
}
