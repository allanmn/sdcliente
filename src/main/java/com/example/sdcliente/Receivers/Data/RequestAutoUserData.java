package com.example.sdcliente.Receivers.Data;

import com.example.sdcliente.Models.User;

public class RequestAutoUserData extends BaseData {
    User user = null;

    public RequestAutoUserData(User user) {
        this.user = user;
    }

    public RequestAutoUserData() {}

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
