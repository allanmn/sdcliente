package com.example.sdcliente.Senders.Data;

import com.example.sdcliente.Helpers.Validators;
import com.example.sdcliente.Models.Validation.ValidationException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.codec.digest.DigestUtils;

public class EditPointData implements BaseData {
    private String token;

    @JsonProperty("name")
    private String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String obs;

    @JsonProperty("ponto_id")
    private long pointId;

    public EditPointData(long pointId, String nome, String obs, String token) {
        this.pointId = pointId;
        if (obs != null && !obs.isEmpty()) {
            this.obs = obs;
        }
        this.token = token;
        this.nome = nome;
    }

    public EditPointData() {}

    public boolean validate() throws ValidationException {
        if (nome == null || nome.isEmpty()) {
            throw  new ValidationException("Nome é obrigatório");
        }

        return true;
    }

    public String getToken() {
        return token;
    }

    public String getNome() {
        return nome;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getPointId() {
        return pointId;
    }

    public String getObs() {
        return obs;
    }

    public void setPointId(long pointId) {
        this.pointId = pointId;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
