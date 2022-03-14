package br.com.empresa.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class VendaBuilterTest {

	@Test
	public void deveConstruirVenda() {
		String registro = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
		Venda venda = new VendaBuilder(registro).build();
		assertEquals("Pedro", venda.getNomeVendedor());
		assertTrue(venda.getId() > 0);
		assertTrue(venda.getItens().size() > 0);
	}

}
