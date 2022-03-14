package br.com.empresa.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class VendedorBuilderTest {

	@Test
	public void deveConstruirVendedor() {
		String registro = "001ç1234567891234çPedçroç50000";
		Vendedor vendedor = new VendedorBuilder(registro).build();
		assertEquals("Pedro", vendedor.getNome());
		assertEquals("1234567891234", vendedor.getCpf());
		assertEquals(new BigDecimal("50000"), vendedor.getSalario());
	}

}
