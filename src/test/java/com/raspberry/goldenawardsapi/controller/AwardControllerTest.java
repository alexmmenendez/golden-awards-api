package com.raspberry.goldenawardsapi.controller;

import com.raspberry.goldenawardsapi.GoldenAwardsApiApplication;
import com.raspberry.goldenawardsapi.config.DataCsvImporter;
import com.raspberry.goldenawardsapi.repository.MovieAwardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = GoldenAwardsApiApplication.class)
@AutoConfigureMockMvc
class AwardControllerTest {

    private static final String ENDPOINT = "/movie-award/metrics";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieAwardRepository repository;

    private DataCsvImporter dataCsvImporter;

    @BeforeEach
    public void before() {
        dataCsvImporter = new DataCsvImporter(repository);
    }

    @AfterEach
    public void afterEach() {
        repository.deleteAll();
    }

    @Test
    void shouldReturnOneResultForMinIntervalAndForMaxInterval() throws Exception {
        dataCsvImporter.execute("should-return-one-result-for-min-interval-and-for-max-interval.csv");

        mockMvc.perform(get(ENDPOINT)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.min[0].producer", is("Joel Silver")))
                .andExpect(jsonPath("$.min[0].interval", is(1)))
                .andExpect(jsonPath("$.min[0].previousWin", is(1990)))
                .andExpect(jsonPath("$.min[0].followingWin", is(1991)))
                .andExpect(jsonPath("$.max[0].producer", is("Matthew Vaughn")))
                .andExpect(jsonPath("$.max[0].interval", is(13)))
                .andExpect(jsonPath("$.max[0].previousWin", is(2002)))
                .andExpect(jsonPath("$.max[0].followingWin", is(2015)));
    }

    @Test
    void shouldReturnMinIntervalBiggestThanOne() throws Exception {
        dataCsvImporter.execute("should-return-min-interval-biggest-than-one.csv");

        mockMvc.perform(get(ENDPOINT)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.min[0].producer", is("Bo Derek")))
                .andExpect(jsonPath("$.min[0].interval", is(6)))
                .andExpect(jsonPath("$.min[0].previousWin", is(1984)))
                .andExpect(jsonPath("$.min[0].followingWin", is(1990)))
                .andExpect(jsonPath("$.max[0].producer", is("Matthew Vaughn")))
                .andExpect(jsonPath("$.max[0].interval", is(13)))
                .andExpect(jsonPath("$.max[0].previousWin", is(2002)))
                .andExpect(jsonPath("$.max[0].followingWin", is(2015)));
    }

    @Test
    void shouldReturnMoreThanOneResultForMinIntervalAndForMaxInterval() throws Exception {
        dataCsvImporter.execute("should-return-more-than-one-result-for-min-interval-and-for-max-interval.csv");

        mockMvc.perform(get(ENDPOINT)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.min[0].producer", is("Buzz Feitshans")))
                .andExpect(jsonPath("$.min[0].interval", is(1)))
                .andExpect(jsonPath("$.min[0].previousWin", is(1985)))
                .andExpect(jsonPath("$.min[0].followingWin", is(1986)))
                .andExpect(jsonPath("$.min[1].producer", is("Joel Silver")))
                .andExpect(jsonPath("$.min[1].interval", is(1)))
                .andExpect(jsonPath("$.min[1].previousWin", is(1990)))
                .andExpect(jsonPath("$.min[1].followingWin", is(1991)))
                .andExpect(jsonPath("$.max[0].producer", is("Matthew Vaughn")))
                .andExpect(jsonPath("$.max[0].interval", is(13)))
                .andExpect(jsonPath("$.max[0].previousWin", is(2002)))
                .andExpect(jsonPath("$.max[0].followingWin", is(2015)))
                .andExpect(jsonPath("$.max[1].producer", is("M. Night Shyamalan")))
                .andExpect(jsonPath("$.max[1].interval", is(13)))
                .andExpect(jsonPath("$.max[1].previousWin", is(2010)))
                .andExpect(jsonPath("$.max[1].followingWin", is(2023)));
    }

    @Test
    void shouldReturnNoContentStatus() throws Exception {
        dataCsvImporter.execute("should-return-no-content-status.csv");
        mockMvc.perform(get(ENDPOINT)).andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnTheSameResultForMinIntervalAndForMaxInterval() throws Exception {
        dataCsvImporter.execute("should-return-the-same-result-for-min-interval-and-for-max-interval.csv");

        mockMvc.perform(get(ENDPOINT)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.min[0].producer", is("Buzz Feitshans")))
                .andExpect(jsonPath("$.min[0].interval", is(9)))
                .andExpect(jsonPath("$.min[0].previousWin", is(1985)))
                .andExpect(jsonPath("$.min[0].followingWin", is(1994)))
                .andExpect(jsonPath("$.max[0].producer", is("Buzz Feitshans")))
                .andExpect(jsonPath("$.max[0].interval", is(9)))
                .andExpect(jsonPath("$.max[0].previousWin", is(1985)))
                .andExpect(jsonPath("$.max[0].followingWin", is(1994)));
    }

}
