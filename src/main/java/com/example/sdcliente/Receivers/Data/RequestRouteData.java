package com.example.sdcliente.Receivers.Data;

import com.example.sdcliente.Models.Segment;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RequestRouteData extends BaseData {
    @JsonProperty("segmentos")
    List<Segment> segments = null;

    public RequestRouteData(List<Segment> segments) {
        this.segments = segments;
    }

    public RequestRouteData() {}

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public List<Segment> getSegments() {
        return segments;
    }
}
