package br.com.gac.constantes;

public enum Tipo {

	LIBELO("Libelo"), ARQUIVADO("Arquivado"), ACEITACAO("Aceitacao"), CONTESTACAO("Contestacao"), AUSENCIA(
			"Ausência"), FIXACAO_DUVIDA("Fixação da Dúvida"), NOMEACAO_JUIZES("Nomeação dos Juízes"), QUESTIONARIOS(
					"Questionários"), AUTOS("Autos"), INSTRUCAO("Instrução"), ARQUIVO_PARTES(
							"Arquivos das Partes"), PARECER("Parecer"), VOTACAO("Votação"), SETENCA("Setença");

	private String descricao;

	private Tipo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
