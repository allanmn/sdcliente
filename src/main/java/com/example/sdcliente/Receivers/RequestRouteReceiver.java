package com.example.sdcliente.Receivers;

import com.example.sdcliente.Actions.Actions;
import com.example.sdcliente.Models.RouteSegment;
import com.example.sdcliente.Models.Segment;
import com.example.sdcliente.Receivers.Data.RequestRouteData;
import com.example.sdcliente.Receivers.Data.RequestSegmentData;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RequestRouteReceiver extends BaseReceiver {

    @JsonProperty("segmentos")
    List<RouteSegment> segments = null;

    public RequestRouteReceiver(String message, boolean error, List<RouteSegment> segments) {
        super(Actions.REQUEST_ROUTE, message, error);

        this.segments = segments;
    }

    public RequestRouteReceiver() {
        super();
    }

    public List<RouteSegment> getSegments() {
        return segments;
    }

    public void setSegments(List<RouteSegment> segments) {
        this.segments = segments;
    }
}
