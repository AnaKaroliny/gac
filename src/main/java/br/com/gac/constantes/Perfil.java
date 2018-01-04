package br.com.gac.constantes;

public enum Perfil {

	ADMINISTRADOR("Administrador"), DEFENSOR_DO_VINCULO("Defensor do Vínculo"), JUIZ("Juíz"), JUIZ_AUDITOR(
			"Juíz Auditor"), VIGARIO_JUDICIAL("Vigário Judicial");

	private String descricao;

	Perfil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
