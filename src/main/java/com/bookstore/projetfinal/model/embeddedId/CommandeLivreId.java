package com.bookstore.projetfinal.model.embeddedId;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class CommandeLivreId implements Serializable{

	private static final long serialVersionUID = -4267043085014312754L;

	@Column(name="id_livre")
	Integer idLivre;

	@Column(name="id_commande")
	Integer idCommande;

	
	
	
	public CommandeLivreId() {
	}




	public CommandeLivreId(Integer idLivre, Integer idCommande) {
		this.idLivre = idLivre;
		this.idCommande = idCommande;
	}




	public Integer getIdLivre() {
		return idLivre;
	}




	public void setIdLivre(Integer idLivre) {
		this.idLivre = idLivre;
	}




	public Integer getIdCommande() {
		return idCommande;
	}




	public void setIdCommande(Integer idCommande) {
		this.idCommande = idCommande;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	



}
