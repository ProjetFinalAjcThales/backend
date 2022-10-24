package com.bookstore.projetfinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.projetfinal.model.Client;
import com.bookstore.projetfinal.model.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Integer>{
	List<Commande> findByConfirme(Boolean confirme);
	
	List<Commande> findByClient(Client c);

}
