package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;
import com.example.sdcliente.Receivers.Data.ListPointsData;
import com.example.sdcliente.Receivers.Data.ListUsersData;

public class ListPointsReceiver extends BaseReceiver {

    private ListPointsData data = null;

    public ListPointsReceiver(String message, boolean error, ListPointsData data) {
        super(Actions.LIST_POINTS, message, error);

        this.data = data;
    }

    public ListPointsReceiver() {
        super();
    }

    public ListPointsData getData() {
        return data;
    }

    public void setData(ListPointsData data) {
        this.data = data;
    }
}
