package com.bookstore.projetfinal.model.exception;

import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/*
 * Une classe pour gérer les exceptions sur les entités qui n'existe pas et créer les response status exception 
 */
public class EntityNotFoundException extends Exception  implements ResponsableException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4906597582996324768L;
	private Object id;
	private Class<?> origin;

	public EntityNotFoundException(Class<?> c,Object id) {
		super(message(c.getName(),id));
		this.origin = c;
		this.id = id;
	}
	
	public ResponseStatusException toResponse() {
		return new ResponseStatusException(HttpStatus.NOT_FOUND,message(origin.getSimpleName(),id));
	}
	
	private static String message(String className,Object id) {
		return "L'objet ["+className+", id="+id+"] n'existe pas";
	}
	
	public static ResponseStatusException responseStatus(Class<?> c,Object id) {
		return new EntityNotFoundException(c,id).toResponse();
	}
	
	public static Supplier<? extends ResponseStatusException> responseStatusSupplier(Class<?> c,Object id) {
		return () -> responseStatus(c,id);
	}
}
