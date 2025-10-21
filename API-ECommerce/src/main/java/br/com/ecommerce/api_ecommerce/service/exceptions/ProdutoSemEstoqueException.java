package br.com.ecommerce.api_ecommerce.service.exceptions;

public class ProdutoSemEstoqueException extends RuntimeException {

	public ProdutoSemEstoqueException(String msg) {
        super(msg);
    }
}
