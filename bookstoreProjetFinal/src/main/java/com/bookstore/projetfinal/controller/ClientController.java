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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bookstore.projetfinal.model.Client;
import com.bookstore.projetfinal.model.exception.EntityNotFoundException;
import com.bookstore.projetfinal.service.ClientService;

@RestController
@RequestMapping("/client")
@CrossOrigin
public class ClientController {

	@Autowired
	ClientService clientService;
			
	//Créer un nouveau client
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void newPersonne(@RequestBody Client client, @RequestHeader(required = false) String identifiant) {
		if (!Objects.isNull(client.getId()))
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Ce client existe déjà");
		clientService.create(client);
			
	}
	
	//Lister les utilisateurs de la BDD
	@GetMapping
	public List<Client> getAll() {
		return clientService.getAll();
	}
	
	//Mettre à jour un client
	@GetMapping("/{id}")
	public Client getById(@PathVariable Integer id) {
		return clientService.findById(id).orElseThrow(EntityNotFoundException.responseStatusSupplier(Client.class, id));
	}
	
	@PutMapping
	public void update(@RequestBody Client client) {
		//Vérifier que l'id du client existe dans la BDD
		if(Objects.isNull(client.getId()) || clientService.findById(client.getId()).isEmpty())
			throw EntityNotFoundException.responseStatus(Client.class, client.getId());
	
		clientService.update(client);
			
	}
	//Supprimer un client 
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		clientService.delete(id);
		
	}
	
}

	
	
	

	
	
	

