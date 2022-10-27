package com.bookstore.projetfinal.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@SequenceGenerator(name = "commande_gen", sequenceName = "commande_seq", initialValue = 100, allocationSize = 1)
public class Commande {
	
        @Id
        @GeneratedValue(generator = "commande_gen")
        private Integer id;
        
        private Boolean confirme;
        
        private LocalDateTime dateCreation;
        
        public Commande() {}
        
		public Commande(Integer id, Boolean confirme, Client client) {
			this.id = id;
			this.confirme = confirme;
			this.client = client;
		}

		@ManyToOne 
        @JoinColumn (name = "id_client")
        @JsonIgnoreProperties("client")
        private Client client;
        
        
        @OneToMany(mappedBy = "commande")
        @JsonIgnoreProperties("commande")
        private List<CommandeLivre> livres;


		public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
		}


		public Boolean getConfirme() {
			return confirme;
		}


		public void setConfirme(Boolean confirme) {
			this.confirme = confirme;
		}


		public LocalDateTime getDateCreation() {
			return dateCreation;
		}


		public void setDateCreation(LocalDateTime dateCreation) {
			this.dateCreation = dateCreation;
		}


		public Client getClient() {
			return client;
		}


		public void setClient(Client client) {
			this.client = client;
		}


		public List<CommandeLivre> getLivres() {
			return livres;
		}


		public void setLivres(List<CommandeLivre> livres) {
			this.livres = livres;
		}

		@Override
		public String toString() {
			return "Commande [id=" + id + ", confirme=" + confirme + ", dateCreation=" + dateCreation + ", client="
					+ client + ", livres=" + livres + "]";
		}
        
        
        
}
	
	
