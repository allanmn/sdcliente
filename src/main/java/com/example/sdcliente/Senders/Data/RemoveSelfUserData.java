package com.example.sdcliente.Senders.Data;

import com.example.sdcliente.Helpers.Validators;
import com.example.sdcliente.Models.Validation.ValidationException;
import org.apache.commons.codec.digest.DigestUtils;

public class RemoveSelfUserData implements BaseData {
    private String email;
    private String password;

    private String token;
    public RemoveSelfUserData(String email, String password, String token) {
        this.email = email;
        this.password = DigestUtils.md5Hex(password).toUpperCase();
        this.token = token;
    }

    public RemoveSelfUserData() {}

    public boolean validate() throws ValidationException {
        if (email == null || email.isEmpty()) {
            throw  new ValidationException("E-mail é obrigatório");
        }

        if (password == null || password.isEmpty()) {
            throw  new ValidationException("Senha é obrigatório");
        }

        if (!Validators.isValidEmail(email)) {
            throw  new ValidationException("E-mail inválido");
        }

        if (!Validators.isValidPassword(password)) {
            throw  new ValidationException("Senha deve ter 6 digitos");
        }

        if (token == null || token.isEmpty()) {
            throw new ValidationException("Token é obrigatório");
        }

        return true;
    }



    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
