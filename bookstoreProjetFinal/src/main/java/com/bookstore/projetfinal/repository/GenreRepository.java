package com.bookstore.projetfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.projetfinal.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer>{

}
