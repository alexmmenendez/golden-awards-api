package com.raspberry.goldenawardsapi.repository;

public interface MovieAwardMetric {

    String getProducer();
    int getInterval();
    int getPreviousWin();
    int getFollowingWin();

}
