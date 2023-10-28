package com.example.sdcliente.Senders.Data;

import com.example.sdcliente.Models.Validation.ValidationException;

public class RemoveUserData implements BaseData {
    private String token;

    private long userId;

    public RemoveUserData(String token, long userId) {
        this.token = token;
        this.userId = userId;
    }

    public RemoveUserData() {}

    public boolean validate() throws ValidationException {
        return true;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token = token;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }
}
