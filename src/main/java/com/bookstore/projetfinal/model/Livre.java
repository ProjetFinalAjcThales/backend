package com.bookstore.projetfinal.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@SequenceGenerator(name = "livre_gen", sequenceName = "livre_seq", initialValue = 100, allocationSize = 1)
public class Livre {
	@Id
	@GeneratedValue(generator = "livre_gen")
	private Integer id;
	private String titre; 
	private String description;
	private String image;
	private LocalDate dateSortie;
	private Double prix;
	@Version
	private int version;
	
	
	public Livre() {
	}

	

	public Livre(Integer id, String titre, String description, Double prix, Long qte, List<CommandeLivre> commandes,
			Genre genre, Auteur auteur) {
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.prix = prix;
		this.qte = qte;
		this.commandes = commandes;
		this.genre = genre;
		this.auteur = auteur;
	}



	@Column(name = "qte_stock")
	private Long qte;
	@OneToMany(mappedBy = "livre")
	@JsonIgnoreProperties("livre")
	private List<CommandeLivre> commandes;
	
	@ManyToOne
	@JoinColumn(name = "id_genre")
	@JsonIgnoreProperties("livres")
	private Genre genre;
	
	
	@ManyToOne
	@JoinColumn(name = "id_auteur")
	@JsonIgnoreProperties("livres")
	private Auteur auteur;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public LocalDate getDateSortie() {
		return dateSortie;
	}


	public void setDateSortie(LocalDate dateSortie) {
		this.dateSortie = dateSortie;
	}


	public Double getPrix() {
		return prix;
	}


	public void setPrix(Double prix) {
		this.prix = prix;
	}


	public Long getQte() {
		return qte;
	}


	public void setQte(Long qte) {
		this.qte = qte;
	}


	public List<CommandeLivre> getCommandes() {
		return commandes;
	}


	public void setCommandes(List<CommandeLivre> commandes) {
		this.commandes = commandes;
	}


	public Genre getGenre() {
		return genre;
	}


	public void setGenre(Genre genre) {
		this.genre = genre;
	}


	public Auteur getAuteur() {
		return auteur;
	}


	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}



	public int getVersion() {
		return version;
	}



	public void setVersion(int version) {
		this.version = version;
	}
	
	
	
	
}
