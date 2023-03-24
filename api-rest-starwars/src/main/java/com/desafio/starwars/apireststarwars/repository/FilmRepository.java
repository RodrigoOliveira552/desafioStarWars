package com.desafio.starwars.apireststarwars.repository;


import com.desafio.starwars.apireststarwars.model.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<FilmEntity, Long> {

}
