package com.example.sdcliente.Receivers.Data;

import com.example.sdcliente.Models.User;

public class RequestUserData extends BaseData {
    User user = null;

    public RequestUserData(User user) {
        this.user = user;
    }

    public RequestUserData() {}

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
