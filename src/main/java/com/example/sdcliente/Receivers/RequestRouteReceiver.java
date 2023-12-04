package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;
import com.example.sdcliente.Receivers.Data.RequestRouteData;
import com.example.sdcliente.Receivers.Data.RequestSegmentData;

public class RequestRouteReceiver extends BaseReceiver {

    private RequestRouteData data = null;

    public RequestRouteReceiver(String message, boolean error, RequestRouteData data) {
        super(Actions.REQUEST_ROUTE, message, error);

        this.data = data;
    }

    public RequestRouteReceiver() {
        super();
    }

    public RequestRouteData getData() {
        return data;
    }

    public void setData(RequestRouteData data) {
        this.data = data;
    }
}
