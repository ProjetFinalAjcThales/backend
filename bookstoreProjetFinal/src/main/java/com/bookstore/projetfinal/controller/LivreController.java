package com.bookstore.projetfinal.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.management.relation.RelationException;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bookstore.projetfinal.model.Livre;
import com.bookstore.projetfinal.service.LivreService;





@RestController
@RequestMapping("/api/livre")
@CrossOrigin
public class LivreController {

	@Autowired
	LivreService ls;

	List<Livre> livres = new ArrayList<Livre>();
	
	//methode pour consulter l'ensemble des livres/
	@GetMapping
	private List<Livre> consulterLesLivre() {
		livres = ls.getAllLivres();
		return livres;
		

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
	
	//mise a jour d'un livre en base 
	@PutMapping
	//@ResponseStatus(HttpStatus.ACCEPTED)
	public Livre modifierLivre(@RequestBody Livre livre) {
		try {
			return ls.ModifierLivre(livre);
		} catch (RelationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	 //ici on fait recherche par le titre du livre qu'on souhaite voir
	@GetMapping("/bytitre")
	public List<Livre> rechercheByTitreLivre(@RequestParam(required = false) String search) {
		System.out.println(search);
		if (search == null)
			return livres;
		System.out.println(livres);

		List<Livre> listeLivreByTitre = new ArrayList<>();

		for (Livre l : livres) {
			if (l.getTitre().toLowerCase().contains(search.toLowerCase())) {
					
				listeLivreByTitre.add(l);
			}
		}
		return listeLivreByTitre;
	}
	
	//pour une recherche par le nom ou le prenom de l'auteur
	@GetMapping("/byauteur")
	public List<Livre> rechercheByAuteurLivre(@RequestParam(required = false) String search) {
		System.out.println(search);
		if (search == null)
			return livres;
		System.out.println(livres);

		List<Livre> listeLivreByAuteur = new ArrayList<>();

		for (Livre l : livres) {
			if (l.getAuteur().getNom().toLowerCase().contains(search.toLowerCase())
					|| l.getAuteur().getPrenom().toLowerCase().contains(search.toLowerCase())) {
				listeLivreByAuteur.add(l);
			}
		}
		return listeLivreByAuteur;
	}
	
	//pour une recherche selon le genre du livre
		@GetMapping("/bygenre")
		public List<Livre> rechercheByGenreLivre(@RequestParam(required = false) String search) {
			System.out.println(search);
			if (search == null)
				return livres;
			System.out.println(livres);

			List<Livre> listeLivreByGenre = new ArrayList<>();

			for (Livre l : livres) {
				if (l.getGenre().getNom().toLowerCase().contains(search.toLowerCase())) {
					listeLivreByGenre.add(l);
				}
			}
			return listeLivreByGenre;
		}
	
	

}
