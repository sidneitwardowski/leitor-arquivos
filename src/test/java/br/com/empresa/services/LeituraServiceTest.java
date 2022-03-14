package br.com.empresa.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LeituraServiceTest {

	private List<String> dados;
	private LeituraService service;

	@BeforeEach
	public void init() {
		this.dados = new ArrayList<>();
		dados.add("001ç1234567891234çPedroç50000");
		dados.add("001ç3245678865434çPauloç40000.99");
		dados.add("002ç2345675434544345çJose da SilvaçRural");
		dados.add("002ç2345675433444345çEduardo PereiraçRural");
		dados.add("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");
		dados.add("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");

		this.service = new LeituraService();
		service.processarRegistros(dados);
	}

	@Test
	public void deveObterQuantiadeDeClientes() {
		assertTrue(service.obterClientes().size() == 2);
	}

	@Test
	public void deveObterQuantidadeDeVendedores() {
		assertTrue(service.obterVendedores().size() == 2);
	}

	@Test
	public void deveObterVendas() {
		assertTrue(service.obterVendas().size() > 0);
	}

	@Test
	public void deveObterPiorVendedor() {
		assertEquals("Paulo", service.obterPiorVendedor());
	}

	@Test
	public void deveObterMaiorVenda() {
		assertTrue(service.obterIdMaiorVenda() == 10L);
	}

	@Test
	public void deveGerarResultadoFormatado() {
		assertEquals("2ç2ç10çPaulo", service.obterResultadoFormatado());
	}

	@AfterEach
	public void limpar() {
		service.Limpar();
	}

}
