package br.com.gac.dao.datamodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.gac.bo.ProcessoBO;
import br.com.gac.dao.filter.ProcessoFilter;
import br.com.gac.model.Processo;

public class ProcessoDataModel extends LazyDataModel<Processo> implements Serializable {

	private static final long serialVersionUID = 4473180678891034260L;

	private ProcessoFilter filter;

	private ProcessoBO processoBO;

	List<Processo> processos = null;

	public ProcessoDataModel(ProcessoFilter filter, ProcessoBO processoBO) {
		this.filter = filter;
		this.processoBO = processoBO;
	}

	@Override
	public List<Processo> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		processos = processoBO.getProcessoByMatricula(first, pageSize, filter);
		setRowCount(processoBO.encontrarQuantidadeProcesso(filter).intValue());
		return processos;
	}
}