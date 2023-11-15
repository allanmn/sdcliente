package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;
import com.example.sdcliente.Receivers.Data.ListUsersData;

public class ListUsersReceiver extends BaseReceiver {

    private ListUsersData data = null;

    public ListUsersReceiver(String message, boolean error, ListUsersData data) {
        super(Actions.LIST_USERS, message, error);

        this.data = data;
    }

    public ListUsersReceiver() {
        super();
    }

    public ListUsersData getData() {
        return data;
    }

    public void setData(ListUsersData data) {
        this.data = data;
    }
}
