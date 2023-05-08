package com.raspberry.goldenawardsapi.service;

import com.raspberry.goldenawardsapi.controller.dto.MovieAwardResultResponse;

public interface MovieAwardService {

    MovieAwardResultResponse getMetricsForInterval();
}
