package br.com.gac.dao;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.gac.constantes.Perfil;
import br.com.gac.dao.filter.UsuarioFilter;
import br.com.gac.model.Usuario;

public class UsuarioDAO implements Serializable {

	private static final long serialVersionUID = 9063942926793212775L;

	@PersistenceContext(unitName = "gac-pu")
	private EntityManager manager;
	
	public Usuario save(Usuario usuario) {
		return manager.merge(usuario);
	}

	public Usuario findByMatricula(String matricula) {
		try {
			return manager.createQuery("SELECT u FROM Usuario u WHERE u.matricula = :matricula AND u.ativo = 'T'",
					Usuario.class).setParameter("matricula", matricula).getSingleResult();
		} catch (NoResultException e) {
			System.err.println("Usuário não encontrado!");
		}
		return null;
	}

	public List<Usuario> findAll() {
		return manager.createQuery("FROM Usuario u JOIN FETCH u.grupos", Usuario.class).getResultList();
	}

	public List<Usuario> pesquisar(int first, int pageSize, UsuarioFilter filter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);

		root.fetch("grupos");

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		criteria.orderBy(builder.asc(root.get("nome")));

		return manager.createQuery(criteria).setFirstResult(first).setMaxResults(pageSize).getResultList();
	}

	public Long count(UsuarioFilter filter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Usuario> root = criteria.from(Usuario.class);

		criteria.select(builder.count(root));

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);

		return manager.createQuery(criteria).getSingleResult();
	}

	private Predicate[] criarRestricoes(UsuarioFilter filter, CriteriaBuilder builder, Root<Usuario> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (isNotEmpty(filter.getNome()))
			predicates.add(builder.like(builder.lower(root.get("nome")), "%" + filter.getNome().toLowerCase() + "%"));

		if (isNotEmpty(filter.getMatricula()))
			predicates.add(builder.like(builder.lower(root.get("matricula")), filter.getMatricula().toLowerCase()));

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	public Perfil permissoes(Usuario usuario) {
		return manager.createQuery(
				"SELECT DISTINCT u.perfil FROM Usuario u WHERE u = :usuario AND u.ativo = 'T'",
				Perfil.class).setParameter("usuario", usuario).getSingleResult();
	}
}