package com.desafio.starwars.apireststarwars.form;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FilmForm {

    @JsonProperty("episode_id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("director")
    private String director;

    @JsonProperty("release_date")
    private String releaseDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "FilmForm [id=" + id + ", title=" + title + ", director=" + director + ", releaseDate=" + releaseDate
                + "]";
    }
}
