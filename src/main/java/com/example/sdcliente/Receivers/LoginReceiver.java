package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;
import com.example.sdcliente.Receivers.Data.LoginData;

public class LoginReceiver extends BaseReceiver {

    LoginData data;
    public LoginReceiver(LoginData data) {
        super(Actions.LOGIN);
        this.data = data;
    }

    public LoginReceiver() {
        super();
    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }
}
