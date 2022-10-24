package com.bookstore.projetfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.projetfinal.model.Livre;

public interface LivreRepository extends JpaRepository<Livre, Integer>{

}
