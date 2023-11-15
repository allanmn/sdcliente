package com.example.sdcliente.Senders.Data;

import com.example.sdcliente.Helpers.Validators;
import com.example.sdcliente.Models.Validation.ValidationException;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.codec.digest.DigestUtils;

public class PointData implements BaseData {
    private String token;

    @JsonProperty("name")
    private String nome;

    @JsonProperty("obs")
    private String obs;

    public PointData(String nome, String obs, String token) {
        this.obs = obs;
        this.token = token;
        this.nome = nome;
    }

    public PointData() {}

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

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
