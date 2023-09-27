package com.example.sdcliente.Senders.Data;

import com.example.sdcliente.Helpers.Validators;
import com.example.sdcliente.Models.Validation.ValidationException;
import org.apache.commons.codec.digest.DigestUtils;

public class LoginData implements BaseData {
    private String email;
    private String password;

    public LoginData(String email, String password) {
        this.email = email;
        this.password = DigestUtils.md5Hex(password).toUpperCase();
    }

    public LoginData () {}

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
}
