package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;
import com.example.sdcliente.Receivers.Data.RequestPointData;
import com.example.sdcliente.Receivers.Data.RequestUserData;

public class RequestPointReceiver extends BaseReceiver {

    private RequestPointData data = null;

    public RequestPointReceiver(String message, boolean error, RequestPointData data) {
        super(Actions.REQUEST_POINT, message, error);

        this.data = data;
    }

    public RequestPointReceiver() {
        super();
    }

    public RequestPointData getData() {
        return data;
    }

    public void setData(RequestPointData data) {
        this.data = data;
    }
}
