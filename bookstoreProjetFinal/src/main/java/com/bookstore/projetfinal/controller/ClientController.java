package com.bookstore.projetfinal.controller;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bookstore.projetfinal.model.Client;
import com.bookstore.projetfinal.model.exception.EntityNotFoundException;
import com.bookstore.projetfinal.service.ClientService;

@RestController
@RequestMapping("/client")
@CrossOrigin
public class ClientController {

	@Autowired
	ClientService clientService;

	// Lister les utilisateurs de la BDD
	@GetMapping
	public List<Client> getAll() {
		return clientService.getAll();
	}

	// UPDATE client
	@PutMapping
	public Client update(@RequestBody Client client) {
		// Vérifier que l'id du client existe dans la BDD
		if (Objects.isNull(client.getId()) || clientService.findById(client.getId()).isEmpty())
			throw EntityNotFoundException.responseStatus(Client.class, client.getId());
		return clientService.update(client);
	}

	// Get Client By ID
	@GetMapping("/{id}")
	public Client getById(@PathVariable Integer id) {
		return clientService.findById(id).orElseThrow(EntityNotFoundException.responseStatusSupplier(Client.class, id));
	}

	// Supprimer un client
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		clientService.delete(id);

	}

}
