package com.bookstore.projetfinal.controller;

import java.util.List;

import javax.management.relation.RelationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.bookstore.projetfinal.model.Livre;
import com.bookstore.projetfinal.service.LivreService;






@RestController
@RequestMapping("/api/livre")
public class LivreController {

	@Autowired
	LivreService ls;

	//methode pour consulter l'ensemble des livres/
	@GetMapping
	private List<Livre> consulterLesLivre() {
		return ls.getAllLivres();
	}
	
	//pour acceder à un livre par son identifiant et acces à l'objet livre
	@GetMapping("/{id}")
	private Livre getLivreById(@PathVariable Integer id) {
		return ls.getLivreById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le livre " + id + " est introuvable"));
	}
	
	//pour la création/insertion livre en base
	@PostMapping
	private Livre createLivre(@RequestBody Livre livre) {

		try {
			return ls.createLivre(livre);
		} catch (RelationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	//pour supprimer un livre par identifiant
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	private void deleteCommand(@PathVariable Integer id) {
		if (!ls.getLivreById(id).isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "l'id : " + id + " n'existe pas ");
		}

		try {
			ls.deleteLivre(id);;
		} catch (RelationException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	
	@PutMapping
	//@ResponseStatus(HttpStatus.ACCEPTED)
	public Livre modifierLivre(@RequestBody Livre livre) {
		try {
			return ls.ModifierLivre(livre);
		} catch (RelationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
