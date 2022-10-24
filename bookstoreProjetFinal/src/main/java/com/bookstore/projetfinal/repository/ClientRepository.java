package com.bookstore.projetfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.projetfinal.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
