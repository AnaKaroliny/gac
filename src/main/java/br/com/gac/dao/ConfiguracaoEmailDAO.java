package br.com.gac.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.gac.email.ConfiguracaoEmail;

public class ConfiguracaoEmailDAO implements Serializable {

	private static final long serialVersionUID = -4550789955653382737L;

	@PersistenceContext(unitName = "gac-pu")
	private EntityManager manager;

	public ConfiguracaoEmail save(ConfiguracaoEmail email) {
		return manager.merge(email);
	}

	public ConfiguracaoEmail findConfiguracao() {
		try {
			return manager.createQuery("FROM ConfiguracaoEmail", ConfiguracaoEmail.class).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}