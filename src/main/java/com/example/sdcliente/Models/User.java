package com.example.sdcliente.Models;

public class User {
    private long id;

    private String name;

    private String email;

    private String type;

    public User(String name, String email, String passoword, String type) {
        this.name = name;
        this.email = email;
        this.type = type;
    }

    public User() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }


    public String getType() {
        return type;
    }

    public void setType(String tipo) {
        this.type = tipo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                ", email='" + email + '\'' +
                ", tipo='" + type + '\'' +
                '}';
    }
}
