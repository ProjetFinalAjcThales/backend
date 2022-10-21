package com.bookstore.projetfinal.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="auteur")
@SequenceGenerator(name = "auteur_gen", sequenceName = "auteur_seq", initialValue = 100, allocationSize = 1)
public class Auteur {
	
	@Id
	@GeneratedValue(generator = "auteur_gen")
	private Integer id;
	
	
	private String nom;
	
	
	private String prenom;
	
		
	public Auteur() {}

	public Auteur(Integer id, String nom, String prenom, String img, List<Livre> livres) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.img = img;
		this.livres = livres;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<Livre> getLivres() {
		return livres;
	}

	public void setLivres(List<Livre> livres) {
		this.livres = livres;
	}

	private String img;
	
	@OneToMany(mappedBy = "auteur")
	
	private List<Livre> livres; 
	
		

}
