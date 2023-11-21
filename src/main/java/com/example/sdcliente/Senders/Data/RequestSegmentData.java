package com.example.sdcliente.Senders.Data;

import com.example.sdcliente.Models.Validation.ValidationException;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestSegmentData implements BaseData {
    private String token;

    @JsonProperty("segmento_id")
    private long segmentId;

    public RequestSegmentData(String token, long segmentId) {
        this.token = token;
        this.segmentId = segmentId;
    }

    public RequestSegmentData() {}

    public boolean validate() throws ValidationException {
        return true;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token = token;
    }

    public void setSegmentId(long segmentId) {
        this.segmentId = segmentId;
    }

    public long getSegmentId() {
        return segmentId;
    }
}
