package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;
import com.example.sdcliente.Receivers.Data.LoginData;
import com.example.sdcliente.Receivers.Data.RemoveUserData;

public class RemoveUserReceiver extends BaseReceiver {

    RemoveUserData data;
    public RemoveUserReceiver(RemoveUserData data) {
        super(Actions.REMOVE_USER);
        this.data = data;
    }

    public RemoveUserReceiver() {
        super();
    }

    public RemoveUserData getData() {
        return data;
    }

    public void setData(RemoveUserData data) {
        this.data = data;
    }
}
