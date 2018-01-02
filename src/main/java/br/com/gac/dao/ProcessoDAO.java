package br.com.gac.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.gac.dao.filter.ProcessoFilter;
import br.com.gac.model.Processo;

public class ProcessoDAO implements Serializable {

	private static final long serialVersionUID = 3631624015270199568L;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public Processo save(Processo processo) {
		return manager.merge(processo);
	}

	public List<Processo> findByMatricula(int first, int pageSize, ProcessoFilter filter) {
		return manager
				.createQuery(
						"select p from Processo p where p.usuario.matricula = :matricula", Processo.class)
				.setParameter("matricula", filter.getMatricula()).setFirstResult(first).setMaxResults(pageSize)
				.getResultList();
	}
	
	public Long encontrarQuantidadeProcesso(ProcessoFilter filter) {
			return manager
					.createQuery("select count(p) from Processo p where p.usuario.matricula = :matricula", Long.class)
					.setParameter("matricula", filter.getMatricula()).getSingleResult();

	}

}
