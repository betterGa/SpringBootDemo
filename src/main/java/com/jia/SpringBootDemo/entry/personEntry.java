package com.jia.SpringBootDemo.entry;

import javax.validation.constraints.Size;

public class personEntry {
    public personEntry(){

    }

    private int id;
    private String phoneNum;
    private String personalID;

    @Size(min = 2, max = 5)
    private String age;
    private String name;
    private String sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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
        this.sex=sex;
    }

    public personEntry(int id, String age, String name, String sex) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.sex = sex;
    }
}
