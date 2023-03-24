package com.desafio.starwars.apireststarwars.controller;

import com.desafio.starwars.apireststarwars.form.FilmForm;
import com.desafio.starwars.apireststarwars.form.Person;
import com.desafio.starwars.apireststarwars.model.FilmEntity;
import com.desafio.starwars.apireststarwars.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.desafio.starwars.apireststarwars.utils.Utils.API_REST_FORMAT;
import static com.desafio.starwars.apireststarwars.utils.Utils.formatDate;

@RestController
@RequestMapping("/desafio2")
public class Desafio2Controller {

    @Autowired
    private FilmRepository repository;

    private static final String SWAPI_BASE_URL = "https://swapi.dev/api";
    private static final String LUKE_SKYWALKER_ENDPOINT = "/people/1";

    @GetMapping("/films")
    public List<FilmEntity> findAllFilms() {
        List<FilmEntity> films = repository.findAll();

        films.forEach(film -> film.setReleaseDate(formatDate(API_REST_FORMAT, API_REST_FORMAT, film.getReleaseDate())));

        return films;
    }

    @PostMapping("/films")
    public void saveFilm(@RequestBody FilmForm filmForm) throws Exception {

        FilmEntity entity = new FilmEntity();
        entity.setDirector(filmForm.getDirector());
        entity.setReleaseDate(filmForm.getReleaseDate());
        entity.setTitle(filmForm.getTitle());

        repository.save(entity);

        repository.findAll().forEach(System.out::println);
    }

    @PutMapping("/films")
    public void fillMovies() throws Exception {

        List<FilmEntity> films = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        Person luke = restTemplate.getForObject(SWAPI_BASE_URL + LUKE_SKYWALKER_ENDPOINT, Person.class);
        for (String url : luke.getFilms()) {
            FilmForm form = restTemplate.getForObject(url, FilmForm.class);

            FilmEntity entity = new FilmEntity();
            entity.setTitle(form.getTitle());
            entity.setDirector(form.getDirector());
            entity.setReleaseDate(form.getReleaseDate());
            films.add(entity);
        }

        repository.saveAll(films);
    }
}