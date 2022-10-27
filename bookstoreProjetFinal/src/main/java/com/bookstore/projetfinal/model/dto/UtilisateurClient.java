package com.bookstore.projetfinal.model.dto;

public class UtilisateurClient {
	
	private String mail;
	private String mdp;
	private String nom;
	private String prenom;
	private String adresse;
	private String codePostal;
	private String ville;

	public UtilisateurClient() {
	}

	public UtilisateurClient(String mail, String mdp) {
		this.mail = mail;
		this.mdp = mdp;
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

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "UtilisateurClient [mail=" + mail + ", mdp=" + mdp + ", nom=" + nom + ", prenom=" + prenom + ", adresse="
				+ adresse + ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}

}
