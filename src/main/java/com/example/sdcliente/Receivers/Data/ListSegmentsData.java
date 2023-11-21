package com.example.sdcliente.Receivers.Data;

import com.example.sdcliente.Models.Segment;
import com.example.sdcliente.Models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListSegmentsData extends BaseData {

    @JsonProperty("segmentos")
    List<Segment> segments = null;

    public ListSegmentsData(List<Segment> segments) {
        this.segments = segments;
    }

    public ListSegmentsData() {}

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public List<Segment> getSegments() {
        return segments;
    }
}
