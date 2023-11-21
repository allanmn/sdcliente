package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;
import com.example.sdcliente.Receivers.Data.RequestSegmentData;

public class RequestSegmentReceiver extends BaseReceiver {

    private RequestSegmentData data = null;

    public RequestSegmentReceiver(String message, boolean error, RequestSegmentData data) {
        super(Actions.REQUEST_SEGMENT, message, error);

        this.data = data;
    }

    public RequestSegmentReceiver() {
        super();
    }

    public RequestSegmentData getData() {
        return data;
    }

    public void setData(RequestSegmentData data) {
        this.data = data;
    }
}
