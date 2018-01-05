package br.com.gac.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.gac.dao.filter.ArquivoFilter;
import br.com.gac.model.Arquivo;

public class ArquivoDAO implements Serializable {

	private static final long serialVersionUID = 5512747192820937994L;

	@PersistenceContext(unitName = "gac-pu")
	private EntityManager manager;

	@Transactional
	public Arquivo save(Arquivo arquivo) {
		return manager.merge(arquivo);
	}

	public List<Arquivo> getArquivoByProcessoId(int first, int pageSize, ArquivoFilter filter) {
		return manager.createQuery("select a from Arquivo a where a.processo.id = :id", Arquivo.class)
				.setParameter("id", filter.getId()).setFirstResult(first).setMaxResults(pageSize).getResultList();
	}

	public Long encontrarQuantidadeArquivo(ArquivoFilter filter) {
		return manager.createQuery("select count(a) from Arquivo a where a.processo.id = :id", Long.class)
				.setParameter("id", filter.getId()).getSingleResult();

	}
	
	public Arquivo findArquivoById(Integer id){
		return manager.createNamedQuery("Arquivo.findById", Arquivo.class).setParameter("id", id).getSingleResult();
	}

}
