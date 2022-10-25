package com.bookstore.projetfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.projetfinal.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}


