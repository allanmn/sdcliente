package com.example.sdcliente.Senders.Data;

import com.example.sdcliente.Models.Validation.ValidationException;

public class RequestAutoUserData implements BaseData {
    private String token;

    public RequestAutoUserData(String token) {
        this.token = token;
    }

    public RequestAutoUserData() {}

    public boolean validate() throws ValidationException {
        return true;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token = token;
    }
}
