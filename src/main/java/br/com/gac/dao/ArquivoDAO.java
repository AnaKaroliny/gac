package br.com.gac.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.gac.model.Arquivo;

public class ArquivoDAO implements Serializable{

	private static final long serialVersionUID = 5512747192820937994L;
	
	@PersistenceContext(unitName = "gac-pu")
	private EntityManager manager;
	
	@Transactional
	public Arquivo save(Arquivo arquivo) {
		return manager.merge(arquivo);
	}

}
