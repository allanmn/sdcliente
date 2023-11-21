package com.example.sdcliente.Receivers.Data;

import com.example.sdcliente.Models.Segment;
import com.example.sdcliente.Models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestSegmentData extends BaseData {
    @JsonProperty("segmento")
    Segment segment = null;

    public RequestSegmentData(Segment segment) {
        this.segment = segment;
    }

    public RequestSegmentData() {}

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }
}
