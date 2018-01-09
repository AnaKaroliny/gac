package br.com.gac.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import br.com.gac.constantes.Localidades;
import br.com.gac.constantes.Status;

@NamedQueries({ @NamedQuery(name = "Processo.findById", query = "select p from Processo p where p.id = :id") })

@Entity
@Table(name = "PROCESSO")
public class Processo implements Serializable {

	private static final long serialVersionUID = -7939608821758289143L;

	private Integer id;
	private Localidades localidade;
	private String entradaDireta;
	private String classeAcao;
	private String tipoDistribuicao;
	private boolean urgencia;
	private boolean segredoJustiça;
	private String assunto;
	private String complementoAssunto;
	private String detalheAssunto;
	private String nomeDemandante;
	private String nomeDemandado;
	private String cpfDemandante;
	private String cpfDemandado;
	private Usuario usuario;
	private List<Arquivo> arquivos;
	private Status status;

	public void adicionaArquivo(Arquivo arquivo) {
		arquivos = new ArrayList<>();
		this.arquivos.add(arquivo);
		arquivo.setProcesso(this);
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Enumerated(EnumType.STRING)
	public Localidades getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidades localidade) {
		this.localidade = localidade;
	}

	@Column(name = "entrada_direta")
	public String getEntradaDireta() {
		return entradaDireta;
	}

	public void setEntradaDireta(String entradaDireta) {
		this.entradaDireta = entradaDireta;
	}

	@Column(name = "classe_acao")
	public String getClasseAcao() {
		return classeAcao;
	}

	public void setClasseAcao(String classeAcao) {
		this.classeAcao = classeAcao;
	}

	@Column(name = "tipo_distribuicao")
	public String getTipoDistribuicao() {
		return tipoDistribuicao;
	}

	public void setTipoDistribuicao(String tipoDistribuicao) {
		this.tipoDistribuicao = tipoDistribuicao;
	}

	@Type(type = "true_false")
	public boolean isUrgencia() {
		return urgencia;
	}

	public void setUrgencia(boolean urgencia) {
		this.urgencia = urgencia;
	}

	@Column(name = "segredo_justica")
	@Type(type = "true_false")
	public boolean isSegredoJustiça() {
		return segredoJustiça;
	}

	public void setSegredoJustiça(boolean segredoJustiça) {
		this.segredoJustiça = segredoJustiça;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	@Column(name = "complemento_assunto")
	public String getComplementoAssunto() {
		return complementoAssunto;
	}

	public void setComplementoAssunto(String complementoAssunto) {
		this.complementoAssunto = complementoAssunto;
	}

	@Column(name = "detalhe_assunto")
	public String getDetalheAssunto() {
		return detalheAssunto;
	}

	public void setDetalheAssunto(String detalheAssunto) {
		this.detalheAssunto = detalheAssunto;
	}

	@Column(name = "nome_demandante")
	public String getNomeDemandante() {
		return nomeDemandante;
	}

	public void setNomeDemandante(String nomeDemandante) {
		this.nomeDemandante = nomeDemandante;
	}

	@Column(name = "nome_demandado")
	public String getNomeDemandado() {
		return nomeDemandado;
	}

	public void setNomeDemandado(String nomeDemandado) {
		this.nomeDemandado = nomeDemandado;
	}

	@Column(name = "cpf_demandante")
	public String getCpfDemandante() {
		return cpfDemandante;
	}

	public void setCpfDemandante(String cpfDemandante) {
		this.cpfDemandante = cpfDemandante;
	}

	@Column(name = "cpf_demandado")
	public String getCpfDemandado() {
		return cpfDemandado;
	}

	public void setCpfDemandado(String cpfDemandado) {
		this.cpfDemandado = cpfDemandado;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@OneToMany(mappedBy = "processo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Arquivo> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}

	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Processo other = (Processo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
