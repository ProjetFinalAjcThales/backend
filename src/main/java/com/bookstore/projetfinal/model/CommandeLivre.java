package com.bookstore.projetfinal.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Version;

import com.bookstore.projetfinal.model.embeddedId.CommandeLivreId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class CommandeLivre {
		@EmbeddedId
         CommandeLivreId id;
        
        @ManyToOne
        @MapsId("idCommande")
        @JoinColumn(name="id_commande")
        @JsonIgnoreProperties("livres")
        private Commande commande;
        
        @ManyToOne
        @MapsId("idLivre")
        @JoinColumn(name="id_livre")
        @JsonIgnoreProperties("commandes")
       
        private Livre livre;
        
        private Integer qte;
        
        @Version
    	private int version;

        
        
		public CommandeLivre() {}
		
		

		public CommandeLivre(CommandeLivreId id, Commande commande, Livre livre, Integer qte) {
			this.id = id;
			this.commande = commande;
			this.livre = livre;
			this.qte = qte;
		}


		public CommandeLivreId getId() {
			return id;
		}

		public void setId(CommandeLivreId id) {
			this.id = id;
		}

		public Commande getCommande() {
			return commande;
		}

		public void setCommande(Commande commande) {
			this.commande = commande;
		}

		public Livre getLivre() {
			return livre;
		}

		public void setLivre(Livre livre) {
			this.livre = livre;
		}

		public Integer getQte() {
			return qte;
		}

		public void setQte(Integer qte) {
			this.qte = qte;
		}



		public int getVersion() {
			return version;
		}



		public void setVersion(int version) {
			this.version = version;
		}
        
		

        
}