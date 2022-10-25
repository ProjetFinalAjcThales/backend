package com.bookstore.projetfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookstore.projetfinal.model.Livre;



public interface LivreRepository extends JpaRepository<Livre, Integer>{
	
	@Query(value = "SELECT * FROM livre where titre = :titre", nativeQuery=true)
	 Livre findLivreByTitre(String titre);

}
