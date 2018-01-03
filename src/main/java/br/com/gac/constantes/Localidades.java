package br.com.gac.constantes;

public enum Localidades {

	QUIXADA("Quixadá"), FORTALEZA("Fortaleza"), IBARETAMA("Ibaretama");

	private String descricao;

	private Localidades(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
