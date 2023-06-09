package com.raspberry.goldenawardsapi.controller.dto;

import java.util.List;

public class MovieAwardResultResponse {

    private final List<MovieAwardResponse> min;
    private final List<MovieAwardResponse> max;

    public MovieAwardResultResponse(List<MovieAwardResponse> min, List<MovieAwardResponse> max) {
        this.min = min;
        this.max = max;
    }

    public List<MovieAwardResponse> getMin() {
        return min;
    }

    public List<MovieAwardResponse> getMax() {
        return max;
    }
}
