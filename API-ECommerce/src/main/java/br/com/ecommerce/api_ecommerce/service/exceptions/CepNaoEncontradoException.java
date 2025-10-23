package br.com.ecommerce.api_ecommerce.service.exceptions;

public class CepNaoEncontradoException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

	public CepNaoEncontradoException(String msg) {
        super(msg);
    }
}
