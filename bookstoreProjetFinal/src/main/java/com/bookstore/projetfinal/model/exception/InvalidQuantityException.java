package com.bookstore.projetfinal.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.bookstore.projetfinal.model.CommandeLivre;

public class InvalidQuantityException extends Exception implements ResponsableException {


	private static final long serialVersionUID = -1259489077787842446L;

	public InvalidQuantityException(CommandeLivre cl) {
		super("La commande ne peut aboutir.\nLa quantité ("+cl.getQte()+") du produit "+cl.getLivre().getTitre() +" éxcède les quantités disponibles.");
	}
	
	public ResponseStatusException toResponse() {
		return new ResponseStatusException(HttpStatus.CONFLICT,super.getMessage());
	}

}
