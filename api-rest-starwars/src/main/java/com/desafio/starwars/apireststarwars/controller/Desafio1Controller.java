package com.desafio.starwars.apireststarwars.controller;


import com.desafio.starwars.apireststarwars.form.FilmForm;
import com.desafio.starwars.apireststarwars.form.Person;
import com.desafio.starwars.apireststarwars.utils.Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/desafio1")
public class Desafio1Controller {

    private static final String SWAPI_BASE_URL = "https://swapi.dev/api";
    private static final String LUKE_SKYWALKER_ENDPOINT = "/people/1";

    @GetMapping("/films")
    public List<FilmForm> findMovieApi(@RequestParam(required = false) Long id,
                                       @RequestParam(required = false) String title) throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        Person luke = restTemplate.getForObject(SWAPI_BASE_URL + LUKE_SKYWALKER_ENDPOINT, Person.class);
        List<String> filmUrls = luke.getFilms();

        List<FilmForm> films = new ArrayList<>();
        filmUrls.forEach(filmUrl -> films.add(restTemplate.getForObject(filmUrl, FilmForm.class)));

        films.forEach(film -> film.setReleaseDate(Utils.formatDate(Utils.API_SWAPI_FORMAT, Utils.API_REST_FORMAT, film.getReleaseDate())));

        if (id != null) {
            return films.stream().filter(film -> film.getId().equals(id)).collect(Collectors.toList());

        } else if (title != null) {
            return films.stream().filter(film -> film.getTitle().equalsIgnoreCase(title))
                    .collect(Collectors.toList());

        } else {
            return films;
        }
    }
}