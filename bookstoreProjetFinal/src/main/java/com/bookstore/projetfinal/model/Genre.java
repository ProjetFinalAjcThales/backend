package com.bookstore.projetfinal.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@SequenceGenerator(name = "genre_gen", sequenceName = "genre_seq", initialValue = 100, allocationSize = 1)
public class Genre {
        @Id
        @GeneratedValue(generator = "genre_gen")
        private Integer id;
        private String nom;
        
        
        
        @OneToMany(mappedBy="genre")
        @JsonIgnoreProperties("genre")
        private List<Livre> livres;

        

		public Genre() {}


		public Genre(Integer id, String nom, List<Livre> livres) {
			this.id = id;
			this.nom = nom;
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


		public List<Livre> getLivres() {
			return livres;
		}


		public void setLivres(List<Livre> livres) {
			this.livres = livres;
		}
        
		

        
}


