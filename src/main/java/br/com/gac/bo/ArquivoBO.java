package br.com.gac.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.gac.dao.ArquivoDAO;
import br.com.gac.dao.filter.ArquivoFilter;
import br.com.gac.model.Arquivo;

public class ArquivoBO implements Serializable {

	private static final long serialVersionUID = -1467298216587924632L;

	@Inject
	public ArquivoDAO arquivoDAO;

	public Arquivo save(Arquivo arquivo) {
		return arquivoDAO.save(arquivo);
	}

	public List<Arquivo> getArquivoByProcessoId(int first, int pageSize, ArquivoFilter filter) {
		return arquivoDAO.getArquivoByProcessoId(first, pageSize, filter);
	}

	public Long encontrarQuantidadeArquivo(ArquivoFilter filter) {
		return arquivoDAO.encontrarQuantidadeArquivo(filter);
	}

}
