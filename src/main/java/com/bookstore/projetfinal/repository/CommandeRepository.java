package com.bookstore.projetfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.projetfinal.model.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Integer>{

}
