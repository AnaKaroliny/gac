package br.com.gac.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.gac.dao.ProcessoDAO;
import br.com.gac.dao.filter.ProcessoFilter;
import br.com.gac.model.Processo;

public class ProcessoBO implements Serializable {

	private static final long serialVersionUID = 6366188189526543359L;

	@Inject
	public ProcessoDAO processoDAO;

	public Processo save(Processo processo) {
		return processoDAO.save(processo);
	}

	public List<Processo> getProcessoByMatricula(int first, int pageSize, ProcessoFilter filter) {
		return processoDAO.findByMatricula(first, pageSize, filter);
	}

	public Long encontrarQuantidadeProcesso(ProcessoFilter filter) {
		return processoDAO.encontrarQuantidadeProcesso(filter);
	}

	public Processo findProcessoById(String id) {
		return processoDAO.findProcessoById(id);
	}

}
