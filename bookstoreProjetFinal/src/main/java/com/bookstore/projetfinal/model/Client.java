package com.bookstore.projetfinal.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.bookstore.projetfinal.model.dto.UtilisateurClient;
import com.bookstore.projetfinal.model.security.Utilisateur;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@SequenceGenerator(name = "client_gen", sequenceName = "client_seq", initialValue = 100, allocationSize = 1)
public class Client {
	@Id
	@GeneratedValue(generator = "client_gen")
	private Integer id;
	private String nom;
	private String prenom;
	private String adresse;
	private String ville;
	private String codePostal;
	private LocalDate createdAt;

	@OneToMany(mappedBy = "client")
	@JsonIgnoreProperties("client")
	private List<Commande> commandes;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_user", referencedColumnName = "id")
	@JsonIgnoreProperties()
	private Utilisateur utilisateur;

	public Client() {
	}

	/*
	 * Constructeur spécial pour la création d'un utilisateur et client en même
	 * temps
	 */
	public Client(UtilisateurClient uc) {
		this.utilisateur = new Utilisateur(uc);
		this.createdAt = LocalDate.now();
		this.nom = uc.getNom();
		this.prenom = uc.getPrenom();
		this.adresse = uc.getAdresse();
		this.codePostal = uc.getCodePostal();
		this.ville = uc.getVille();
	}

	public Client(Integer id, String nom, String prenom, String adresse, String ville, String codePostal,
			Utilisateur utilisateur) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.ville = ville;
		this.codePostal = codePostal;
		this.utilisateur = utilisateur;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}