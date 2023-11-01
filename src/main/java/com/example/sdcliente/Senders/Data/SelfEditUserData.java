package com.example.sdcliente.Senders.Data;

import com.example.sdcliente.Helpers.Validators;
import com.example.sdcliente.Models.Validation.ValidationException;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.codec.digest.DigestUtils;

public class SelfEditUserData implements BaseData {
    private String token;

    @JsonProperty("name")
    private String nome;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String senha;

    private long id;

    public SelfEditUserData(long id, String nome, String email, String password, String token) {
        this.email = email;
        if (password != null && !password.isEmpty()) {
            this.senha = DigestUtils.md5Hex(password).toUpperCase();
        }
        this.token = token;
        this.nome = nome;
        this.id = id;
    }

    public SelfEditUserData() {}

    public boolean validate() throws ValidationException {
        if (email == null || email.isEmpty()) {
            throw  new ValidationException("E-mail é obrigatório");
        }

        if (!Validators.isValidEmail(email)) {
            throw  new ValidationException("E-mail inválido");
        }

        if (senha != null && !Validators.isValidPassword(senha)) {
            throw  new ValidationException("Senha deve ter 6 digitos");
        }

        if (nome == null || nome.isEmpty()) {
            throw  new ValidationException("Nome é obrigatório");
        }

        return true;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String password) {
        this.senha = password;
    }

    public String getToken() {
        return token;
    }

    public String getNome() {
        return nome;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
