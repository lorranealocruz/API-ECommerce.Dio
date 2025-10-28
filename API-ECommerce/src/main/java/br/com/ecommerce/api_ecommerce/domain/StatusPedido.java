package br.com.ecommerce.api_ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.ecommerce.api_ecommerce.exception.EnumValidationException;

public enum StatusPedido {

		AGUARDANDO_PAGAMENTO,
		PAGO,
		ENVIADO,
		CANCELADO;
	
	@JsonCreator
	public static StatusPedido verifica(String value) throws EnumValidationException {
		for (StatusPedido c: values()) {
			if (value.equals(c.name())) {
				return c;
			}
		}
		throw new EnumValidationException("Status preenchido incorretamente");
	}
	

}
