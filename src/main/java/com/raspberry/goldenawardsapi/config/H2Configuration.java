package com.raspberry.goldenawardsapi.config;

import com.raspberry.goldenawardsapi.domain.MovieAward;
import com.raspberry.goldenawardsapi.repository.MovieAwardRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Component
public class H2Configuration implements ApplicationRunner {

    @Value("classpath:movielist-manipulado.csv")
    Resource resourceFile;

    @Autowired
    private MovieAwardRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (repository.count() > 0) {
            return;
        }

        final var cSVFormat = CSVFormat.DEFAULT.builder()
                .setDelimiter(';')
                .setHeader()
                .setSkipHeaderRecord(true)
                .build();

        try (InputStream inputStream = new ClassPathResource("movielist.csv").getInputStream();

             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

//            final var reader = Files.newBufferedReader(resourceFile.getFile().toPath());

             final var csvParser = new CSVParser(reader, cSVFormat)
        ) {

            final var list = csvParser.getHeaderNames();

            for (var csvRecord : csvParser) {

                final var year = Integer.parseInt(csvRecord.get(0));
                final var title = csvRecord.get(1);
                final var studios = csvRecord.get(2);
                final var producers = csvRecord.get(3);
                final var winner = convertToBoolean(csvRecord.get(4));

                final var producerNames = convertToList(producers);

                for (var producerName : producerNames) {
                    final var movieAward = new MovieAward(
                            year, title, studios, producerName, winner
                    );

                    System.out.println(csvRecord.getRecordNumber() + " - " + year + " - " + title + " - " + studios + " - " + producerName + " - " + winner);
                    repository.save(movieAward);
                }


            }
        }
    }

    private List<String> convertToList(final String text) {
       return Arrays.asList(text.replace(", and ", ", ").replace(" and ", ", ").split(", "));
    }

    private boolean convertToBoolean(final String text) {
        return text.contains("yes");
    }
}
