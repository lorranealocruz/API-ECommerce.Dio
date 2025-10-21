package br.com.ecommerce.api_ecommerce.service.exceptions;

public class PedidoNaoEncontradoException extends RuntimeException {
	public PedidoNaoEncontradoException(String msg) {
        super(msg);
    }
}
