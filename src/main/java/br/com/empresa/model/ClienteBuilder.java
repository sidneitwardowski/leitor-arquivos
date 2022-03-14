package br.com.empresa.model;

public class ClienteBuilder {

	private Cliente instance;

	public ClienteBuilder(String registro) {
		String[] registroSeparado = registro.split("ç");
		this.instance = new Cliente(registroSeparado[1], registroSeparado[2], registroSeparado[3]);
	}

	public Cliente build() {
		return this.instance;
	}

}
