package com.example.sdcliente.Senders.Data;

import com.example.sdcliente.Models.Validation.ValidationException;

public class ListSegmentsData implements BaseData {
    private String token;

    public ListSegmentsData(String token) {
        this.token = token;
    }

    public ListSegmentsData() {}

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
