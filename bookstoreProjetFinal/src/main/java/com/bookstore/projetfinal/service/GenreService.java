package com.bookstore.projetfinal.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bookstore.projetfinal.model.Genre;
import com.bookstore.projetfinal.repository.GenreRepository;

@Service
public class GenreService {
	@Autowired
	GenreRepository gr;

	// methode pour retourner la liste des genres de livre
	public List<Genre> getAllGenre() {
		return gr.findAll();
	}

	// retrouver un genre par son identifiant
	public Optional<Genre> getGenreById(Integer id) {
		if (Objects.isNull(id))
			throw new IllegalArgumentException("id can not be null");
		return gr.findById(id);
	}

	// pouvoir inserer des genres en base
	public Genre createGenre(Genre genre) {
		if (genre.getId() != null && gr.findById(genre.getId()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le genre existe déjà");
		System.out.println("test");
		return gr.save(genre);
	}

	// mise a jour du genre
	public Genre updateGenre(Genre g) {
		checkGenre(g);
		return gr.save(g);
	}

	private void checkGenre(Genre g) {
		if (Objects.isNull(g.getId()) || !gr.findById(g.getId()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le genre n'a pas d'id");
		}
	}

	public void deleteGenre(Integer id) {
		if (Objects.isNull(id))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'id ne peut pas etre null");
		if (!gr.findById(id).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'id n'xiste pas en base de donnees");
		gr.deleteById(id);
	}

}
