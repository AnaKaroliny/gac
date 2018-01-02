package br.com.gac.bo;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.gac.dao.ArquivoDAO;
import br.com.gac.model.Arquivo;

public class ArquivoBO implements Serializable{

	private static final long serialVersionUID = -1467298216587924632L;
	
	@Inject
	public ArquivoDAO arquivoDAO;
	
	public Arquivo save(Arquivo arquivo){
		return arquivoDAO.save(arquivo);
	}
}
