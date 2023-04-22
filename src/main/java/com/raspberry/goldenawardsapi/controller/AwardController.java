package com.raspberry.goldenawardsapi.controller;

import com.raspberry.goldenawardsapi.controller.dto.MovieAwardResponse;
import com.raspberry.goldenawardsapi.controller.dto.MovieAwardResultResponse;
import com.raspberry.goldenawardsapi.repository.MovieAwardRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/raspberry/api")
@RestController
public class AwardController {

    private final MovieAwardRepository movieAwardRepository;

    public AwardController(MovieAwardRepository movieAwardRepository) {
        this.movieAwardRepository = movieAwardRepository;
    }

    @GetMapping
    public MovieAwardResultResponse getResults() {

        final List<MovieAwardResponse> metricsForMinInterval = movieAwardRepository.getMetricsForMinInterval()
                .stream().map(MovieAwardResponse::of).collect(Collectors.toList());

        final List<MovieAwardResponse> metricsForMaxInterval = movieAwardRepository.getMetricsForMaxInterval()
                .stream().map(MovieAwardResponse::of).collect(Collectors.toList());

        return new MovieAwardResultResponse(metricsForMinInterval, metricsForMaxInterval);

    }
}
