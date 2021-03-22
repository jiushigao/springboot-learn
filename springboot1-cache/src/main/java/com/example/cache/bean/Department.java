package com.example.cache.bean;

public class Department {

    private Integer id;
    private String dName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public Department(Integer id, String dName) {
        this.id = id;
        this.dName = dName;
    }

    public Department() {
    }
}
