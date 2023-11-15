package com.example.sdcliente.Receivers.Data;

import com.example.sdcliente.Models.Point;
import com.example.sdcliente.Models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestPointData extends BaseData {
    @JsonProperty("ponto")
    Point point = null;

    public RequestPointData(Point point) {
        this.point = point;
    }

    public RequestPointData() {}

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }
}
