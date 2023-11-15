package com.example.sdcliente.Receivers.Data;

import com.example.sdcliente.Models.Point;
import com.example.sdcliente.Models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListPointsData extends BaseData {
    @JsonProperty("pontos")
    List<Point> points = null;

    public ListPointsData(List<Point> points) {
        this.points = points;
    }

    public ListPointsData() {}

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
