package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;
import com.example.sdcliente.Receivers.Data.RemovePointData;
import com.example.sdcliente.Receivers.Data.RemoveUserData;

public class RemovePointReceiver extends BaseReceiver {

    RemovePointData data;
    public RemovePointReceiver(RemovePointData data) {
        super(Actions.REMOVE_POINT);
        this.data = data;
    }

    public RemovePointReceiver() {
        super();
    }

    public RemovePointData getData() {
        return data;
    }

    public void setData(RemovePointData data) {
        this.data = data;
    }
}
