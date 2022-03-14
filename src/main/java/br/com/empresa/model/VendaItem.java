package br.com.empresa.model;

import java.math.BigDecimal;

public class VendaItem {

	private Long id;
	private Long quantidade;
	private BigDecimal precoUnitario;

	VendaItem(Long id, Long quantidade, BigDecimal precoUnitario) {
		this.id = id;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public BigDecimal getPrecoTotal() {
		return precoUnitario.multiply(new BigDecimal(quantidade));
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

}
