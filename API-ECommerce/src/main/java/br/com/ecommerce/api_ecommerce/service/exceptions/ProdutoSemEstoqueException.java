package br.com.ecommerce.api_ecommerce.service.exceptions;

public class ProdutoSemEstoqueException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProdutoSemEstoqueException(String msg) {
        super(msg);
    }
}
