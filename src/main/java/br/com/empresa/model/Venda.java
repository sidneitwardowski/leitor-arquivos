package br.com.empresa.model;

import java.util.List;

public class Venda {

	private Long id;

	private String nomeVendedor;

	private List<VendaItem> itens;

	Venda(Long id, List<VendaItem> itens, String nomeVendedor) {
		this.id = id;
		this.itens = itens;
		this.nomeVendedor = nomeVendedor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public List<VendaItem> getItens() {
		return itens;
	}

	public void setItens(List<VendaItem> itens) {
		this.itens = itens;
	}

	public void addItens(VendaItem item) {
		this.itens.add(item);
	}

}
