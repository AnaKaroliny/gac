package br.com.gac.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gac.bo.ProcessoBO;
import br.com.gac.dao.datamodel.ProcessoDataModel;
import br.com.gac.dao.filter.ProcessoFilter;

@Named
@ViewScoped
public class PesquisarProcessoBean implements Serializable {

	private static final long serialVersionUID = -8131685749202231880L;
	
	private ProcessoFilter filter;
	private ProcessoDataModel data;
	
	@Inject
	private ProcessoBO bo;

	@PostConstruct
	public void init() {
		filter = new ProcessoFilter();
	}

	public void pesquisar() {
		data = new ProcessoDataModel(filter, bo);
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

}
