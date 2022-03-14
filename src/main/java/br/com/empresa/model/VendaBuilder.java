package br.com.empresa.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VendaBuilder {

	private Venda instance;

	public VendaBuilder(String registro) {
		String[] registroSeparado = registro.split("ç");
		this.instance = new Venda(new Long(registroSeparado[1]), criarVendaItens(registroSeparado[2]),
				registroSeparado[3]);

	}

	public Venda build() {
		return this.instance;
	}

	private List<VendaItem> criarVendaItens(String registros) {
		List<VendaItem> vendaItens = new ArrayList<>();
		List<String> itens = Arrays.asList(registros.replace("[", "").replace("]", "").split(","));
		itens.forEach(i -> {
			vendaItens.add(new VendaItemBuilder(i).build());
		});

		return vendaItens;

	}

}
