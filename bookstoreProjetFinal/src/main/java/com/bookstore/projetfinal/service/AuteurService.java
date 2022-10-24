package com.bookstore.projetfinal.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bookstore.projetfinal.model.Auteur;
import com.bookstore.projetfinal.repository.AuteurRepository;

@Service
public class AuteurService {
	@Autowired
	AuteurRepository ar;

	// methode pour retourner la liste des auteurs de livre
	public List<Auteur> getAllAuteur() {
		return ar.findAll();
	}

	// retrouver un auteur par son identifiant
	public Optional<Auteur> getAuteurById(Integer id) {
		if (Objects.isNull(id))
			throw new IllegalArgumentException("id can not be null");
		return ar.findById(id);
	}

	// pouvoir inserer des auteur en base
	public Auteur createAuteur(Auteur auteur) {
		if (auteur.getId() != null && ar.findById(auteur.getId()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'auteur existe déjà");
		System.out.println("test");
		return ar.save(auteur);
	}

	// mise a jour d'un auteur
	public Auteur updateAuteur(Auteur a) {
		checkAuteur(a);
		return ar.save(a);
	}

	private void checkAuteur(Auteur a) {
		if (Objects.isNull(a.getId()) || !ar.findById(a.getId()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'auteur n'a pas d'id");
		}
	}

	public void deleteAuteur(Integer id) {
		if (Objects.isNull(id))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'id ne peut pas etre null");
		if (!ar.findById(id).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'id n'xiste pas en base de donnees");
		ar.deleteById(id);
	}



}
