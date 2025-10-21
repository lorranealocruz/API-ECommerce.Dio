package br.com.ecommerce.api_ecommerce.service.exceptions;

public class CepNaoEncontradoException extends RuntimeException {
    
    public CepNaoEncontradoException(String msg) {
        super(msg);
    }
}
