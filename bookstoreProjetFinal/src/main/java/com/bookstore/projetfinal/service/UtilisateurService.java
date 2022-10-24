 package com.bookstore.projetfinal.service;


import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.bookstore.projetfinal.model.exception.EntityNotFoundException;
import com.bookstore.projetfinal.model.security.Utilisateur;
import com.bookstore.projetfinal.repository.UtilisateurRepository;



@Service
public class UtilisateurService {

	@Autowired
	UtilisateurRepository ur;
	
			//créer un nouveau utilisateur
			public void create(@RequestBody Utilisateur u) {
				if(Objects.nonNull(u.getId()) && ur.findById(u.getId()).isPresent())
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"L'utilisateur existe déjà");
				ur.save(u);
			}
			
			// lister les utilisateurs de la BDD
			public List<Utilisateur> getAll() {
				return ur.findAll();
			}
			
			//récupérer un utilisateur depuis son ID
			public Utilisateur getById(@PathVariable Integer id) {
				return ur.findById(id).orElseThrow(() 
						-> new ResponseStatusException(HttpStatus.NOT_FOUND));
			}
						
			// mettre à jour un utilisateur
			public void update(@RequestBody Utilisateur u) {
				// Vérifier si l'utilisateur existe
				if(ur.findById(u.getId()).isEmpty())
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,"L'utilisateur n'existe pas");
				ur.save(u);
			}
		
			// supprimer un utilisateur 
			public void delete(@PathVariable Integer id) {
				ur.deleteById(id);
			}
		
}
