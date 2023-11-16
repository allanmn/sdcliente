package com.example.sdcliente.Senders.Data;

import com.example.sdcliente.Helpers.Validators;
import com.example.sdcliente.Models.Segment;
import com.example.sdcliente.Models.Validation.ValidationException;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.codec.digest.DigestUtils;

public class SegmentData implements BaseData {
    private String token;

    @JsonProperty("segmento")
    private Segment segmento;

    public SegmentData(Segment segmento, String token) {
        this.token = token;
        this.segmento = segmento;
    }

    public SegmentData() {}

    public boolean validate() throws ValidationException {
        if (segmento == null) {
            throw  new ValidationException("Segmento é obrigatório");
        }

        if (segmento.getPontoOrigem() == null) {
            throw  new ValidationException("Ponto de origem é obrigatório");
        }

        if (segmento.getPontoDestino() == null) {
            throw  new ValidationException("Ponto de destino é obrigatório");
        }

        if (segmento.getDirecao() == null || segmento.getDirecao().isEmpty()) {
            throw  new ValidationException("Direção é obrigatório");
        }

        if (segmento.getDistancia() == null || segmento.getDistancia().isEmpty()) {
            throw  new ValidationException("Distância é obrigatório");
        }

        return true;
    }

    public Segment getSegmento() {
        return segmento;
    }

    public void setSegmento(Segment segmento) {
        this.segmento = segmento;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
