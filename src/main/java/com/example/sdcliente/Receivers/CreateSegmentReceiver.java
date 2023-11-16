package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;

public class CreateSegmentReceiver extends BaseReceiver {

    public CreateSegmentReceiver(String message, boolean error) {
        super(Actions.CAD_SEGMENT, message, error);
    }

    public CreateSegmentReceiver() {
        super();
    }
}
