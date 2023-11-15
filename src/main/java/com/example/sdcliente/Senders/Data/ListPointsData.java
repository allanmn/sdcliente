package com.example.sdcliente.Senders.Data;

import com.example.sdcliente.Models.Validation.ValidationException;

public class ListPointsData implements BaseData {
    private String token;

    public ListPointsData(String token) {
        this.token = token;
    }

    public ListPointsData() {}

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
