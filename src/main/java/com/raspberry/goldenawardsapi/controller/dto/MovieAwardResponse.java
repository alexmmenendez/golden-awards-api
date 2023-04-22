package com.raspberry.goldenawardsapi.controller.dto;

import com.raspberry.goldenawardsapi.repository.MovieAwardMetric;

public class MovieAwardResponse {

    private final String producer;
    private final int interval;
    private final int previousWin;
    private final int followingWin;

    public MovieAwardResponse(String producer, int interval, int previousWin, int followingWin) {
        this.producer = producer;
        this.interval = interval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }

    public String getProducer() {
        return producer;
    }

    public int getInterval() {
        return interval;
    }

    public int getPreviousWin() {
        return previousWin;
    }

    public int getFollowingWin() {
        return followingWin;
    }

    public static MovieAwardResponse of(MovieAwardMetric metric) {
        return new MovieAwardResponse(metric.getProducer(), metric.getInterval(), metric.getPreviousWin(), metric.getFollowingWin());
    }
}
