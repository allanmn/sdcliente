package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;
import com.example.sdcliente.Receivers.Data.LogoutData;
import com.example.sdcliente.Receivers.Data.RemoveSelfUserData;

public class RemoveSelfUserReceiver extends BaseReceiver {

    RemoveSelfUserData data;
    public RemoveSelfUserReceiver(RemoveSelfUserData data) {
        super(Actions.REMOVE_SELF_USER);
        this.data = data;
    }

    public RemoveSelfUserReceiver() {
        super();
    }

    public RemoveSelfUserData getData() {
        return data;
    }

    public void setData(RemoveSelfUserData data) {
        this.data = data;
    }
}
