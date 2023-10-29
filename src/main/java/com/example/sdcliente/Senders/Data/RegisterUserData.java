package com.example.sdcliente.Senders.Data;

import com.example.sdcliente.Helpers.Validators;
import com.example.sdcliente.Models.Validation.ValidationException;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.codec.digest.DigestUtils;

public class RegisterUserData implements BaseData {
    @JsonProperty("name")
    private String nome;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String senha;

    public RegisterUserData(String nome, String email, String password) {
        this.email = email;
        this.senha = DigestUtils.md5Hex(password).toUpperCase();
        this.nome = nome;
    }

    public RegisterUserData() {}

    public boolean validate() throws ValidationException {
        if (email == null || email.isEmpty()) {
            throw  new ValidationException("E-mail é obrigatório");
        }

        if (senha == null || senha.isEmpty()) {
            throw  new ValidationException("Senha é obrigatório");
        }

        if (!Validators.isValidEmail(email)) {
            throw  new ValidationException("E-mail inválido");
        }

        if (!Validators.isValidPassword(senha)) {
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

    public String getNome() {
        return nome;
    }
}
