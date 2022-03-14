package br.com.empresa.model;

public class Cliente {

	private String cnpj;

	private String nome;

	private String areaNegocios;

	Cliente(String cnpj, String nome, String areaNegocios) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.areaNegocios = areaNegocios;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAreaNegocios() {
		return areaNegocios;
	}

	public void setAreaNegocios(String areaNegocios) {
		this.areaNegocios = areaNegocios;
	}

}
