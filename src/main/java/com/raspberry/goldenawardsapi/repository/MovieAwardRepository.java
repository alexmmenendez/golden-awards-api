package com.raspberry.goldenawardsapi.repository;

import com.raspberry.goldenawardsapi.domain.MovieAward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieAwardRepository extends JpaRepository<MovieAward, Long> {

    List<MovieAwardMetric> getMetricsForMaxInterval();

    List<MovieAwardMetric> getMetricsForMinInterval();

}
