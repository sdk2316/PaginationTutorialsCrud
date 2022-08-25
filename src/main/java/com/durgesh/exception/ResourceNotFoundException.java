package com.durgesh.exception;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2001701405304983570L;

	private String message;

	public ResourceNotFoundException(String message) {
		
		this.message = message;
	}
	

	
	
}
