package com.example.sdcliente.Senders.Data;

import com.example.sdcliente.Models.Validation.ValidationException;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestPointData implements BaseData {
    private String token;

    @JsonProperty("ponto_id")
    private long pointId;

    public RequestPointData(String token, long pointId) {
        this.token = token;
        this.pointId = pointId;
    }

    public RequestPointData() {}

    public boolean validate() throws ValidationException {
        return true;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token = token;
    }

    public long getPointId() {
        return pointId;
    }

    public void setPointId(long pointId) {
        this.pointId = pointId;
    }
}
