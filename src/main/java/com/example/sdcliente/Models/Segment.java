package com.example.sdcliente.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Segment {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private long id;

    @JsonProperty("ponto_origem")
    private Point pontoOrigem;

    @JsonProperty("ponto_destino")
    private Point pontoDestino;

    private String direcao;

    private int distancia;

    private boolean bloqueado;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String obs;

    public Segment(Point pontoDestino, Point pontoOrigem, String direcao, int distancia, String obs, boolean bloqueado) {
        this.pontoDestino = pontoDestino;
        this.pontoOrigem = pontoOrigem;
        this.direcao = direcao;
        this.distancia = distancia;
        if (obs != null && !obs.isEmpty()) {
            this.obs = obs;
        }
        this.bloqueado = bloqueado;
    }

    public Segment() {}

    public String getObs() {
        return obs;
    }

    public long getId() {
        return id;
    }

    public Point getPontoDestino() {
        return pontoDestino;
    }

    public Point getPontoOrigem() {
        return pontoOrigem;
    }

    public String getDirecao() {
        return direcao;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public void setPontoDestino(Point pontoDestino) {
        this.pontoDestino = pontoDestino;
    }

    public void setPontoOrigem(Point pontoOrigem) {
        this.pontoOrigem = pontoOrigem;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public boolean getBloqueado () {
        return this.bloqueado;
    }
}
