package br.com.empresa.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ClienteBuilderTest {

	@Test
	public void deveConstruirCliente() {
		String registro = "002ç2345675434544345çJose da SilvaçRural";
		Cliente cliente = new ClienteBuilder(registro).build();
		assertEquals("Jose da Silva", cliente.getNome());
		assertEquals("2345675434544345", cliente.getCnpj());
		assertEquals("Rural", cliente.getAreaNegocios());
	}

}
