package br.com.empresa.model;

import java.math.BigDecimal;

public class VendaItemBuilder {

	private VendaItem instance;

	public VendaItemBuilder(String registro) {
		String[] registroSeparado = registro.split("-");
		this.instance = new VendaItem(new Long(registroSeparado[0]), new Long(registroSeparado[1]),
				new BigDecimal(registroSeparado[2]));

	}

	public VendaItem build() {
		return this.instance;
	}

}
