package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;
import com.example.sdcliente.Receivers.Data.LoginData;

public class CreateUserReceiver extends BaseReceiver {

    public CreateUserReceiver(String message, boolean error) {
        super(Actions.CAD_USER, message, error);
    }

    public CreateUserReceiver() {
        super();
    }
}
