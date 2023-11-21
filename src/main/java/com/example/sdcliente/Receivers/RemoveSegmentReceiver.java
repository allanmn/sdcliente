package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;
import com.example.sdcliente.Receivers.Data.RemoveSegmentData;

public class RemoveSegmentReceiver extends BaseReceiver {

    RemoveSegmentData data;
    public RemoveSegmentReceiver(RemoveSegmentData data) {
        super(Actions.REMOVE_SEGMENT);
        this.data = data;
    }

    public RemoveSegmentReceiver() {
        super();
    }

    public RemoveSegmentData getData() {
        return data;
    }

    public void setData(RemoveSegmentData data) {
        this.data = data;
    }
}
