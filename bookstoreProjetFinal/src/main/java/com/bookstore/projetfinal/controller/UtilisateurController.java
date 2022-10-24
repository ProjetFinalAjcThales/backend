package com.bookstore.projetfinal.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bookstore.projetfinal.model.Client;
import com.bookstore.projetfinal.model.exception.EntityNotFoundException;
import com.bookstore.projetfinal.model.security.Utilisateur;
import com.bookstore.projetfinal.repository.UtilisateurRepository;
import com.bookstore.projetfinal.service.ClientService;
import com.bookstore.projetfinal.service.UtilisateurService;


@RestController
@RequestMapping("/utilisateur")
@CrossOrigin
public class UtilisateurController {
	
		@Autowired
		UtilisateurService userService;
	
		@Autowired
		UtilisateurRepository ur;
	
	
	
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public void newPersonne(@RequestBody Utilisateur user, @RequestHeader(required = false) String identifiant) {
		if (!Objects.isNull(user.getId()))
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Ce client existe déjà");
		userService.create(user);
			
	}
		//Lister les utilisateurs de la BDD
		@GetMapping
		public List<Utilisateur> getAll() {
			return userService.getAll();
		}
	
		
		
		//Lister les utilisateurs depuis son ID
		@GetMapping("/{id}")
		public Utilisateur getById(@PathVariable Integer id) {
			return ur.findById(id).orElseThrow(() 
					-> new ResponseStatusException(HttpStatus.NOT_FOUND));
		}
		
		//Mettre à jour un utilisateur
		@PutMapping
		public void update(@RequestBody Utilisateur user) {
			//Vérifier que l'id du client existe dans la BDD
			if(Objects.isNull(user.getId()) || ur.findById(user.getId()).isEmpty())
				throw EntityNotFoundException.responseStatus(Client.class, user.getId());
		
			userService.update(user);
				
		}
		
		
		//Supprimer un client 
		@DeleteMapping("/{id}")
		public void delete(@PathVariable Integer id) {
			userService.delete(id);
			
		}
		
		
	}
	
	
	
	

