package com.example.exceldmeo.bean;

public class Person {
   // String[] title = {"编号","姓名","性别","头像"};//用字符串数组存放标题


    public String number;
    public String name;
    public String sex;
    public String avator;


    public Person(String number, String name, String sex, String avator) {
        this.number = number;
        this.name = name;
        this.sex = sex;
        this.avator = avator;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }
}
