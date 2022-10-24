package com.bookstore.projetfinal.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.server.ResponseStatusException;

import com.bookstore.projetfinal.model.Client;

import com.bookstore.projetfinal.repository.ClientRepository;



@Service
public class ClientService {
	
		@Autowired
		ClientRepository cr;
		
		// créer un nouveau client
		public void create(@RequestBody Client c) {
			if(Objects.nonNull(c.getId()) && cr.findById(c.getId()).isPresent())
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Le client existe deja");
			c.setCreatedAt(LocalDate.now());
			cr.save(c);
		}
			
			
		//lister les utilisateurs de la BDD
		public List<Client> getAll() {
					return cr.findAll();
				}
		
		//récupérer un utilisateur depuis son ID
		public Optional<Client> findById(Integer id) {
			return cr.findById(id);
		}
						
		
		// mettre à jour un client
		public void update(@RequestBody Client u) {
		if(cr.findById(u.getId()).isEmpty())
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Le client n'existe pas");
		cr.save(u);
		}
				
		
		// supprimer un client 
		public void delete(@PathVariable Integer id) {
		cr.deleteById(id);
		}
				

}
