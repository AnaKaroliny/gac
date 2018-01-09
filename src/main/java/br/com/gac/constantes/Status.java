package br.com.gac.constantes;

public enum Status {
	ANALISE("Em análise"), ARQUIVADO("Arquivado"), ACEITO("Aceito"), NAO_ACEITO("Não aceito"), CONTESTADO(
			"Contestado pelo demandado"), DEMANDADO_AUSENTE("Demandado declarado ausente"), FIXACAO_DUVIDA(
					"Decretado a fixação da dúvida"), NOMEAÇAO_JUIZES("Juízes nomeados"), QUESTIONARIOS(
							"Elaborado os questionários"), INSTRUÇÃO("Instrução efetuada"), PUBLICACAO_AUTOS(
									"Decretado a Publicação dos Autos"), OUVIR_PARTES("Atendido as partes"), PARECER(
											"Publicado o parecer"), VOTACAO(
													"Publicado as votações"), SENTENÇA("Decretado a Setença");

	private String descricao;

	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
