package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;
import com.example.sdcliente.Receivers.Data.ListSegmentsData;
import com.example.sdcliente.Receivers.Data.ListUsersData;

public class ListSegmentsReceiver extends BaseReceiver {

    private ListSegmentsData data = null;

    public ListSegmentsReceiver(String message, boolean error, ListSegmentsData data) {
        super(Actions.LIST_SEGMENTS, message, error);

        this.data = data;
    }

    public ListSegmentsReceiver() {
        super();
    }

    public ListSegmentsData getData() {
        return data;
    }

    public void setData(ListSegmentsData data) {
        this.data = data;
    }
}
