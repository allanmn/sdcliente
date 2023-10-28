package com.example.sdcliente.Receivers.Data;

import com.example.sdcliente.Models.User;

import java.util.List;

public class ListUsersData extends BaseData {
    List<User> users = null;

    public ListUsersData(List<User> users) {
        this.users = users;
    }

    public ListUsersData() {}

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
