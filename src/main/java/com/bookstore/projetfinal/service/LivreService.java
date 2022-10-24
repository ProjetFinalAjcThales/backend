package com.bookstore.projetfinal.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.management.relation.RelationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bookstore.projetfinal.model.Auteur;
import com.bookstore.projetfinal.model.Genre;
import com.bookstore.projetfinal.model.Livre;
import com.bookstore.projetfinal.repository.LivreRepository;



@Service
public class LivreService {
	@Autowired
	LivreRepository lr;

	@Autowired
	AuteurService as;

	@Autowired
	GenreService gs;

	// service qui nous retourne une liste de livre
	public List<Livre> getAllLivres() {
		return lr.findAll();
	}

	// acceder un livre par son identifiant
	public Optional<Livre> getLivreById(Integer id) {
		return lr.findById(id);
	}

	// Insertion d'un livre en base
	public Livre createLivre(Livre livre) throws RelationException {
		//ici on fait appel à la methode checkInsertLivre(Livre livre)
		//qui permet de verifier si le body(en Json) de l'objet livre est non nul et ne que l'id n'y doit pas etre renseigné
		//Pour ensuite verifier s'il a un auteur et genre déjà en base au cas échéant d'envoyer des exceptions.
		this.checkInsertLivre(livre);
		
		livre.setDateSortie(LocalDate.now());
		
		Auteur auteurBdd = as.getAuteurById(livre.getAuteur().getId()).get();
		livre.setAuteur(auteurBdd);
		
		Genre genreBdd = gs.getGenreById(livre.getGenre().getId()).get();
		livre.setGenre(genreBdd);
		// 1. Persister la commande en bdd
		return lr.save(livre);

	}
	
	//pour supprimer un livre en base
	public void deleteLivre(Integer id) throws RelationException {
		try {
			lr.deleteById(id);
		} catch (Exception e) {
			throw new RelationException("Une entité liée au livre [" + id + "] existe encore.");
		}
	}
	
	//Mise à jour d'un livre en base
	
	public Livre ModifierLivre(Livre livre) throws RelationException{

		System.out.println(livre);
		this.checkUpdateLivre(livre);
		// je vais aller chercher le livre en base par son Id recuperer sa date de sortie
		//.getDateSortie() et de là je set cette date sortie à mon livre
		//pour ne pas qu'on soit tenter à modifier sa date de sortie
		livre.setDateSortie(lr.findById(livre.getId()).get().getDateSortie());
	
		//on va chercher l'auteur et le genre en base puis les ett	
		
		Auteur auteurBdd = as.getAuteurById(livre.getAuteur().getId()).get();
		livre.setAuteur(auteurBdd);
		
		Genre genreBdd = gs.getGenreById(livre.getGenre().getId()).get();
		livre.setGenre(genreBdd);
		

		//deleteLivre(livre.getId());
		return lr.save(livre);

	}
	
	
	
	

	//ici on a les verifications qu'on fait avant insertion en base
	private void checkInsertLivre(Livre livre) throws RelationException {
		if(Objects.nonNull(livre.getId()) || Objects.isNull(livre)) 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"le body doit être non null et ne pas avoir d'id.");
		
		if (Objects.isNull(livre.getAuteur()) || Objects.isNull(livre.getAuteur().getId()) || !as.getAuteurById(livre.getAuteur().getId()).isPresent()) {
			throw new RelationException("Dans l'objet livre, l'auteur et son id doivent être non null et l'auteur doit déjà existé en BDD.");
		}
		
		if (Objects.isNull(livre.getGenre()) || Objects.isNull(livre.getGenre().getId()) || !gs.getGenreById(livre.getGenre().getId()).isPresent()) {
			throw new RelationException("Dans l'objet livre, le genre et son id doivent être non null et le genre doit déjà existé en BDD.");
		}
		
	}
	
	//ici les verifications concernant les mises a jour d'un livre déjà en base
	private void checkUpdateLivre(Livre livre) throws RelationException {
		if (Objects.isNull(livre) || Objects.isNull(livre.getId()) || !lr.findById(livre.getId()).isPresent()) {
			throw new RelationException(
					"le body est vide, ou ne contient pas d'id ou un id qui n'existe pas.");
		}
//		if (Objects.nonNull(livre.getGenre())) {
//			throw new RelationException("sur le livre à modifier le genre doit être null, seul les autres attributs doivent être mis à jour.");
//		}
//		if (Objects.nonNull(livre.getAuteur())) {
//			throw new RelationException("sur le livre à modifier l'auteur doit être null, seul les autres attributs doivent être mis à jour.");
//		}
		
	}

}
