package com.bookstore.projetfinal.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bookstore.projetfinal.model.Genre;
import com.bookstore.projetfinal.service.GenreService;



@RestController
@RequestMapping("/api/genre")
@CrossOrigin
public class GenreController {
	
	@Autowired
	GenreService gs ;
	
	//consulter l'ensemble des genres de livre
	@GetMapping
    public List<Genre> afficherAllGenres() {
        return gs.getAllGenre();
    }

	//afficher un genre grace à son identifiant
    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable Integer id) {
        return gs.getGenreById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id inconnu"));
    }

    //creation/insertion genre en base
    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    public Genre createGenre(@RequestBody Genre genre) {
        if (Objects.isNull(genre))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le genre ne peut pas etre null");
        try{
        return  gs.createGenre(genre);}
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage()); }
       
    }

    //mise à jour genre en base
    @PutMapping
    public Genre updateGenre(@RequestBody Genre genre) {
        if (Objects.isNull(genre))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le genre ne peut pas etre null");
        try {
            return gs.updateGenre(genre);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //supprimer un genre de la base
    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Integer id) {
        if (Objects.isNull(id))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'id ne peut pas etre null");
        gs.deleteGenre(id);
    }

}
