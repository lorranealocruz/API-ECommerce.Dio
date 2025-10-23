package br.com.ecommerce.api_ecommerce.service.exceptions;

public class PedidoNaoEncontradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException(String msg) {
        super(msg);
    }
}
