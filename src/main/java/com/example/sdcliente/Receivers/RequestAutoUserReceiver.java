package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;
import com.example.sdcliente.Receivers.Data.RequestAutoUserData;
import com.example.sdcliente.Receivers.Data.RequestUserData;

public class RequestAutoUserReceiver extends BaseReceiver {

    private RequestAutoUserData data = null;

    public RequestAutoUserReceiver(String message, boolean error, RequestAutoUserData data) {
        super(Actions.REQUEST_AUTO_USER, message, error);

        this.data = data;
    }

    public RequestAutoUserReceiver() {
        super();
    }

    public RequestAutoUserData getData() {
        return data;
    }

    public void setData(RequestAutoUserData data) {
        this.data = data;
    }
}
