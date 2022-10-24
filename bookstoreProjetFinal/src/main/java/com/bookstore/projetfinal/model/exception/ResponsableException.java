package com.bookstore.projetfinal.model.exception;

import org.springframework.web.server.ResponseStatusException;

public interface ResponsableException {

	public ResponseStatusException toResponse();
}
