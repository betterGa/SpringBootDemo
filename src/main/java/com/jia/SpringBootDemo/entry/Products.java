package com.jia.SpringBootDemo.entry;

public class Products {

    private String id;
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Products(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
