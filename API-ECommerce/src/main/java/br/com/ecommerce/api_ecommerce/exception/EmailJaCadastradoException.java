package br.com.ecommerce.api_ecommerce.exception;

public class EmailJaCadastradoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public EmailJaCadastradoException(String message) {
        super(message);
    }
}