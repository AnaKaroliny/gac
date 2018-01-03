package br.com.gac.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gac.bo.ArquivoBO;
import br.com.gac.bo.ProcessoBO;
import br.com.gac.dao.datamodel.ArquivoDataModel;
import br.com.gac.dao.datamodel.ProcessoDataModel;
import br.com.gac.dao.filter.ArquivoFilter;
import br.com.gac.dao.filter.ProcessoFilter;
import br.com.gac.infra.jsf.FacesUtil;
import br.com.gac.model.Arquivo;
import br.com.gac.model.Processo;

@Named
@ViewScoped
public class PesquisarProcessoBean implements Serializable {

	private static final long serialVersionUID = -8131685749202231880L;

	private ProcessoFilter filter;
	private ProcessoDataModel data;
	private Processo processoSelecionado;
	private Arquivo arquivoSelecionado;
	private ArquivoFilter arquivoFilter;
	private ArquivoDataModel arquivoData;

	@Inject
	private ProcessoBO processoBO;

	@Inject
	private ArquivoBO arquivoBO;

	@Inject
	private FacesUtil facesUtil;

	@PostConstruct
	public void init() {
		filter = new ProcessoFilter();
		arquivoFilter = new ArquivoFilter();

		String id = facesUtil.getValorParametro("id");

		if (id != null) {
			processoSelecionado = processoBO.findProcessoById(id);
			arquivoFilter.setId(Integer.parseInt(id));
			pesquisarArquivos();
		}
	}

	public void pesquisar() {
		data = new ProcessoDataModel(filter, processoBO);
	}

	public void pesquisarArquivos() {
		arquivoData = new ArquivoDataModel(arquivoFilter, arquivoBO);
	}

	public ProcessoFilter getFilter() {
		return filter;
	}

	public ProcessoDataModel getData() {
		return data;
	}

	public void setFilter(ProcessoFilter filter) {
		this.filter = filter;
	}

	public Processo getProcessoSelecionado() {
		return processoSelecionado;
	}

	public void setProcessoSelecionado(Processo processoSelecionado) {
		this.processoSelecionado = processoSelecionado;
	}

	public Arquivo getArquivoSelecionado() {
		return arquivoSelecionado;
	}

	public void setArquivoSelecionado(Arquivo arquivoSelecionado) {
		this.arquivoSelecionado = arquivoSelecionado;
	}

	public ArquivoDataModel getArquivoData() {
		return arquivoData;
	}

	public void setArquivoData(ArquivoDataModel arquivoData) {
		this.arquivoData = arquivoData;
	}

}
