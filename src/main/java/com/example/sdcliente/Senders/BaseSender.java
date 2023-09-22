package com.example.sdcliente.Senders;

import com.example.sdcliente.Actions.Actions;
import com.example.sdcliente.Senders.Data.BaseData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseSender {

    private static final ObjectMapper jackson = new ObjectMapper();

    private String action;

    private BaseData data;

    public BaseSender(String action, BaseData data) {
        this.action = action;
        this.data = data;
    }

    public BaseSender() {

    }

    public String toJson() throws JsonProcessingException {
        return jackson.writeValueAsString(this);
    }
    public static <T> T fromJson(String json, Class<T> generic_response) throws JsonProcessingException {
        return jackson.readValue(json, generic_response);
    }

    public String getAction() {
        return action;
    }

    public BaseData getData() {
        return data;
    }

    public void setData(BaseData data) {
        this.data = data;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
