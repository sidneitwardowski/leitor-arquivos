package br.com.empresa.model;

import java.math.BigDecimal;

public class VendedorBuilder {

	private Vendedor instance;

	public VendedorBuilder(String registro) {
		String[] registroSeparado = registro.split("ç");
		this.instance = new Vendedor(registroSeparado[1], registroSeparado[2], new BigDecimal(registroSeparado[3]));
	}

	public Vendedor build() {
		return this.instance;
	}

}
