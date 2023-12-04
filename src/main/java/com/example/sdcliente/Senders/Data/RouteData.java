package com.example.sdcliente.Senders.Data;

import com.example.sdcliente.Models.Point;
import com.example.sdcliente.Models.Segment;
import com.example.sdcliente.Models.Validation.ValidationException;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RouteData implements BaseData {
    @JsonProperty("ponto_origem")
    private Point pontoOrigem;

    @JsonProperty("ponto_destino")
    private Point pontoDestino;

    public RouteData(Point pontoOrigem, Point pontoDestino) {
        this.pontoDestino = pontoDestino;
        this.pontoOrigem = pontoOrigem;
    }

    public RouteData() {}

    public boolean validate() throws ValidationException {
        if (this.pontoOrigem == null) {
            throw new ValidationException("Ponto de origem é obrigatório");
        }

        if (this.pontoDestino == null) {
            throw new ValidationException("Ponto de destino é obrigatório");
        }

        return true;
    }

    public void setPontoOrigem(Point pontoOrigem) {
        this.pontoOrigem = pontoOrigem;
    }

    public void setPontoDestino(Point pontoDestino) {
        this.pontoDestino = pontoDestino;
    }

    public Point getPontoOrigem() {
        return pontoOrigem;
    }

    public Point getPontoDestino() {
        return pontoDestino;
    }
}
