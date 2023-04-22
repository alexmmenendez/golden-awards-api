package com.raspberry.goldenawardsapi.domain;

import javax.persistence.*;

@Entity
@Table(name = "movie_award")
public class MovieAward {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "\"year\"", nullable = false)
    private Integer year;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String studios;

    @Column(nullable = false)
    private String producer;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean winner;

    protected MovieAward() { }

    public MovieAward(Integer year, String title, String studios, String producer, boolean winner) {
        this.year = year;
        this.title = title;
        this.studios = studios;
        this.producer = producer;
        this.winner = winner;
    }

}
