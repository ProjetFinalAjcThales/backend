package com.bookstore.projetfinal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.bookstore.projetfinal.model.security.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
	Optional<Utilisateur> findFirstByMail(String mail);
}
