package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;

public class RegisterUserReceiver extends BaseReceiver {

    public RegisterUserReceiver(String message, boolean error) {
        super(Actions.REGISTER_USER, message, error);
    }

    public RegisterUserReceiver() {
        super();
    }
}
