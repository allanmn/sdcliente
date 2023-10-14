package com.example.sdcliente.Receivers.Data;

import com.example.sdcliente.Models.User;

import java.util.List;

public class ListUsersData extends BaseData {
    List<User> usuarios = null;

    public ListUsersData(List<User> usuarios) {
        this.usuarios = usuarios;
    }

    public ListUsersData() {}

    public void setUsuarios(List<User> usuarios) {
        this.usuarios = usuarios;
    }

    public List<User> getUsuarios() {
        return usuarios;
    }
}
