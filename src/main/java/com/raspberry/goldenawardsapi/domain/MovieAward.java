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

    public Long getId() {
        return id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudios() {
        return studios;
    }

    public void setStudios(String studios) {
        this.studios = studios;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }
}
