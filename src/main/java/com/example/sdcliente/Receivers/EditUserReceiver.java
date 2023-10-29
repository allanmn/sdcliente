package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;
import com.example.sdcliente.Receivers.Data.EditUserData;
import com.example.sdcliente.Receivers.Data.RemoveUserData;

public class EditUserReceiver extends BaseReceiver {

    EditUserData data;
    public EditUserReceiver(EditUserData data) {
        super(Actions.EDIT_USER);
        this.data = data;
    }

    public EditUserReceiver() {
        super();
    }

    public EditUserData getData() {
        return data;
    }

    public void setData(EditUserData data) {
        this.data = data;
    }
}
