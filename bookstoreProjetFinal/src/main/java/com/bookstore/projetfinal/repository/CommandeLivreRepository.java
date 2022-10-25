package com.bookstore.projetfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.projetfinal.model.CommandeLivre;
import com.bookstore.projetfinal.model.embeddedId.CommandeLivreId;

public interface CommandeLivreRepository extends JpaRepository<CommandeLivre, CommandeLivreId>{

}
