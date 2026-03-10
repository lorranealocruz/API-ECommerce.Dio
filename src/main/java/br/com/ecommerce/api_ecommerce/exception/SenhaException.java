package br.com.ecommerce.api_ecommerce.exception;

public class SenhaException extends RuntimeException {
	
	private static final Long serialVersionUID = 1L;
	public SenhaException(String message) {
		super(message);
	}
}
