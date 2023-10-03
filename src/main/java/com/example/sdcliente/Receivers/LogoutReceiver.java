package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;
import com.example.sdcliente.Receivers.Data.LoginData;

public class LogoutReceiver extends BaseReceiver {

    LoginData data;
    public LogoutReceiver(LoginData data) {
        super(Actions.LOGOUT);
        this.data = data;
    }

    public LogoutReceiver() {
        super();
    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }
}
