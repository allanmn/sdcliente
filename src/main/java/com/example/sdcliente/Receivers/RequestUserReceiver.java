package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;
import com.example.sdcliente.Receivers.Data.RequestUserData;

public class RequestUserReceiver extends BaseReceiver {

    private RequestUserData data = null;

    public RequestUserReceiver(String message, boolean error, RequestUserData data) {
        super(Actions.REQUEST_USER, message, error);

        this.data = data;
    }

    public RequestUserReceiver() {
        super();
    }

    public RequestUserData getData() {
        return data;
    }

    public void setData(RequestUserData data) {
        this.data = data;
    }
}
