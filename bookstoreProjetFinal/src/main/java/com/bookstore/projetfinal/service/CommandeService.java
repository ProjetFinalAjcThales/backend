package com.bookstore.projetfinal.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.projetfinal.model.Client;
import com.bookstore.projetfinal.model.Commande;
import com.bookstore.projetfinal.model.CommandeLivre;
import com.bookstore.projetfinal.model.Livre;
import com.bookstore.projetfinal.model.exception.EntityNotFoundException;
import com.bookstore.projetfinal.model.exception.InvalidQuantityException;
import com.bookstore.projetfinal.repository.ClientRepository;
import com.bookstore.projetfinal.repository.CommandeLivreRepository;
import com.bookstore.projetfinal.repository.CommandeRepository;
import com.bookstore.projetfinal.repository.LivreRepository;


@Service
public class CommandeService {

	@Autowired
	CommandeRepository commandeRepo;
	
	@Autowired
	CommandeLivreRepository commandeLivreRepo;
	
	@Autowired
	ClientRepository clientRepo;
	
	@Autowired
	LivreRepository livreRepo;
	
	
	// GET all commande 
	public List<Commande> findAll() {
		return commandeRepo.findAll();
	}
	
	// CREATE a commande
	public Commande newCommande(Commande c) throws InvalidQuantityException, EntityNotFoundException {
		c.setConfirme(false);
		c.setDateCreation(LocalDateTime.now());
		return create(c);
	}
	
	@Transactional(rollbackFor= {InvalidQuantityException.class,EntityNotFoundException.class}) 
	public Commande create(Commande c) throws InvalidQuantityException, EntityNotFoundException {
		checkQuantites(c);

		Commande cBdd = commandeRepo.save(c);
		
		for (CommandeLivre cl : c.getLivres()) {
			cl.setCommande(cBdd);
			cl.generateId();
		}
		
		commandeLivreRepo.saveAll(c.getLivres());
		return commandeRepo.findById(cBdd.getId()).get();
	}
	
	// GET BY ID a commande 
	public Optional<Commande> findById(Integer id) {
		return commandeRepo.findById(id);
	}
	
	
	// UPDATE A COMMANDE
	public void update(Commande c) throws EntityNotFoundException, InvalidQuantityException {
		// vérification que l'id de la commande existe bien en bdd
		Commande optC = findById(c.getId()).orElseThrow(() -> new EntityNotFoundException(Commande.class,c.getId()));
		
		c.setDateCreation(optC.getDateCreation()); // On sait jamais si le client essaye de modifier la date..?
		//delete(c.getId());
		create(c);
	}
	
	
	// DELETE a commande
	public void delete(Integer id) throws EntityNotFoundException {
		Commande c = commandeRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(Commande.class,id));// Récupération de la commande
		commandeLivreRepo.deleteAll(c.getLivres());
		commandeRepo.deleteById(id);
	}
	
	// GET ALL confirmed commande
	public List<Commande> getAllConfirmed(Boolean b) {
		return commandeRepo.findByConfirme(b);
	}
	
	// GET ALL commande BY CLIENT ID 
	public List<Commande> getByClientId(Integer id) throws EntityNotFoundException{
		Optional<Client> optC = clientRepo.findById(id);
		if(optC.isEmpty()) throw new EntityNotFoundException(Client.class,id);
		
		return commandeRepo.findByClient(optC.get());
	}
	
	
	private void checkQuantites(Commande c) throws InvalidQuantityException, EntityNotFoundException {
		for (CommandeLivre cl : c.getLivres()) {
			Integer qteStock = cl.getLivre().getQte();
			if(Objects.isNull(qteStock)) // Peut-être qu'on a que les id des produits, pas les libellés ni qté...
				qteStock = livreRepo.findById(cl.getLivre().getId()).map((p) -> p.getQte()).orElseThrow(() -> new EntityNotFoundException(Livre.class, cl.getLivre().getId()));
				
				
			if (qteStock < cl.getQte())
				throw new InvalidQuantityException(cl);
		}
	}
}
