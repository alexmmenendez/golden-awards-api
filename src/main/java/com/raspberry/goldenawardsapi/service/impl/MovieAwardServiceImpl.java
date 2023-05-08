package com.raspberry.goldenawardsapi.service.impl;

import com.raspberry.goldenawardsapi.controller.dto.MovieAwardResponse;
import com.raspberry.goldenawardsapi.controller.dto.MovieAwardResultResponse;
import com.raspberry.goldenawardsapi.repository.MovieAwardRepository;
import com.raspberry.goldenawardsapi.service.MovieAwardService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieAwardServiceImpl implements MovieAwardService {

    private final MovieAwardRepository movieAwardRepository;

    public MovieAwardServiceImpl(MovieAwardRepository movieAwardRepository) {
        this.movieAwardRepository = movieAwardRepository;
    }

    @Override
    public MovieAwardResultResponse getMetricsForInterval() {

        final List<MovieAwardResponse> metricsForInterval = movieAwardRepository.getMetricsForInterval()
                .stream().map(MovieAwardResponse::of).collect(Collectors.toList());

        final int min = metricsForInterval.stream().min(Comparator.comparing(MovieAwardResponse::getInterval)).orElseThrow().getInterval();

        final int max = metricsForInterval.stream().max(Comparator.comparing(MovieAwardResponse::getInterval)).orElseThrow().getInterval();

        final List<MovieAwardResponse> metricsForMinInterval = metricsForInterval.stream().filter(
                movieAwardResponse -> movieAwardResponse.getInterval() == min
        ).collect(Collectors.toList());

        final List<MovieAwardResponse> metricsForMaxInterval = metricsForInterval.stream().filter(
                movieAwardResponse -> movieAwardResponse.getInterval() == max
        ).collect(Collectors.toList());

        return new MovieAwardResultResponse(metricsForMinInterval, metricsForMaxInterval);

    }
}
