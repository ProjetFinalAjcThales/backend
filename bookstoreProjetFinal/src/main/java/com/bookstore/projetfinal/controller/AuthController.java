package com.bookstore.projetfinal.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bookstore.projetfinal.model.Client;
import com.bookstore.projetfinal.model.dto.UtilisateurClient;
import com.bookstore.projetfinal.model.security.Utilisateur;
import com.bookstore.projetfinal.repository.UtilisateurRepository;
import com.bookstore.projetfinal.service.ClientService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	UtilisateurRepository utilisateurRepo;

	@Autowired
	ClientService clientService;

	/*
	 * Création d'un utilisateur et client en même temps voir
	 * ClientSerivce->create()
	 */
	@PostMapping("/register")
	public Utilisateur register(@RequestBody UtilisateurClient uc) {
		System.out.println(uc.getMail());
		if((uc.getMail() == "")|| (uc.getMdp() == "") ||(uc.getPrenom() == "") || (uc.getNom() == ""))
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Veuillez remplir tous les champs.");
		if (utilisateurRepo.findFirstByMail(uc.getMail()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ce mail existe déjà.");
		
		return clientService.create(uc);
	}
	
	/*
	 * Connexion d'un utilisateur Renvoie le client correspondant
	 */
	@CrossOrigin
	@GetMapping("/connexion/{mail}/{mdp}")
	public Client findByMailAndPass(@PathVariable(name = "mail") String mail, @PathVariable(name = "mdp") String mdp) {
		if((mail == "")|| (mdp == ""))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Veuillez remplir tous les champs.");
		// si aucun mail est dans la bdd => erreur
		if (!utilisateurRepo.findFirstByMail(mail).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aucun mail correspondant n'a été trouvé.");

		// je récupère l'utilisateur selon le mail fourni
		Utilisateur utilisateurBdd = utilisateurRepo.findFirstByMail(mail).get();
		Client clientBdd = clientService.findById(utilisateurBdd.getClient().getId()).get();
		
		
		// je compare le mdp fourni avec le mdp de l'utilisateur dans la bdd
		if (!mdp.equals(utilisateurBdd.getMdp()))
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
					"Le mot de passe ne correspond pas à celui en base de données.");
		return clientBdd;

	}

	// POUR PLUS TARD SI ON A LE TEMPS DE METTRE LA SCURITÉ
//	/*
//	 * Modification du mot de passe
//	 */
//	@PreAuthorize("isAuthenticated()")
//	@PutMapping
//	public Utilisateur updateUserInfo(@RequestBody Utilisateur u, Principal p) {
//		if (Objects.isNull(u.getId()))
//			throw EntityNotFoundException.responseStatus(Utilisateur.class, p.getName());
//		if (!p.getName().equals(u.getIdentifiant()))
//			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
//					"Vous ne pouvez pas modifiez un autre utilisateur");
//		u.setMdp(pe.encode(u.getMdp()));
//		return ur.save(u);
//	}

}
