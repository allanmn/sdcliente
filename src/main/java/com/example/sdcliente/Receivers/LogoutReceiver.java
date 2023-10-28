package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;
import com.example.sdcliente.Receivers.Data.LogoutData;

public class LogoutReceiver extends BaseReceiver {

    LogoutData data;
    public LogoutReceiver(LogoutData data) {
        super(Actions.LOGOUT);
        this.data = data;
    }

    public LogoutReceiver() {
        super();
    }

    public LogoutData getData() {
        return data;
    }

    public void setData(LogoutData data) {
        this.data = data;
    }
}
