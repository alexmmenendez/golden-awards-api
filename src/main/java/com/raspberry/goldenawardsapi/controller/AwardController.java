package com.raspberry.goldenawardsapi.controller;

import com.raspberry.goldenawardsapi.controller.dto.MovieAwardResultResponse;
import com.raspberry.goldenawardsapi.service.MovieAwardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/raspberry/api")
@RestController
public class AwardController {

    private final MovieAwardService movieAwardService;

    public AwardController(MovieAwardService movieAwardService) {
        this.movieAwardService = movieAwardService;
    }

    @GetMapping
    public MovieAwardResultResponse getResults() {
        return movieAwardService.getMetricsForInterval();
    }
}
