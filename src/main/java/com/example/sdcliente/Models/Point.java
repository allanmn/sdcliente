package com.example.sdcliente.Models;

public class Point {
    private long id;

    private String name;

    private String obs;

    public Point(String nome, String obs) {
        this.name = nome;
        this.obs = obs;
    }

    public Point() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
