package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;

public class CreatePointReceiver extends BaseReceiver {

    public CreatePointReceiver(String message, boolean error) {
        super(Actions.CAD_POINT, message, error);
    }

    public CreatePointReceiver() {
        super();
    }
}
