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

import com.bookstore.projetfinal.model.Auteur;
//import com.bookstore.projetfinal.service.AuteurService;

@RestController
@RequestMapping("/api/auteur")
@CrossOrigin
public class AuteurController {
//	@Autowired
//	AuteurService as;
//	
//	//Pour afficher l'ensemble des auteurs en base
//	@GetMapping
//    public List<Auteur> afficherAllAuteurs() {
//        return as.getAllAuteur();
//    }
//
//	//Consulter un auteur par son identifiant
//    @GetMapping("/{id}")
//    public Auteur getAuteurById(@PathVariable Integer id) {
//        return as.getAuteurById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id inconnu"));
//    }
//
//    //pour creer/inserer un auteur en base
//    @PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//    public Auteur createAuteur(@RequestBody Auteur auteur) {
//        if (Objects.isNull(auteur))
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'auteur ne peut pas etre null");
//        try{
//        return  as.createAuteur(auteur);}
//        catch (IllegalArgumentException e){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage()); }
//       
//    }
//
//    //Pour Modifier les attributs de l'auteur en base
//    @PutMapping
//    public Auteur updateAuteur(@RequestBody Auteur auteur) {
//        if (Objects.isNull(auteur))
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'auteur ne peut pas etre null");
//        try {
//            return as.updateAuteur(auteur);
//        } catch (IllegalArgumentException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
//
//    //supprimer un auteur de la base
//    @DeleteMapping("/{id}")
//    public void deleteAuteur(@PathVariable Integer id) {
//        if (Objects.isNull(id))
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'id ne peut pas etre null");
//        as.deleteAuteur(id);
//    }

}
