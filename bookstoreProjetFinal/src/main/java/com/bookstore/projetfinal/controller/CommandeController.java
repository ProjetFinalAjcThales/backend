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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bookstore.projetfinal.model.Commande;
import com.bookstore.projetfinal.model.exception.EntityNotFoundException;
import com.bookstore.projetfinal.model.exception.InvalidQuantityException;
import com.bookstore.projetfinal.service.CommandeService;


@RestController
@CrossOrigin
@RequestMapping("/commande")
public class CommandeController {

	@Autowired
	CommandeService commandeService;

	// GET all commande 
	@GetMapping("/all")
	public List<Commande> getAll() {
		return commandeService.findAll();
	}


	// CREATE a commande
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Commande createCommande(@RequestBody Commande c) {
		//Si la commande a un id => BAD_REQUEST
		if (Objects.nonNull(c.getId()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		try {
			return commandeService.newCommande(c);
		} catch(EntityNotFoundException | InvalidQuantityException e) {
			throw e.toResponse();
		}
	}


	// GET BY ID a commande
	@GetMapping("/{id}")
	public Commande getById(@PathVariable Integer id) {
		return commandeService.findById(id).orElseThrow(EntityNotFoundException.responseStatusSupplier(Commande.class, id));
	}


	//UPDATE a commande 
	@PutMapping
	public void update(@RequestBody Commande c) {
		if (Objects.isNull(c.getId()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La commande n'existe pas");

		try {
			commandeService.update(c);
		} catch (EntityNotFoundException | InvalidQuantityException e) {
			throw e.toResponse();
		}
	}


	// DELETE a commande
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		try {
			commandeService.delete(id);
		} catch (EntityNotFoundException e) {
			throw e.toResponse();
		}
	}

	// GET ALL commande BY CLIENT ID 
	@GetMapping("/client/{id}")
	public List<Commande> getByClientId(@PathVariable Integer id) {
		try {
			return commandeService.getByClientId(id);
		} catch (EntityNotFoundException e) {
			throw e.toResponse();
		}
	}

	// GET ALL not-confirmed commande
	@GetMapping("/not-confirmed")
	public List<Commande> getAllNotConfirmed() {
		return commandeService.getAllConfirmed(false);
	}

	// GET ALL confirmed commande
	@GetMapping("/confirmed")
	public List<Commande> getAllConfirmed() {
		return commandeService.getAllConfirmed(true);
	}


}
