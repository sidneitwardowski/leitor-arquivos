package br.com.empresa.services;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.empresa.model.Cliente;
import br.com.empresa.model.ClienteBuilder;
import br.com.empresa.model.Venda;
import br.com.empresa.model.VendaBuilder;
import br.com.empresa.model.Vendedor;
import br.com.empresa.model.VendedorBuilder;

public class LeituraService {

	private List<Object> lista;

	public LeituraService() {
		this.lista = new ArrayList<>();
	}

	public void processarRegistros(List<String> linhas) {
		linhas.stream().forEach(linha -> processar(linha));
	}

	private void processar(String linha) {
		Object objeto = obterObjetoCorrespondente(linha);
		lista.add(objeto);

	}

	public List<Cliente> obterClientes() {
		return lista.stream().filter(obj -> obj instanceof Cliente).map(obj -> (Cliente) obj)
				.collect(Collectors.toList());
	}

	public List<Vendedor> obterVendedores() {
		return lista.stream().filter(obj -> obj instanceof Vendedor).map(obj -> (Vendedor) obj)
				.collect(Collectors.toList());
	}

	public List<Venda> obterVendas() {
		return lista.stream().filter(obj -> obj instanceof Venda).map(obj -> (Venda) obj).collect(Collectors.toList());
	}

	public String obterPiorVendedor() {
		List<Venda> vendas = obterVendas();
		BigDecimal piorValorVenda = obterTotalVenda(vendas.get(0));
		Venda piorVenda = vendas.get(0);
		for (Venda venda : vendas) {
			if (piorValorVenda.compareTo(obterTotalVenda(venda)) < 0) {
			} else {
				piorValorVenda = obterTotalVenda(venda);
				piorVenda = venda;
			}
		}

		return piorVenda.getNomeVendedor();

	}

	public Long obterIdMaiorVenda() {
		BigDecimal maiorValor = BigDecimal.ZERO;
		Long idVenda = 0L;
		List<Venda> vendas = obterVendas();

		for (Venda venda : vendas) {

			BigDecimal totalVenda = obterTotalVenda(venda);

			if (maiorValor.compareTo(totalVenda) <= 0) {
				idVenda = venda.getId();
				maiorValor = totalVenda;
			}

		}

		return idVenda;
	}

	public void Limpar() {
		lista.clear();
	}

	public String obterResultadoFormatado() {
		return String.format("%dç%dç%dç%s", obterClientes().size(), obterVendedores().size(), obterIdMaiorVenda(),
				obterPiorVendedor());
	}

	private BigDecimal obterTotalVenda(Venda venda) {

		return venda.getItens().stream().map(s -> s.getPrecoTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);

	}

	private Object obterObjetoCorrespondente(String linha) {
		String tipo = linha.substring(0, 3);
		Object retorno = null;

		switch (tipo) {
		case "001":
			retorno = new VendedorBuilder(linha).build();
			break;

		case "002":
			retorno = new ClienteBuilder(linha).build();
			break;

		case "003":
			retorno = new VendaBuilder(linha).build();
			break;
		default:
			throw new InvalidParameterException("Tipo não localizado");
		}

		return retorno;
	}

}
