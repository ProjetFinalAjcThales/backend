package com.bookstore.projetfinal.model.security;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;



import com.bookstore.projetfinal.model.Client;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@SequenceGenerator(name = "utilisateur_gen", sequenceName = "utilisateur_seq", initialValue = 100, allocationSize = 1)
public class Utilisateur {

        @Id
        @GeneratedValue(generator = "utilisateur_gen")
        private Integer id;
        private String mail;
        private String mdp;

        @OneToOne(mappedBy = "utilisateur")
        @JsonIgnoreProperties("utilisateur")
        private Client client;
        //private List<Role> roles;
        
        
		public Utilisateur() {
		}
		public Utilisateur(Integer id, String mail, String mdp, Client client) {
			this.id = id;
			this.mail = mail;
			this.mdp = mdp;
			this.client = client;
		}




		public Integer getId() {
			return id;
		}




		public void setId(Integer id) {
			this.id = id;
		}




		public String getMail() {
			return mail;
		}




		public void setMail(String mail) {
			this.mail = mail;
		}




		public String getMdp() {
			return mdp;
		}




		public void setMdp(String mdp) {
			this.mdp = mdp;
		}




		public Client getClient() {
			return client;
		}




		public void setClient(Client client) {
			this.client = client;
		}
		
		/*public UserDetails toUserDetails() {
			List<GrantedAuthority> auths = roles.stream().map(Role::name)// transformation en String (role -> role.name())
					.map(SimpleGrantedAuthority::new)// transformation en objet (name -> new SimpleGrantedAuthority(name))
					.collect(Collectors.toList());
			return new User(this.mail, this.mdp, auths);
		}*/
		/*
		 * public UserDetails toUserDetails() { System.out.println("toUserDetails");
		 * return new User(mail, mdp, new ArrayList<>()); }
		 */








}
