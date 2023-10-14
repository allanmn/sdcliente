package com.example.sdcliente.Models;

public class User {
    private long id;

    private String nome;

    private String email;

    private String tipo;

    public User(String nome, String email, String senha, String tipo) {
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
