package com.example.sdcliente.Senders.Data;

import com.example.sdcliente.Helpers.Validators;
import com.example.sdcliente.Models.Segment;
import com.example.sdcliente.Models.Validation.ValidationException;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.codec.digest.DigestUtils;

public class EditSegmentData implements BaseData {
    private String token;

    @JsonProperty("segmento_id")
    long segmentId;

    @JsonProperty("segmento")
    Segment segment;

    public EditSegmentData(long segmentId, Segment segment, String token) {
        this.segment = segment;
        this.segmentId = segmentId;
        this.token = token;
    }

    public EditSegmentData() {}

    public boolean validate() throws ValidationException {
        if (segment == null) {
            throw  new ValidationException("Segmento é obrigatório");
        }

        if (segment.getPontoDestino() == null) {
            throw  new ValidationException("Ponto de destino é obrigatório");
        }

        if (segment.getPontoOrigem() == null) {
            throw  new ValidationException("Ponto de origem é obrigatório");
        }

        if (segment.getDirecao() == null || segment.getDirecao().isEmpty()) {
            throw  new ValidationException("Direção é obrigatório");
        }

        if (segment.getDistancia() == null || segment.getDistancia().isEmpty()) {
            throw  new ValidationException("Distância é obrigatório");
        }

        if (segmentId == 0) {
            throw  new ValidationException("ID do segmento é obrigatório");
        }

        return true;
    }

    public long getSegmentId() {
        return segmentId;
    }

    public Segment getSegment() {
        return segment;
    }

    public String getToken() {
        return token;
    }

    public void setSegmentId(long segmentId) {
        this.segmentId = segmentId;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
