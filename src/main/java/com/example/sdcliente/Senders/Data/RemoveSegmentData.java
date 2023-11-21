package com.example.sdcliente.Senders.Data;

import com.example.sdcliente.Models.Validation.ValidationException;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoveSegmentData implements BaseData {
    private String token;

    @JsonProperty("segmento_id")
    private long segmentId;

    public RemoveSegmentData(String token, long segmentoId) {
        this.token = token;
        this.segmentId = segmentoId;
    }

    public RemoveSegmentData() {}

    public boolean validate() throws ValidationException {
        return true;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token = token;
    }

    public void setSegmentId(long segmentoId) {
        this.segmentId = segmentoId;
    }

    public long getSegmentId() {
        return segmentId;
    }
}
