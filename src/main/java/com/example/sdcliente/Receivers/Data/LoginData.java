package com.example.sdcliente.Receivers.Data;

public class LoginData extends BaseData {
    private String token;

    public LoginData(String token) {
        this.token = token;
    }

    public LoginData() {}

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
